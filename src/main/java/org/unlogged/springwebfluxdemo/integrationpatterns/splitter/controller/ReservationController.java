package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationItemRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.service.ReservationService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("integration/splitter")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("reserve")
    public Mono<ReservationResponse> reserve(@RequestBody Flux<ReservationItemRequest> flux){
        return this.service.reserve(flux);
    }

}