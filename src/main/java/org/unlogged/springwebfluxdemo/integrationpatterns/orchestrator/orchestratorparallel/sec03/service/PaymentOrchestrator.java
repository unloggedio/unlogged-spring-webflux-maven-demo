package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.client.UserClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.dto.OrchestrationRequestContext;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.dto.Status;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class PaymentOrchestrator extends Orchestrator{

    private final UserClient client;

    @Autowired
    public PaymentOrchestrator(@Qualifier("orchestratorUserClient") UserClient client) {
        this.client = client;
    }

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
        return this.client.deduct(ctx.getPaymentRequest())
                .doOnNext(ctx::setPaymentResponse)
                .thenReturn(ctx);
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return ctx -> Status.SUCCESS.equals(ctx.getPaymentResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return ctx -> Mono.just(ctx)
                .filter(isSuccess())
                .map(OrchestrationRequestContext::getPaymentRequest)
                .flatMap(this.client::refund)
                .subscribe();
    }

}
