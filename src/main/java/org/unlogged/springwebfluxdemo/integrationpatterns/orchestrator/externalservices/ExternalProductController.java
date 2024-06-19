package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.externalservices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.Product;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("integration/orchestration/parallel/product")
public class ExternalProductController {

    @GetMapping("{id}")
    public Mono<ResponseEntity<Product>> getProduct(@PathVariable Integer id) {
        // Simulate fetching product details
        Product product = Product.create(id, "Electronics", "Sample Product Description", 100);
        return Mono.just(ResponseEntity.ok(product));
    }
}
