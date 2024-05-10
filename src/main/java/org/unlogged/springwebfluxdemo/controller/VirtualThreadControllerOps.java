package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class VirtualThreadControllerOps {

    private final ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    @RequestMapping("/virtual-threads/process")
    public Mono<String> processRequest() {
        return Mono.fromCallable(() -> {
                    System.out.println("Processing on virtual thread: " + Thread.currentThread().getClass());
                    Thread.sleep(1000); // Simulate I/O wait
                    return "Processing complete!";
                })
                .subscribeOn(Schedulers.fromExecutor(executor)); // Schedule on virtual threads
    }

    private String threadName() throws InterruptedException {
        System.out.println(Thread.currentThread().isVirtual());
        Thread.Builder threadBuilder = Thread.ofVirtual().name("test thread");
        Runnable r = () -> {
            System.out.println("Hello");
        };
        Thread thread = threadBuilder.start(r);
        thread.join();
        if(thread.isVirtual()) {
            return "Virtual Thread: " + thread.getName();
        } else {
            return thread.getName() + "is not Virtual";
        }
    }
}
