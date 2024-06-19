package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.client.ShippingClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.OrchestrationRequestContext;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.Status;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class ShippingOrchestrator extends Orchestrator{

    @Autowired
    private ShippingClient client;

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
        return this.client.schedule(ctx.getShippingRequest())
                .doOnNext(ctx::setShippingResponse)
                .thenReturn(ctx);
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return ctx -> Status.SUCCESS.equals(ctx.getShippingResponse().getStatus());
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
