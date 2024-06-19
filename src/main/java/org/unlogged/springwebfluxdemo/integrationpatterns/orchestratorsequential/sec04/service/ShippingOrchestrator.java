package org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.client.ShippingClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.OrchestrationRequestContext;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.Status;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service("orchestratorSeqShippingOrchService")
public class ShippingOrchestrator extends Orchestrator {

    @Autowired
    private ShippingClient client;

    @Autowired
    public ShippingOrchestrator(@Qualifier("orchestratorSeqShippingClient") ShippingClient client) {
        this.client = client;
    }

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
        return this.client.schedule(ctx.getShippingRequest())
                .doOnNext(ctx::setShippingResponse)
                .thenReturn(ctx)
                .handle(this.statusHandler());
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return ctx -> Objects.nonNull(ctx.getShippingResponse()) && Status.SUCCESS.equals(ctx.getShippingResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return ctx -> Mono.just(ctx)
                .filter(isSuccess())
                .map(OrchestrationRequestContext::getShippingRequest)
                .flatMap(this.client::cancel)
                .subscribe();
    }
}
