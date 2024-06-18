package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Supplier;

@RestController
@RequestMapping("/optional")
public class OptionalController {

    @GetMapping("/empty")
    public Mono<Optional<String>> empty() {
        return Mono.just(Optional.empty());
    }

    @GetMapping("/of/{value}")
    public Mono<Optional<String>> of(@PathVariable String value) {
        return Mono.just(Optional.of(value));
    }

    @GetMapping("/ofNullable/{value}")
    public Mono<Optional<String>> ofNullable(@PathVariable(required = false) String value) {
        return Mono.just(Optional.ofNullable(value));
    }

    @GetMapping("/get/{value}")
    public Mono<String> get(@PathVariable String value) {
        Optional<String> optional = Optional.of(value);
        return Mono.just(optional.get());
    }

    @GetMapping("/isPresent/{value}")
    public Mono<Boolean> isPresent(@PathVariable String value) {
        Optional<String> optional = Optional.of(value);
        return Mono.just(optional.isPresent());
    }
    @GetMapping("/isPresent1")
    public Mono<Boolean> isPresent1() {
        Optional<String> optional = Optional.empty();
        return Mono.just(optional.isPresent());
    }

    @GetMapping("/ifPresent/{value}")
    public Mono<String> ifPresent(@PathVariable String value) {
        Optional<String> optional = Optional.of(value);
        StringBuilder result = new StringBuilder();
        optional.ifPresent(result::append);
        return Mono.just(result.toString());
    }

    @GetMapping("/filter/{value}/{length}")
    public Mono<Optional<String>> filter(@PathVariable String value, @PathVariable int length) {
        Optional<String> optional = Optional.of(value);
        return Mono.just(optional.filter(v -> v.length() > length));
    }

    @GetMapping("/map/{value}")
    public Mono<Optional<Integer>> map(@PathVariable String value) {
        Optional<String> optional = Optional.of(value);
        return Mono.just(optional.map(String::length));
    }

    @GetMapping("/flatMap/{value}")
    public Mono<Optional<Integer>> flatMap(@PathVariable String value) {
        Optional<String> optional = Optional.of(value);
        return Mono.just(optional.flatMap(v -> Optional.of(v.length())));
    }

    @GetMapping("/orElse/{defaultValue}")
    public Mono<String> orElse(@PathVariable String defaultValue) {
        Optional<String> optional = Optional.ofNullable(null);
        return Mono.just(optional.orElse(defaultValue));
    }

    @GetMapping("/orElseGet/{defaultValue}")
    public Mono<String> orElseGet(@PathVariable String defaultValue) {
        Optional<String> optional = Optional.ofNullable(null);
        Supplier<String> supplier = () -> defaultValue;
        return Mono.just(optional.orElseGet(supplier));
    }

    @GetMapping("/orElseThrow")
    public Mono<String> orElseThrow() {
        Optional<String> optional = Optional.ofNullable(null);
        Supplier<RuntimeException> exceptionSupplier = () -> new RuntimeException("Value not present");
        return Mono.just(optional.orElseThrow(exceptionSupplier));
    }

    @GetMapping("/equals/{value1}/{value2}")
    public Mono<Boolean> equals(@PathVariable String value1, @PathVariable String value2) {
        Optional<String> optional1 = Optional.ofNullable(value1);
        Optional<String> optional2 = Optional.ofNullable(value2);
        return Mono.just(optional1.equals(optional2));
    }

    @GetMapping("/hashCode/{value}")
    public Mono<Integer> hashCode(@PathVariable String value) {
        Optional<String> optional = Optional.ofNullable(value);
        return Mono.just(optional.hashCode());
    }

    @GetMapping("/toString/{value}")
    public Mono<String> toString(@PathVariable String value) {
        Optional<String> optional = Optional.ofNullable(value);
        return Mono.just(optional.toString());
    }
}
