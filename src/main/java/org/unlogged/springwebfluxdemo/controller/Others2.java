package org.unlogged.springwebfluxdemo.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/others2")
public class Others2 {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String apiKey) {
        return cache.computeIfAbsent(apiKey, this::newBucket);
    }

    public Bucket newBucket(String apiKey) {
        Bandwidth bw = Bandwidth.classic(1, Refill.intervally(1, Duration.ofMinutes(20)));
        return bucket(bw);
    }

    public Bucket bucket(Bandwidth limit) {
        return Bucket4j.builder().addLimit(limit).build();
    }

}
