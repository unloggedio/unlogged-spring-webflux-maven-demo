package org.unlogged.springwebfluxdemo.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecursiveService {

    public Mono<Integer> factorial(Integer number) {
        if (number <= 1) {
            return Mono.just(1);
        } else {
            return Mono.just(number)
                    .flatMap(n -> factorial(n - 1))
                    .flatMap(result -> Mono.just(number * result));
        }
    }

    public Mono<Long> nthFibonacci(int n) {
        return fibonacci(n);
    }

    private Mono<Long> fibonacci(int n) {
        if (n <= 1) {
            return Mono.just((long) n);
        } else {
            return Mono.zip(fibonacci(n - 1), fibonacci(n - 2), (a, b) -> a + b);
        }
    }
}
