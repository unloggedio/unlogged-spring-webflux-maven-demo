package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.unlogged.springwebfluxdemo.model.EmployeeV1;
import reactor.core.publisher.Mono;

public class RedisOpsController {

    @Autowired
    private ReactiveRedisTemplate<String, EmployeeV1> redisTemplate;
    private ReactiveValueOperations<String, EmployeeV1> reactiveValueOps;

    public Mono<EmployeeV1> redisOps1() throws InterruptedException {
        reactiveValueOps = redisTemplate.opsForValue();
        Mono<Boolean> result = reactiveValueOps.set("123",
                new EmployeeV1("123", "Bill", "Accounts"));
//        Thread.sleep(200);
        Mono<EmployeeV1> fetchedEmployee = reactiveValueOps.get("123");
//        Thread.sleep(200);
        return fetchedEmployee;
    }

}
