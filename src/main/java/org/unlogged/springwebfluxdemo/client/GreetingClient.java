package org.unlogged.springwebfluxdemo.client;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.unlogged.springwebfluxdemo.model.GenericTypeWrapper;
import org.unlogged.springwebfluxdemo.model.Greeting;
import org.unlogged.springwebfluxdemo.model.TypeWrapper;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Component
public class GreetingClient {

    private final WebClient client;

    private ClientHttpConnector connector() {
        return new
                ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection()));
    }

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public GreetingClient(WebClient.Builder builder) {

        this.client =
                builder
                        .clientConnector(connector())
                        .baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getMessage() {
        return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }

    public Mono<String> getE() {
        return this.client.get().uri("/e").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }

    public Mono<TypeWrapper> getTypeWrappedObject() {
        return this.client.get().uri("/typeWrapped").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getTypeWrapper);
    }

    public Mono<TypeWrapper> getTypeWrappedObjectPV(String id) {
        return this.client.get().uri("/typeWrapped/" + id).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getTypeWrapper);
    }

    public Mono<Bean> getRandomBean() {
        return this.client.get().uri("/someBean").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getSomeBean)
                .filter(Objects::isNull);
    }


    public Mono<List<String>> getListofMonoStrings() {
        return this.client.get().uri("/listOfStrings").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getListOfStrings)
                .tag("name", "somename");
    }

    public Mono<GenericTypeWrapper<String>> getGenericTypeWrapper() {
        return this.client.get().uri("/genericTypeWrapped").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::genericTypeWrapperString);
    }


    //make a call to enrich a response on demand outside of webclient call
}