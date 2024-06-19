package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.OrderRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.OrderResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.service.OrchestratorService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("integration/orchestrator/parallel")
public class OrderController {

    @Autowired
    private OrchestratorService service;

    @PostMapping("order")
    public Mono<ResponseEntity<OrderResponse>> placeOrder(@RequestBody Mono<OrderRequest> mono){
        return this.service.placeOrder(mono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
