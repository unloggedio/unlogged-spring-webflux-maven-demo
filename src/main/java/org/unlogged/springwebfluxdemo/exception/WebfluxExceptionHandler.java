package org.unlogged.springwebfluxdemo.exception;

import jakarta.annotation.Nonnull;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.all;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class WebfluxExceptionHandler extends DefaultErrorWebExceptionHandler {
    public WebfluxExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources, ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
    }

    protected RouterFunction<ServerResponse> getRoutingFunction() {
        return route(all(), (HandlerFunction<ServerResponse>) this::getServerResponse);
    }

    private Mono<ServerResponse> getServerResponse(ServerRequest serverRequest) {
        return (Mono<ServerResponse>) ServerResponse.status(200)
                .contentType(MediaType.APPLICATION_JSON);
    }
}
