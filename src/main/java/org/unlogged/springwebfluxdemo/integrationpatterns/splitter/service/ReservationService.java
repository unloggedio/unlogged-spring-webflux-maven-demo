package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.service;

import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationItemRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationItemResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ReservationType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final Map<ReservationType, ReservationHandler> map;

    public ReservationService(List<ReservationHandler> list){
        this.map = list.stream().collect(Collectors.toMap(
                ReservationHandler::getType,
                Function.identity()
        ));
    }

    public Mono<ReservationResponse> reserve(Flux<ReservationItemRequest> flux){
        return flux.groupBy(ReservationItemRequest::getType) // splitter
                .flatMap(this::aggregator)
                .collectList()
                .map(this::toResponse);
    }

    private Flux<ReservationItemResponse> aggregator(GroupedFlux<ReservationType, ReservationItemRequest> groupedFlux){
        var key = groupedFlux.key();
        var handler = map.get(key);
        return handler.reserve(groupedFlux);
    }

    private ReservationResponse toResponse(List<ReservationItemResponse> list){
        return ReservationResponse.create(
                UUID.randomUUID(),
                list.stream().mapToInt(ReservationItemResponse::getPrice).sum(),
                list
        );
    }

}
