package org.unlogged.springwebfluxdemo.controller;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple4;

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

    @RequestMapping("/parallel")
    public List<Integer> parallelFluxExample() throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        ParallelFlux<Integer> parallelFlux = Flux.just(1, 2, 3, 4, 5, 6, 7, 8)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(i -> i * 2);

        parallelFlux.subscribe(elements::add);

        // Sleep to ensure all parallel tasks are completed
        Thread.sleep(500);
        return elements;
    }

    @RequestMapping("/disposable")
    public List<Integer> disposableExample() throws InterruptedException {
        List<Integer> elements = new ArrayList<>();
        Disposable disposable = Flux.just(1, 2, 3, 4, 5)
                .delayElements(ofSeconds(1))
                .subscribe(elements::add);

        // Dispose the subscription after 3.5 seconds
        Thread.sleep(3500);
        disposable.dispose();

        // Sleep to ensure disposal has time to take effect
        Thread.sleep(2000);
        return elements; //3 elements should be returned
    }

    @RequestMapping("/tuple4")
    public Flux<Tuple4<Integer, String, Double, Boolean>> tuple4Example() {
        Flux<Integer> flux1 = Flux.just(1, 2, 3, 4);
        Flux<String> flux2 = Flux.just("A", "B", "C", "D");
        Flux<Double> flux3 = Flux.just(1.1, 2.2, 3.3, 4.4);
        Flux<Boolean> flux4 = Flux.just(true, false, true, false);

        Flux<Tuple4<Integer, String, Double, Boolean>> combinedFlux = Flux.zip(flux1, flux2, flux3, flux4);

        return combinedFlux;
    }


}
