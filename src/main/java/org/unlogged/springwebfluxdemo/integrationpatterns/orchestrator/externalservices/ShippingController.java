package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.externalservices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.Address;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.ShippingRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.ShippingResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.Status;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("integration/orchestration/parallel/shipping")
public class ShippingController {

    @PostMapping("schedule")
    public Mono<ResponseEntity<ShippingResponse>> schedule(@RequestBody ShippingRequest request) {
        // Simulate scheduling shipping
        ShippingResponse response = ShippingResponse.create(
                UUID.randomUUID(), // Generate a random UUID for orderId
                request.getQuantity(),
                Status.SUCCESS,
                LocalDate.now().plusDays(3).toString(), // Simulate expected delivery in 3 days
                Address.create("123 Main St", "Anytown", "AnyState", "12345")
        );
        return Mono.just(ResponseEntity.ok(response));
    }

    @PostMapping("cancel")
    public Mono<ResponseEntity<ShippingResponse>> cancel(@RequestBody ShippingRequest request) {
        // Simulate canceling shipping
        ShippingResponse response = ShippingResponse.create(
                request.getOrderId(),
                request.getQuantity(),
                Status.SUCCESS,
                null, // No expected delivery date for canceled orders
                Address.create("123 Main St", "Anytown", "AnyState", "12345")
        );
        return Mono.just(ResponseEntity.ok(response));
    }
}
