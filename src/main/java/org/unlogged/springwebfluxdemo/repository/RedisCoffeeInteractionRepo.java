package org.unlogged.springwebfluxdemo.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.model.Coffee;
import reactor.core.publisher.Flux;

//@Repository
//@Component
public interface RedisCoffeeInteractionRepo {
    Flux<Coffee> all();
}
