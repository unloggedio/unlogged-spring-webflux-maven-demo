package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.dto.CarrierResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DHLClient {

    private static final String DHL = "DHL";
    private final WebClient client;

    public DHLClient(@Value("${integration.dhl.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<CarrierResponse> getServiceOptions(String from, String to){
        return this.client
                .get()
                .uri("{from}/{to}", from, to)
                .retrieve()
                .bodyToFlux(CarrierResponse.class)
                .doOnNext(fr -> this.normalizeResponse(fr, from, to))
                .onErrorResume(ex -> Mono.empty());
    }

    private void normalizeResponse(CarrierResponse result, String from, String to){
        result.setFrom(from);
        result.setTo(to);
        result.setServiceProvider(DHL);
    }
}