package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class VirtualThreadControllerOps {

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
