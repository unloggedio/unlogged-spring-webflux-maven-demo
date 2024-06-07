package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.unlogged.springwebfluxdemo.model.EmployeeV1;
import reactor.core.publisher.Mono;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RedisOpsController {

    private final ReactiveRedisOperations<String, String> redisOps;
    private final ObjectMapper objectMapper;

    @Autowired
    public RedisOpsController(ReactiveRedisOperations<String, String> redisOps, ObjectMapper objectMapper) {
        this.redisOps = redisOps;
        this.objectMapper = objectMapper;
    }

    @RequestMapping("/redis/set")
    public Mono<Boolean> setValue(@RequestBody EmployeeV1 employeeV1) {
        return redisOps.opsForValue().set(employeeV1.getId(), employeeV1.toJson());
    }

    @GetMapping("/redis/get/{key}")
    public Mono<EmployeeV1> getValue(@PathVariable String key) {
        return redisOps.opsForValue().get(key)
                .flatMap(json -> {
                    try {
                        return Mono.justOrEmpty(objectMapper.readValue(json, EmployeeV1.class));
                    } catch (Exception e) {
                        return Mono.empty();
                    }
                });
    }
}