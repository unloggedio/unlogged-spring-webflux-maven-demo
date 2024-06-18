package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.externalservices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ConcertReservationRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.ConcertReservationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@RestController
public class MockConcertController {

    private final Random random = new Random();

    @PostMapping("integration/splitter/concert/reserve")
    public Flux<ConcertReservationResponse> reserve(@RequestBody Flux<ConcertReservationRequest> requestFlux) {
        return requestFlux.flatMap(request -> {
            int randomPrice = calculateRandomPrice(request.getSlot());
            ConcertReservationResponse response = ConcertReservationResponse.create(
                    UUID.randomUUID(),
                    request.getCity(),
                    request.getSlot(),
                    request.getCategory(),
                    randomPrice
            );
            return Mono.just(response);
        });
    }

    private int calculateRandomPrice(LocalDateTime slot) {
        // Add randomness
        int basePrice = 100;
        int variation = random.nextInt(50);
        return basePrice + variation;
    }
}