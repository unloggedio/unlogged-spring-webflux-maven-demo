package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.client.InventoryClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.OrchestrationRequestContext;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.Status;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class InventoryOrchestrator extends Orchestrator{

    @Autowired
    private InventoryClient client;

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
        return this.client.deduct(ctx.getInventoryRequest())
                .doOnNext(ctx::setInventoryResponse)
                .thenReturn(ctx);
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return ctx -> Status.SUCCESS.equals(ctx.getInventoryResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return ctx -> Mono.just(ctx)
                .filter(isSuccess())
                .map(OrchestrationRequestContext::getInventoryRequest)
                .flatMap(this.client::restore)
                .subscribe();
    }
}
