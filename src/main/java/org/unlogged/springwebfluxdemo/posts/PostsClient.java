package org.unlogged.springwebfluxdemo.posts;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PostsClient {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
    private final HttpClient client;

    public PostsClient() {
        client = HttpClient.newHttpClient();
    }

    public Mono<String> findAll() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        return Mono.fromFuture(responseFuture)
                .map(HttpResponse::body)
                .onErrorMap(IOException.class, e -> new RuntimeException("Failed to fetch posts", e))
                .onErrorMap(InterruptedException.class, e -> new RuntimeException("Request was interrupted", e))
                .onErrorMap(CompletionException.class, Throwable::getCause);
    }

}
