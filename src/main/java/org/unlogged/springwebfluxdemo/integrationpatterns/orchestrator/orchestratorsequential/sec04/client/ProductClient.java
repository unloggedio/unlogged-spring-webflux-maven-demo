package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.Product;
import reactor.core.publisher.Mono;

@Service("orchestratorSeqProductClient")
public class ProductClient {

    private final WebClient client;

    public ProductClient(@Value("${sec04.product.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    public Mono<Product> getProduct(Integer id){
        return this.client
                .get()
                .uri("{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(ex -> Mono.empty());
    }

}
