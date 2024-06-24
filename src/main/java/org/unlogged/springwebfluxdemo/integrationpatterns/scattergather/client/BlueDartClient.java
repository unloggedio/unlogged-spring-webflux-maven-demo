package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.dto.CarrierResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BlueDartClient {

    private final WebClient client;

    public BlueDartClient(@Value("${integration.bluedart.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<CarrierResponse> getServiceOptions(String from, String to){
        return this.client
                .post()
                .bodyValue(BlueDartRequest.create(from, to))
                .retrieve()
                .bodyToFlux(CarrierResponse.class)
                .onErrorResume(ex -> Mono.empty());
    }

    @Data
    @ToString
    @AllArgsConstructor(staticName = "create")
    private static class BlueDartRequest{
        private String from;
        private String to;
    }
}