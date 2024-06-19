package org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.Status;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.ShippingRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.ShippingResponse;
import reactor.core.publisher.Mono;

@Service("orchestratorSeqShippingClient")
public class ShippingClient {

    private static final String SCHEDULE = "schedule";
    private static final String CANCEL = "cancel";
    private final WebClient client;

    public ShippingClient(@Value("${sec04.shipping.service}") String baseUrl){
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
                null,
                request.getQuantity(),
                Status.FAILED,
                null,
                null
        );
    }

}
