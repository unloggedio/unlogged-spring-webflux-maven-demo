package org.unlogged.springwebfluxdemo.resilientPatterns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.RestaurantAggregate;
import org.unlogged.springwebfluxdemo.resilientPatterns.service.RestaurantAggregatorService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("resilient")
public class RestaurantAggregatorController {

    @Autowired
    private RestaurantAggregatorService service;

    @GetMapping("restaurantv1/{id}")
    public Mono<ResponseEntity<RestaurantAggregate>> getProductAggregate(@PathVariable Integer id){
        return this.service.aggregate(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}

/**
 * 1 Everything works fine
 * 2 500 milli timeout reviews
 * 3 Retry reviews
 * 4 Not found reviews and 50-60 ms for reviews
 * 5 404 as both will be empty retry with a 100 ms gap. Hence total 500 ms approx
 * 6 Both not found and hence 10-50 ms
 * With id 2 circuit breaker also implemented
 * */
