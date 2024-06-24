package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.client.ConcertClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.*;
import reactor.core.publisher.Flux;

@Service
public class ConcertReservationHandler extends ReservationHandler{

    @Autowired
    private ConcertClient client;

    @Override
    protected ReservationType getType() {
        return ReservationType.CONCERT;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.map(this::toConcertRequest)
                .transform(this.client::reserve)
                .map(this::toResponse);
    }

    private ConcertReservationRequest toConcertRequest(ReservationItemRequest request){
        return ConcertReservationRequest.create(
                request.getCity(),
                request.getDateTime(),
                request.getCategory()
        );
    }

    private ReservationItemResponse toResponse(ConcertReservationResponse response){
        return ReservationItemResponse.create(
                response.getReservationId(),
                this.getType(),
                response.getCategory(),
                response.getCity(),
                response.getSlot(),
                response.getPrice()
        );
    }
}