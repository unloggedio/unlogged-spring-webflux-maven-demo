package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.TheatreReservationRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.TheatreReservationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TheatreClient {

    private final WebClient client;

    public TheatreClient(@Value("${integration.splitter.theatre.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<TheatreReservationResponse> reserve(Flux<TheatreReservationRequest> flux){
        return this.client
                .post()
                .body(flux, TheatreReservationRequest.class)
                .retrieve()
                .bodyToFlux(TheatreReservationResponse.class)
                .onErrorResume(ex -> Mono.empty());
    }

}
