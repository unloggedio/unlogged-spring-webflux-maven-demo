package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stringmethods")
public class StringMethodsJava11Controller {

    @GetMapping("/isBlank")
    public Mono<Boolean> isBlank(@RequestParam String input) {
        return Mono.just(input.isBlank());
    }

    @GetMapping("/lines")
    public Mono<String> lines(@RequestBody String input) {
        return Mono.just(String.join(",", input.lines().toArray(String[]::new)));
    }

    @GetMapping("/strip")
    public Mono<String> strip(@RequestBody String input) {
        return Mono.just(input.strip());
    }

    @GetMapping("/stripLeading")
    public Mono<String> stripLeading(@RequestBody String input) {
        return Mono.just(input.stripLeading());
    }

    @GetMapping("/stripTrailing")
    public Mono<String> stripTrailing(@RequestBody String input) {
        return Mono.just(input.stripTrailing());
    }

    @GetMapping("/repeat1")
    public Mono<String> repeat1(@RequestBody String input, int count) {
        return Mono.just(input.repeat(count));
    }
}
