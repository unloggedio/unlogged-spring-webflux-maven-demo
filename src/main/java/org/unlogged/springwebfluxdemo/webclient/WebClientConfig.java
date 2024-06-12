package org.unlogged.springwebfluxdemo.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.security.service.JWTService;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpHeaders;

@Configuration
public class WebClientConfig {

    @Autowired
    private JWTService jwtService;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .filter(this::addAuthHeader)
                .build();
    }

    private Mono<ClientResponse> addAuthHeader(ClientRequest request, ExchangeFunction next) {
        String token = jwtService.generate("Akshat"); // hacky way for testing secured paths
        ClientRequest modifiedRequest = ClientRequest.from(request)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
        return next.exchange(modifiedRequest);
    }
}
