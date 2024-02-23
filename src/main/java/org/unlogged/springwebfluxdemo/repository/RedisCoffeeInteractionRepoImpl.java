package org.unlogged.springwebfluxdemo.repository;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.model.Coffee;
import reactor.core.publisher.Flux;

@Repository
@Component
public class RedisCoffeeInteractionRepoImpl implements RedisCoffeeInteractionRepo {

    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    public RedisCoffeeInteractionRepoImpl(ReactiveRedisOperations<String, Coffee> coffeeOps) {
        this.coffeeOps = coffeeOps;
    }

    public Flux<Coffee> all() {
        return coffeeOps.keys("*")
                .flatMap(coffeeOps.opsForValue()::get);
    }
}
