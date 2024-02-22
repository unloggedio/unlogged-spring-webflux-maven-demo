package org.unlogged.springwebfluxdemo.controller;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ReactorSchedulerOpsController {

    public String immediateScheduler() {
        AtomicReference<String> atomicReference = new AtomicReference<>("");
        Scheduler scheduler = Schedulers.immediate();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> atomicReference.set("action"));
        return atomicReference.get();
    }

    public String boundedElasticScheduler() throws InterruptedException {
        AtomicReference<String> atomicReference = new AtomicReference<>("");
        Scheduler scheduler = Schedulers.boundedElastic();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> atomicReference.set("action"));
        Thread.sleep(200);
        return atomicReference.get();
    }

    public String customBEScheduler() throws InterruptedException {
        AtomicReference<String> atomicReference = new AtomicReference<>("");
        Scheduler scheduler = Schedulers.newBoundedElastic(1, 1, "new be");
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> atomicReference.set("action"));
        Thread.sleep(200);
        return atomicReference.get();
    }

    public String executorBasedScheduler() throws InterruptedException {
        ExecutorService executorService =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());
        AtomicReference<String> atomicReference = new AtomicReference<>("");
        Scheduler scheduler = Schedulers.fromExecutorService(executorService);
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> atomicReference.set("action"));
        Thread.sleep(200);
        return atomicReference.get();
    }

    public String parallelExecutor() throws InterruptedException {
        AtomicReference<String> atomicReference = new AtomicReference<>("");
        Scheduler scheduler = Schedulers.parallel();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> atomicReference.set("action"));
        Thread.sleep(200);
        return atomicReference.get();
    }

    public String singleScheduler() throws InterruptedException {
        AtomicReference<String> atomicReference = new AtomicReference<>("");
        Scheduler scheduler = Schedulers.single();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.schedule(() -> atomicReference.set("action"));
        Thread.sleep(200);
        return atomicReference.get();
    }

    public List<String> scheduleSubscribeOn() throws InterruptedException {
        List<String> urlList = Arrays.asList("url 1  2  ", "url xy ", "urlz");
        List<String> result = new ArrayList<>();
        fetchUrls(urlList)
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(body -> {
                    result.add(body);
                    System.out.println(Thread.currentThread().getName() + " from first list, got " + body);
                });
        Thread.sleep(200);
        return result;
    }

    private Flux<String> fetchUrls(List<String> urls) {
        return Flux.fromIterable(urls)
                .map(this::processUrl);
    }

    //supposed to be a blocking call
    private String processUrl(String url) {
        return url.trim();
    }

}
