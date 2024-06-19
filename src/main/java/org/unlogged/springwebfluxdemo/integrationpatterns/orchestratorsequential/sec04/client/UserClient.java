package org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.PaymentRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.PaymentResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.Status;
import reactor.core.publisher.Mono;

@Service("orchestratorSeqUserClient")
public class UserClient {

    private static final String DEDUCT = "deduct";
    private static final String REFUND = "refund";
    private final WebClient client;

    public UserClient(@Value("${sec04.user.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    public Mono<PaymentResponse> deduct(PaymentRequest request){
        return this.callUserService(DEDUCT, request);
    }

    public Mono<PaymentResponse> refund(PaymentRequest request){
        return this.callUserService(REFUND, request);
    }

    private Mono<PaymentResponse> callUserService(String endPoint, PaymentRequest request){
        return this.client
                .post()
                .uri(endPoint)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .onErrorReturn(this.buildErrorResponse(request));
    }

    private PaymentResponse buildErrorResponse(PaymentRequest request){
        return PaymentResponse.create(
                null,
                request.getUserId(),
                null,
                request.getAmount(),
                Status.FAILED
        );
    }

}
