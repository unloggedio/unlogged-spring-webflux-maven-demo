package org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.client.InventoryClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.OrchestrationRequestContext;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.Status;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service("orchestratorSeqInventoryService")
public class InventoryOrchestrator extends Orchestrator {

    private InventoryClient client;

    @Autowired
    public InventoryOrchestrator(@Qualifier("orchestratorSeqInventoryClient") InventoryClient client) {
        this.client = client;
    }

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
        return this.client.deduct(ctx.getInventoryRequest())
                .doOnNext(ctx::setInventoryResponse)
                .thenReturn(ctx)
                .handle(this.statusHandler());
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return ctx -> Objects.nonNull(ctx.getInventoryResponse()) && Status.SUCCESS.equals(ctx.getInventoryResponse().getStatus());
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
