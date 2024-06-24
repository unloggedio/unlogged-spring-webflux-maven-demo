package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ConcertReservationRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ConcertReservationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConcertClient {

    private final WebClient client;

    public ConcertClient(@Value("${integration.splitter.concert.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Flux<ConcertReservationResponse> reserve(Flux<ConcertReservationRequest> flux){
        return this.client
                .post()
                .body(flux, ConcertReservationRequest.class)
                .retrieve()
                .bodyToFlux(ConcertReservationResponse.class)
                .onErrorResume(ex -> Mono.empty());
    }

}
