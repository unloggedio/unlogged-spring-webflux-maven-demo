package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.IntFunction;

@RestController
@RequestMapping("/collectionstoarray")
public class ToArrayController {

    @GetMapping("/default-toArray")
    public Mono<Object[]> getDefaultToArray() {
        List<String> list = List.of("Apple", "Banana", "Cherry");
        Flux<String> flux = Flux.fromIterable(list);

        return flux.collectList().map(List::toArray);
    }

    @GetMapping("/generator-toArray")
    public Mono<String[]> getGeneratorToArray() {
        List<String> list = List.of("Dog", "Elephant", "Frog");
        Flux<String> flux = Flux.fromIterable(list);

        IntFunction<String[]> generator = String[]::new;

        return flux.collectList().map(lst -> lst.toArray(generator));
    }

    @GetMapping("/array-toArray")
    public Mono<String[]> getArrayToArray() {
        List<String> list = List.of("Giraffe", "Hippo", "Iguana");
        Flux<String> flux = Flux.fromIterable(list);

        String[] array = new String[list.size()];

        return flux.collectList().map(lst -> lst.toArray(array));
    }
}
