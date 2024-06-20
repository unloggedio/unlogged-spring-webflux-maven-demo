package org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.util;

import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.InventoryRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.OrchestrationRequestContext;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.PaymentRequest;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestrator.orchestratorsequential.sec04.dto.ShippingRequest;

public class OrchestrationUtil {

    public static void buildPaymentRequest(OrchestrationRequestContext ctx){
        var paymentRequest = PaymentRequest.create(
                ctx.getOrderRequest().getUserId(),
                ctx.getProductPrice() * ctx.getOrderRequest().getQuantity(),
                ctx.getOrderId()
        );
        ctx.setPaymentRequest(paymentRequest);
    }

    public static void buildInventoryRequest(OrchestrationRequestContext ctx){
        var inventoryRequest = InventoryRequest.create(
            ctx.getPaymentResponse().getPaymentId(),
            ctx.getOrderRequest().getProductId(),
            ctx.getOrderRequest().getQuantity()
        );
        ctx.setInventoryRequest(inventoryRequest);
    }

    public static void buildShippingRequest(OrchestrationRequestContext ctx){
        var shippingRequest = ShippingRequest.create(
                ctx.getOrderRequest().getQuantity(),
                ctx.getOrderRequest().getUserId(),
                ctx.getInventoryResponse().getInventoryId()
        );
        ctx.setShippingRequest(shippingRequest);
    }

}
