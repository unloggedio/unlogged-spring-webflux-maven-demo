package org.unlogged.springwebfluxdemo.controller;

import reactor.core.publisher.Mono;

public class MockUtils {
    public Mono<String> returnMockString() {
        return Mono.just("Test String");
    }

    public Mono<Mono<String>> returnNestedMockStringMono() {
        return Mono.just(Mono.just("Nested Mono String"));
    }
}
