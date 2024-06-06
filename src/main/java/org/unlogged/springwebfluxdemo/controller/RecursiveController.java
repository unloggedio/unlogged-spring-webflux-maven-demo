package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.service.RecursiveService;
import reactor.core.publisher.Mono;

@RestController
public class RecursiveController {

    private final RecursiveService recursiveService;

    @Autowired
    public  RecursiveController(RecursiveService recursiveService) {
        this.recursiveService = recursiveService;
    }

    @RequestMapping("/factorial/{number}")
    public Mono<Integer> factorial(@PathVariable Integer number) {
        return recursiveService.factorial(number);
    }

    @GetMapping("/fibonacci/{n}")
    public Mono<Long> nthFibonacci(@PathVariable int n) {
        return recursiveService.nthFibonacci(n);
    }
}
