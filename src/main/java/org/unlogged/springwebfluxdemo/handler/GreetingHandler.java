package org.unlogged.springwebfluxdemo.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import org.unlogged.springwebfluxdemo.model.Greeting;
import org.unlogged.springwebfluxdemo.model.TypeWrapper;
import reactor.core.publisher.Mono;

//@EnableReactiveMethodSecurity(useAuthorizationManager=true)
@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Hello, Spring!")));
    }

    public Mono<ServerResponse> e(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("E")));
    }

    public Mono<ServerResponse> getTypeWrapped(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting(new TypeWrapper()).getTypeWrapper()));
    }

    public Mono<ServerResponse> getTypeWrappedPV(ServerRequest serverRequest) {
        TypeWrapper gt = new Greeting(new TypeWrapper()).getTypeWrapper();
        gt.setStringValue(serverRequest.pathVariable("id"));
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(gt));
    }

    public Mono<ServerResponse> getGenericTypeWrapper(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("").genericTypeWrapperString()));
    }

    public Mono<ServerResponse> getSomeBean(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Some bean")));
    }

    public Mono<ServerResponse> getListOfStrings(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Greeting("Some bean").getListOfStrings()));
    }
}