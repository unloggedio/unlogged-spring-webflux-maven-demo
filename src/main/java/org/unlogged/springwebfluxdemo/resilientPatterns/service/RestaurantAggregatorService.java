package org.unlogged.springwebfluxdemo.resilientPatterns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.resilientPatterns.client.RestaurantClient;
import org.unlogged.springwebfluxdemo.resilientPatterns.client.ReviewClient;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.Restaurant;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.RestaurantAggregate;
import org.unlogged.springwebfluxdemo.resilientPatterns.dto.Review;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RestaurantAggregatorService {

    @Autowired
    RestaurantClient restaurantClient;

    @Autowired
    ReviewClient reviewClient;

    public Mono<RestaurantAggregate> aggregate(Integer id){
        return Mono.zip(
                        this.restaurantClient.getRestaurant(id),
                        this.reviewClient.getReviews(id)
                )
                .map(t -> toDto(t.getT1(), t.getT2()));
    }

    private RestaurantAggregate toDto(Restaurant restaurant, List<Review> reviews){
        return RestaurantAggregate.create(
                restaurant.getId(),
                restaurant.getCuisine(),
                restaurant.getDescription(),
                reviews
        );
    }
}
