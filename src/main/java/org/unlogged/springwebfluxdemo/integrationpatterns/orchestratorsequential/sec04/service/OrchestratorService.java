package org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.OrchestrationRequestContext;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.OrderRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.OrderResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.Status;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.util.DebugUtil;
import reactor.core.publisher.Mono;

@Service("orchestratorSeqService")
public class OrchestratorService {

    @Autowired
    @Qualifier("orchestratorSeqOrderFulfillmentService")
    private OrderFulfillmentService fulfillmentService;

    @Autowired
    @Qualifier("orchestratorSeqOrderCancelService")
    private OrderCancellationService cancellationService;

    public Mono<OrderResponse> placeOrder(Mono<OrderRequest> mono){
        return mono
                .map(OrchestrationRequestContext::new)
                .flatMap(fulfillmentService::placeOrder)
                .doOnNext(this::doOrderPostProcessing)
                .doOnNext(DebugUtil::print) // just for debugging
                .map(this::toOrderResponse);
    }

    private void doOrderPostProcessing(OrchestrationRequestContext ctx){
        if(Status.FAILED.equals(ctx.getStatus()))
            this.cancellationService.cancelOrder(ctx);
    }

    private OrderResponse toOrderResponse(OrchestrationRequestContext ctx){
        var isSuccess = Status.SUCCESS.equals(ctx.getStatus());
        var address = isSuccess ? ctx.getShippingResponse().getAddress() : null;
        var deliveryDate = isSuccess ? ctx.getShippingResponse().getExpectedDelivery() : null;
        return OrderResponse.create(
            ctx.getOrderRequest().getUserId(),
            ctx.getOrderRequest().getProductId(),
            ctx.getOrderId(),
            ctx.getStatus(),
            address,
            deliveryDate
        );
    }

}
