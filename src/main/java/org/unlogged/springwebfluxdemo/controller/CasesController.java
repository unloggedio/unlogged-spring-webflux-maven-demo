package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.time.Duration;
import java.util.function.Function;

import static java.rmi.server.LogStream.log;

@RestController
@RequestMapping("/cases")
public class CasesController {

//    Mono<Boolean> notifyShop(String productName) {
//        log("Notifying shop about: " + productName);
//        return makeRequest(productName) // <1>
//                .contextWrite(Function.identity()) // <2>
//                .doOnNext(r -> log("Request done.")); // <3>
//    }

    Mono<Void> handleRequest() {
        log("Assembling the chain");

        return Mono.just("test-product")
                .delayElement(Duration.ofMillis(1))
                .flatMap(product ->
                        Flux.concat(
                                notifyShopV1(product)).then());
    }

    Mono<Void> addProductV1(String productName) {
        log("Adding product: " + productName);
        return Mono.empty();
    }


    Mono<Void> handleRequestV() {
        return Mono.just("test-product")
                .delayElement(Duration.ofMillis(1)) // <3>
                .flatMap(product ->
                        Flux.concat(
                                notifyShopV1(product)).then());
    }

    Mono<Boolean> notifyShopV1(String productName) {
        log("Notifying shop about: " + productName);
        return Mono.just(true);
    }


    @RequestMapping("/ctx/v1")
    public Context getContextIsolated() {
        return updateContextWithQuoteHeaderV1(makeContext());
    }

    public Context updateContextWithQuoteHeaderV1(Context context) {
        Context finalContext = context.put("quote_header", "custom");
        return finalContext;
    }


    @RequestMapping("/ctx/v1/make")
    public Context makeContext() {
        return Context.of("e", "e");
    }
}
