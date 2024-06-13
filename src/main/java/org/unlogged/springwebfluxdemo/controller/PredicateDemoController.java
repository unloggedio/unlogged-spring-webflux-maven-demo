package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
public class PredicateDemoController {

    private final List<String> sampleList = List.of("apple", "banana", "cherry", "date", "elderberry");

    @GetMapping("/isEqual")
    public Mono<List<String>> isEqual(@RequestParam String target) {
        Predicate<String> isEqual = Predicate.isEqual(target);

        List<String> result = sampleList.stream()
                .filter(isEqual)
                .collect(Collectors.toList());

        return Mono.just(result);
    }

    @GetMapping("/and")
    public Mono<List<String>> and(@RequestParam String target) {
        Predicate<String> isEqual = Predicate.isEqual(target);
        Predicate<String> lengthGreaterThanFive = s -> s.length() > 5;

        Predicate<String> combinedPredicate = isEqual.and(lengthGreaterThanFive);

        List<String> result = sampleList.stream()
                .filter(combinedPredicate)
                .collect(Collectors.toList());

        return Mono.just(result);
    }

    @GetMapping("/or")
    public Mono<List<String>> or(@RequestParam String target) {
        Predicate<String> isEqual = Predicate.isEqual(target);
        Predicate<String> lengthGreaterThanFive = s -> s.length() > 5;

        Predicate<String> combinedPredicate = isEqual.or(lengthGreaterThanFive);

        List<String> result = sampleList.stream()
                .filter(combinedPredicate)
                .collect(Collectors.toList());

        return Mono.just(result);
    }

    @GetMapping("/negate")
    public Mono<List<String>> negate(@RequestParam String target) {
        Predicate<String> isEqual = Predicate.isEqual(target);
        Predicate<String> negatePredicate = isEqual.negate();

        List<String> result = sampleList.stream()
                .filter(negatePredicate)
                .collect(Collectors.toList());

        return Mono.just(result);
    }

    @GetMapping("/not")
    public Mono<List<String>> not(@RequestParam String target) {
        Predicate<String> isEqual = Predicate.isEqual(target);
        Predicate<String> notPredicate = Predicate.not(isEqual);

        List<String> result = sampleList.stream()
                .filter(notPredicate)
                .collect(Collectors.toList());

        return Mono.just(result);
    }
}

