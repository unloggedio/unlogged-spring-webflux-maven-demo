package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.dto.OrchestrationRequestContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class OrderCancellationService {

    private Sinks.Many<OrchestrationRequestContext> sink;
    private Flux<OrchestrationRequestContext> flux;

    @Autowired
    private List<Orchestrator> orchestrators;

    @PostConstruct
    public void  init(){
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
        this.flux = this.sink.asFlux().publishOn(Schedulers.boundedElastic());
        orchestrators.forEach(o -> this.flux.subscribe(o.cancel()));
    }

    public void cancelOrder(OrchestrationRequestContext ctx){
        this.sink.tryEmitNext(ctx);
    }

}
