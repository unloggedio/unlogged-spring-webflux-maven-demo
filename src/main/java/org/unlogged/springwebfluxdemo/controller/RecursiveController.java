package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.service.RecursiveService;
import reactor.core.publisher.Mono;

@RestController
public class RecursiveController {
    @RequestMapping("/factorial/{number}")
    public Mono<Integer> factorial(@PathVariable Integer number) {
        return RecursiveService.factorial(number);
    }
}
