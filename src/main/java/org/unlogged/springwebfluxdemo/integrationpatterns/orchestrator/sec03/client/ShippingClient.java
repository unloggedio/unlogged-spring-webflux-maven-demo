package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.ShippingRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.ShippingResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.sec03.dto.Status;
import reactor.core.publisher.Mono;

@Service
public class ShippingClient {

    private static final String SCHEDULE = "schedule";
    private static final String CANCEL = "cancel";
    private final WebClient client;

    public ShippingClient(@Value("${sec03.shipping.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    public Mono<ShippingResponse> schedule(ShippingRequest request){
        return this.callShippingService(SCHEDULE, request);
    }

    public Mono<ShippingResponse> cancel(ShippingRequest request){
        return this.callShippingService(CANCEL, request);
    }

    private Mono<ShippingResponse> callShippingService(String endPoint, ShippingRequest request){
        return this.client
                .post()
                .uri(endPoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ShippingResponse.class)
                .onErrorReturn(this.buildErrorResponse(request));
    }

    private ShippingResponse buildErrorResponse(ShippingRequest request){
        return ShippingResponse.create(
                request.getOrderId(),
                request.getQuantity(),
                Status.FAILED,
                null,
                null
        );
    }

}
