package org.unlogged.springwebfluxdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CronService {

    private final AtomicInteger counter;

    @Autowired
    public CronService() {
        this.counter = new AtomicInteger(0);
    }

    @Scheduled(cron = "0 * * ? * *")
    public void executeTask() {
        counter.incrementAndGet();
        System.out.println("Task executed. Current count: " + counter.get());
    }

    public Mono<Integer> getCount() {
        return Mono.just(counter.get());
    }
}
