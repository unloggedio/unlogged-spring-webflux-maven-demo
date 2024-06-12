package org.unlogged.springwebfluxdemo.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.model.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Calls will fail because bearer token missing
 * */

@Service
public class UserClient {

    private final WebClient webClient;

    @Autowired
    public UserClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<UserDto> getAllUsers() {
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(UserDto.class);
    }

    public Mono<UserDto> getUserById(String id) {
        return webClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public Mono<UserDto> createUser(UserDto userDto) {
        return webClient.post()
                .uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(userDto))
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public Mono<UserDto> updateUser(String id, UserDto userDto) {
        return webClient.put()
                .uri("/users/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(userDto))
                .retrieve()
                .bodyToMono(UserDto.class);
    }

    public Mono<Void> deleteUser(String id) {
        return webClient.delete()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}

