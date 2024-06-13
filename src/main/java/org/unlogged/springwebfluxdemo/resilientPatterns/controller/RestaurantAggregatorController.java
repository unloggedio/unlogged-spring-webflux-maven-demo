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
