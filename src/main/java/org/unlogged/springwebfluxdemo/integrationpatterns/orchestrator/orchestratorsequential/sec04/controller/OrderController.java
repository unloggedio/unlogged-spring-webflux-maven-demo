package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.service.OrchestratorService;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.OrderRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.OrderResponse;
import reactor.core.publisher.Mono;

@RestController("OrchestratorSeqController")
@RequestMapping("integration/orchestrator/sequential")
public class OrderController {

    @Autowired
    @Qualifier("orchestratorSeqService")
    private OrchestratorService service;

    @PostMapping("order")
    public Mono<ResponseEntity<OrderResponse>> placeOrder(@RequestBody Mono<OrderRequest> mono){
        return this.service.placeOrder(mono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
