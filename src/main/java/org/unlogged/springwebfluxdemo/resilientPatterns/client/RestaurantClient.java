package org.unlogged.springwebfluxdemo.resilientPatterns.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.Restaurant;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class RestaurantClient {

    private final WebClient client;

    public RestaurantClient(@Value("${resilient.restaurant.service}") String baseUrl) {
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
    public Mono<Restaurant> getRestaurant(Integer id){
        return this.client
                .get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Restaurant.class)
                .timeout(Duration.ofMillis(500))
                .onErrorResume(ex -> Mono.empty());
    }
}
