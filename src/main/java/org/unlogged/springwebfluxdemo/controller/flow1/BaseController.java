package org.unlogged.springwebfluxdemo.controller.flow1;

import org.unlogged.springwebfluxdemo.service.BaseService;
import reactor.core.publisher.Mono;

public abstract class BaseController<S> {
    protected S service;

    public Mono<String> getServiceClassName() {
        return Mono.just(service.getClass().getName());
    }
}

