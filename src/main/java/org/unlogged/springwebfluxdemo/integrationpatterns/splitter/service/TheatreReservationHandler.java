package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.client.TheatreClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.*;
import reactor.core.publisher.Flux;

@Service
public class TheatreReservationHandler extends ReservationHandler{

    @Autowired
    private TheatreClient client;

    @Override
    protected ReservationType getType() {
        return ReservationType.THEATRE;
    }

    @Override
    protected Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux) {
        return flux.map(this::toTheatreRequest)
                .transform(this.client::reserve)
                .map(this::toResponse);
    }

    private TheatreReservationRequest toTheatreRequest(ReservationItemRequest request){
        return TheatreReservationRequest.create(
                request.getCity(),
                request.getDateTime(),
                request.getCategory()
        );
    }

    private ReservationItemResponse toResponse(TheatreReservationResponse response){
        return ReservationItemResponse.create(
                response.getReservationId(),
                this.getType(),
                response.getCategory(),
                response.getCity(),
                response.getDateTime(),
                response.getPrice()
        );
    }

}