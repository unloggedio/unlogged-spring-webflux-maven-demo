package org.unlogged.springwebfluxdemo.defaultmethods;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Animal {

    default Mono<ResponseEntity<String>> getDefaultMethodMono(String userId) {
        return Mono.just(ResponseEntity.ok("Cat"));
    }

    default Flux<String> getDefaultMethodFlux() {
        return Flux.just("Dog", "Lion", "Elephant");
    }

    static Mono<String> getStaticMethod() {
        return Mono.just("Static Animal");
    }
}
