package org.unlogged.springwebfluxdemo.integrationpatterns.splitter.externalservices;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.TheatreReservationRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.splitter.dto.TheatreReservationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@RestController
public class MockTheatreController {

    private final Random random = new Random();

    @PostMapping("integration/splitter/theatre/reserve")
    public Flux<TheatreReservationResponse> reserve(@RequestBody Flux<TheatreReservationRequest> requestFlux) {
        return requestFlux.flatMap(request -> {
            int randomPrice = calculateRandomPrice(request.getDateTime());
            TheatreReservationResponse response = TheatreReservationResponse.create(
                    UUID.randomUUID(),
                    request.getCity(),
                    request.getDateTime(),
                    request.getCategory(),
                    randomPrice
            );
            return Mono.just(response);
        });
    }

    private int calculateRandomPrice(LocalDateTime dateTime) {
        // Add randomness
        int basePrice = 50;
        int variation = random.nextInt(30);
        return basePrice + variation;
    }
}
