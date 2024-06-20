package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.InventoryRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.InventoryResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.Status;
import reactor.core.publisher.Mono;

@Service("orchestratorSeqInventoryClient")
public class InventoryClient {

    private static final String DEDUCT = "deduct";
    private static final String RESTORE = "restore";
    private final WebClient client;

    public InventoryClient(@Value("${sec04.inventory.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    public Mono<InventoryResponse> deduct(InventoryRequest request){
        return this.callInventoryService(DEDUCT, request);
    }

    public Mono<InventoryResponse> restore(InventoryRequest request){
        return this.callInventoryService(RESTORE, request);
    }

    private Mono<InventoryResponse> callInventoryService(String endPoint, InventoryRequest request){
        return this.client
                .post()
                .uri(endPoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(InventoryResponse.class)
                .onErrorReturn(this.buildErrorResponse(request));
    }

    private InventoryResponse buildErrorResponse(InventoryRequest request){
        return InventoryResponse.create(
                null,
                request.getProductId(),
                request.getQuantity(),
                null,
                Status.FAILED
        );
    }

}
