package org.unlogged.springwebfluxdemo.service;

import reactor.core.publisher.Mono;

public class RecursiveService {
    public static Mono<Integer> factorial(Integer number) {
        if (number <= 1) {
            return Mono.just(1);
        } else {
            return Mono.just(number)
                    .flatMap(n -> factorial(n - 1))
                    .flatMap(result -> Mono.just(number * result));
        }
    }
}
