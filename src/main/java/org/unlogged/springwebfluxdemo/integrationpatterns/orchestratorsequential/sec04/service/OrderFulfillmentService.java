package org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.client.ProductClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.OrchestrationRequestContext;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.Status;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.dto.Product;
import org.unlogged.springwebfluxdemo.integrationpatterns.orchestratorsequential.sec04.util.OrchestrationUtil;
import reactor.core.publisher.Mono;

@Service("orchestratorSeqOrderFulfillmentService")
public class OrderFulfillmentService {

    @Autowired
    @Qualifier("orchestratorSeqProductClient")
    private ProductClient productClient;

    @Autowired
    @Qualifier("orchestratorSeqPaymentOrchService")
    private PaymentOrchestrator paymentOrchestrator;

    @Autowired
    @Qualifier("orchestratorSeqInventoryService")
    private InventoryOrchestrator inventoryOrchestrator;

    @Autowired
    @Qualifier("orchestratorSeqShippingOrchService")
    private ShippingOrchestrator shippingOrchestrator;

    public Mono<OrchestrationRequestContext> placeOrder(OrchestrationRequestContext ctx){
        return this.getProduct(ctx)
                .doOnNext(OrchestrationUtil::buildPaymentRequest)
                .flatMap(this.paymentOrchestrator::create)
                .doOnNext(OrchestrationUtil::buildInventoryRequest)
                .flatMap(this.inventoryOrchestrator::create)
                .doOnNext(OrchestrationUtil::buildShippingRequest)
                .flatMap(this.shippingOrchestrator::create)
                .doOnNext(c -> c.setStatus(Status.SUCCESS))
                .doOnError(ex -> ctx.setStatus(Status.FAILED))
                .onErrorReturn(ctx);
    }

    private Mono<OrchestrationRequestContext> getProduct(OrchestrationRequestContext ctx){
        return this.productClient.getProduct(ctx.getOrderRequest().getProductId())
                                 .map(Product::getPrice)
                                 .doOnNext(ctx::setProductPrice)
                                 .map(i -> ctx);
    }

}
