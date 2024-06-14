package org.unlogged.springwebfluxdemo.resilientPatterns.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.Review;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewClient {

    private final WebClient client;

    public ReviewClient(@Value("${resilient.review.service}") String baseUrl){
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @CircuitBreaker(name = "review-service", fallbackMethod = "fallBackReview")
    public Mono<List<Review>> getReviews(Integer id){
        return this.client
                .get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.empty())
                .bodyToFlux(Review.class)
                .collectList()
                .retry(5)
                .timeout(Duration.ofMillis(500));
//                .onErrorReturn(Collections.emptyList()); //removing because we want the cb to see the error
    }

    public Mono<List<Review>> fallBackReview(Integer id, Throwable ex){
        System.out.println("Fallback called: "+ ex.getMessage());
        Review fallbackReview = new Review(id, "FallBack User", 0, "FallBack comment");
        return Mono.just(Arrays.asList(fallbackReview));
    }

    /**
     * 2 requests will go and take 500 ms because of timeout,
     * then 10 requests will directly fallback due to cb and take 5-20 ms
     * */

}