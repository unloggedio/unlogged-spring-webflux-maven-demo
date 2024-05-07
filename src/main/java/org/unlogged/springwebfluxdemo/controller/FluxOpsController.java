package org.unlogged.springwebfluxdemo.controller;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.Duration.ofSeconds;

@RestController
@RequestMapping("/flux")
public class FluxOpsController {

    public List<Integer> fluxListSubscribe() {
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4, 50, 100, 150, 200)
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        if (integer >= 50) {
                            elements.add(integer);
                        }
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return elements;
    }

    @RequestMapping("/connectable")
    public void connectableFluxExample() {
        AtomicInteger triesLift = new AtomicInteger(5);
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                    while (triesLift.get() > 0) {
                        triesLift.getAndDecrement();
                        fluxSink.next(System.currentTimeMillis());
                    }
                })
                .publish();
        publish.subscribe(System.out::println);
        publish.subscribe(FluxOpsController::pingOnSubscribe);
        publish.connect();
    }

    @RequestMapping("/string")
    public Flux<String> getFluxString() {
        Flux<String> fluxString = Flux.just("stringInput");
        return fluxString;
    }

    private static void pingOnSubscribe(Object o) {
        System.out.println("PING");
    }

    @RequestMapping("/throttling")
    public int fluxThrottling() {
        AtomicInteger triesLift = new AtomicInteger(5);
        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
                    while (triesLift.get() > 0) {
                        triesLift.getAndDecrement();
                        fluxSink.next("Print : Counter : " + triesLift.get() + " - "
                                + System.currentTimeMillis());
                    }
                })
                .sample(ofSeconds(1))
                .publish();
        publish.subscribe(System.out::println);
        publish.connect();
        return triesLift.get();
    }

    @RequestMapping("/concurrent")
    public List<Integer> concurrent() throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .subscribeOn(Schedulers.parallel())
                .subscribe(elements::add);

        Thread.sleep(200);
        return elements;
    }

    @RequestMapping("/backpressure/1")
    public List<Integer> backPressure1() {
        List<Integer> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .subscribe(new Subscriber<Integer>() {
                    private Subscription s;
                    int onNextAmount;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.s = s;
                        s.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        elements.add(integer);
                        onNextAmount++;
                        if (onNextAmount % 2 == 0) {
                            s.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return elements;
    }

    @RequestMapping("/combine/1")
    public List<String> combineStreams() {
        List<String> elements = new ArrayList<>();
        Flux.just(1, 2, 3, 4)
                .log()
                .map(i -> i * 2)
                .zipWith(Flux.range(0, Integer.MAX_VALUE),
                        (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
                .subscribe(elem -> elements.add(elem));

        return elements;
    }

}
