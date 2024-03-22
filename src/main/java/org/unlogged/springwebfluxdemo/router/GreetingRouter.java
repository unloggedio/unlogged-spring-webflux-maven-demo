package org.unlogged.springwebfluxdemo.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.unlogged.springwebfluxdemo.filter.ExampleHandlerFilterFunction;
import org.unlogged.springwebfluxdemo.handler.GreetingHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)),
                        greetingHandler::hello)
                .andRoute(GET("/e").and(accept(MediaType.APPLICATION_JSON)),
                        greetingHandler::e)
                .andRoute(GET("/typeWrapped").and(accept(MediaType.APPLICATION_JSON)),
                        greetingHandler::getTypeWrapped)
                .andRoute(GET("/typeWrapped/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        greetingHandler::getTypeWrappedPV)
                .filter(new ExampleHandlerFilterFunction())
                .andRoute(GET("/someBean").and(accept(MediaType.APPLICATION_JSON)),
                        greetingHandler::getSomeBean)
                .andRoute(GET("/listOfStrings").and(accept(MediaType.APPLICATION_JSON)),
                        greetingHandler::getListOfStrings)
                .andRoute(GET("/genericTypeWrapped").and(accept(MediaType.APPLICATION_JSON)),
                        greetingHandler::getGenericTypeWrapper);
    }

}