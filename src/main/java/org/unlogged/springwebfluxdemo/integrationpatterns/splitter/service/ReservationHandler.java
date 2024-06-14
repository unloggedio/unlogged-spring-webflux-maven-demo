package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.service;

import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationItemRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationItemResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationType;
import reactor.core.publisher.Flux;

public abstract class ReservationHandler {

    protected abstract ReservationType getType();
    protected abstract Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux);

}