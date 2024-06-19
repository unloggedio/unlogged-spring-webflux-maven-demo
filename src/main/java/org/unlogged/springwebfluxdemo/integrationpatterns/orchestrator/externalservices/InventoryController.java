package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.externalservices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.InventoryRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.InventoryResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.Status;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("integration/orchestration/parallel/inventory")
public class InventoryController {

    @PostMapping("deduct")
    public Mono<ResponseEntity<InventoryResponse>> deduct(@RequestBody InventoryRequest request) {
        return Mono.just(ResponseEntity.ok(InventoryResponse.create(
                request.getProductId(),
                request.getQuantity(),
                null,
                Status.SUCCESS
        )));
    }

    @PostMapping("restore")
    public Mono<ResponseEntity<InventoryResponse>> restore(@RequestBody InventoryRequest request) {
        return Mono.just(ResponseEntity.ok(InventoryResponse.create(
                request.getProductId(),
                request.getQuantity(),
                null,
                Status.SUCCESS
        )));
    }
}
