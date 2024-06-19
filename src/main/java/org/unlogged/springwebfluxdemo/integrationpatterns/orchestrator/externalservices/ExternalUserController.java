package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.externalservices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.dto.PaymentRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.dto.PaymentResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorparallel.sec03.dto.Status;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("integration/orchestration/parallel/user")
public class ExternalUserController {

    @PostMapping("deduct")
    public Mono<ResponseEntity<PaymentResponse>> deduct(@RequestBody PaymentRequest request) {
        return Mono.just(ResponseEntity.ok(PaymentResponse.create(
                request.getUserId(),
                null,
                request.getAmount(),
                Status.SUCCESS
        )));
    }

    @PostMapping("refund")
    public Mono<ResponseEntity<PaymentResponse>> refund(@RequestBody PaymentRequest request) {
        return Mono.just(ResponseEntity.ok(PaymentResponse.create(
                request.getUserId(),
                null,
                request.getAmount(),
                Status.SUCCESS
        )));
    }
}

