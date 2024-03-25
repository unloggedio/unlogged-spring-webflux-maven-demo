package org.unlogged.springwebfluxdemo.controller;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/rx")
public class RXJavaController {

    @RequestMapping("/obs/1")
    public String getStringFromObservable() {
        AtomicReference<String> result = new AtomicReference<>();
        Observable<String> observable = Observable.just("Do you know da way");
        observable.subscribe(result::set);
        return result.get();
    }

    @RequestMapping("/obs/2")
    public Observable<String> getObservableString() {
        Observable<String> observable = Observable.just("Do you know da way");
        return observable;
    }

    @RequestMapping("/obs/ops/1")
    public String mapAndFilter() {
        StringBuilder resList[] = new StringBuilder[1];
        resList[0] = new StringBuilder("E");
        String[] letters = {"123", "345", "678", "1015", "760", "458", "740", "489"};
        Observable<String> observable = Observable.fromArray(letters);
        observable = observable.map(String::toLowerCase);
        observable = observable.filter(element -> Integer.parseInt(element) >= 460);
        observable.subscribe(
                i -> resList[0].append("," + i),  //OnNext
                Throwable::printStackTrace, //OnError
                () -> resList[0].append("_Completed") //OnCompleted
        );
        return resList[0].toString();
    }

    //serialization fails here with unlogged, without it there's numeric value
    @RequestMapping("/obs/ser/1")
    public Observable<String> getObservablePostSubscribe() {
        StringBuilder resList[] = new StringBuilder[1];
        resList[0] = new StringBuilder("E");
        String[] letters = {"123", "345", "678", "1015", "760", "458", "740", "489"};
        Observable<String> observable = Observable.fromArray(letters);
        observable = observable.map(String::toLowerCase);
        observable = observable.filter(element -> Integer.parseInt(element) >= 460);
        observable.subscribe(
                i -> resList[0].append("," + i),  //OnNext
                Throwable::printStackTrace, //OnError
                () -> resList[0].append("_Completed") //OnCompleted
        );
        return observable;
    }

    @RequestMapping("/obs/ops/2")
    public String nestedObservables() {
        StringBuilder resList[] = new StringBuilder[1];
        resList[0] = new StringBuilder("");
        Observable<String> modelsObs = Observable.fromArray("R1", "RSV4", "ZX-10R");

        String[] manufacturers = {"Kawasaki", "Aprilia", "BMW", "Ducati", "Yamaha", "Suzuki", "MV-Agusta", "Honda"};
        Observable<String> observable = Observable.fromArray(manufacturers);
        observable = observable.map(String::toLowerCase);
        observable = observable.filter(element -> element.length() <= 5);

        Observable<String> obs2 = observable.flatMap(s -> modelsObs);
        obs2.subscribe(
                i -> resList[0].append("," + i),  //OnNext
                Throwable::printStackTrace, //OnError
                () -> resList[0].append("_Completed") //OnCompleted
        );
        return resList[0].toString();
    }

    @RequestMapping("/obs/ops/3")
    public String scanObs() {
        String resList[] = new String[1];
        resList[0] = "";
        String[] letters = {"a", "b", "c"};
        Observable.fromArray(letters)
                .scan(new StringBuilder(), StringBuilder::append)
                .subscribe(total -> resList[0] += total.toString());
        return resList[0];
    }

    @RequestMapping("/obs/ops/4")
    public String groupByObs() {
        int[] oddsum = new int[1];
        oddsum[0] = 0;
        int[] evensum = new int[1];
        evensum[0] = 0;
        Observable.fromArray(1, 2, 3, 4, 5)
                .groupBy(i -> 0 == (i % 2) ? "EVEN" : "ODD")
                .subscribe(group ->
                        group.subscribe((number) -> {
                            if (group.getKey().toString().equals("EVEN")) {
                                evensum[0] += number;
                            } else {
                                oddsum[0] += number;
                            }
                        })
                );
        return "OS : " + oddsum[0] + " - ES : " + evensum[0];
    }

    @RequestMapping("/obs/single/1")
    public String single() {
        String[] result = {""};
        Single<String> single = Single.just("Hello")
                .doOnSuccess(i -> result[0] += i)
                .doOnError(error -> {
                    throw new RuntimeException(error.getMessage());
                });
        single.subscribe();
        return result[0];
    }

    @RequestMapping("/obs/ops/5")
    public String[] conditionals() {
        String[] result = new String[3];
        Observable.empty()
                .defaultIfEmpty("Observable is empty")
                .subscribe(s -> result[0] += s);

        Observable.fromArray("a", "b", "c")
                .defaultIfEmpty("Observable is empty")
                .first("sub")
                .subscribe(s -> result[1] += s);

        int[] sum = {0};
        Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .takeWhile(i -> i < 5)
                .subscribe(s -> sum[0] += s);
        result[2] = "" + sum[0];
        return result;
    }

    @RequestMapping("/obs/con/1")
    public String connectableObs() throws InterruptedException {
        String[] result = {""};
        ConnectableObservable<Long> connectable
                = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        connectable.subscribe(i -> result[0] += i);

        connectable.connect();
        Thread.sleep(500);
        return result[0];
    }

    @RequestMapping("/obs/sub/1")
    public int subjectObs() {
        int[] subscriber1 = {0};
        int[] subscriber2 = {0};
        Observer<Integer> obs1 = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                subscriber1[0] += integer;
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        Observer<Integer> obs2 = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                subscriber2[0] += integer;
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        PublishSubject<Integer> subject = PublishSubject.create();
        subject.subscribe(obs1);
        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.subscribe(obs2);
        subject.onNext(4);
        subject.onComplete();
        return subscriber1[0] + subscriber2[0];
    }

    @RequestMapping("/obs/resource/mgmt")
    public String resourceManagementExample() {
        String[] result = {""};
        Observable<Character> values = Observable.using(
                () -> "Resource String",
                r -> {
                    return Observable.create(o -> {
                        for (Character c : r.toCharArray()) {
                            o.onNext(c);
                        }
                        o.onComplete();
                    });
                },
                r -> System.out.println("Disposed: " + r)
        );
        values.subscribe(
                v -> result[0] += v,
                e -> result[0] += e
        );
        return result[0];
    }

    public Flowable<Integer> flowable1() {
        Flowable<Integer> integerFlowable = Flowable.just(1, 2, 3, 4);
        return integerFlowable;
    }

    public Flowable<Integer> fromObs() {
        Observable<Integer> integerObservable = Observable.just(1, 2, 3);
        Flowable<Integer> integerFlowable = integerObservable
                .toFlowable(BackpressureStrategy.BUFFER);
        return integerFlowable;
    }

    public FlowableOnSubscribe<Integer> flowableOnSubscribe1() {
        FlowableOnSubscribe<Integer> flowableOnSubscribe
                = flowable -> flowable.onNext(1);
        return flowableOnSubscribe;
    }

    public Flowable<Integer> fromFlowableOnSub() {
        FlowableOnSubscribe<Integer> flowableOnSubscribe
                = flowable -> flowable.onNext(1);
        Flowable<Integer> integerFlowable = Flowable
                .create(flowableOnSubscribe, BackpressureStrategy.BUFFER);
        return integerFlowable;
    }

    @RequestMapping("/bp/buffered")
    public List<Integer> backPressureBufferedStrat() {
        List<Integer> testList = IntStream.range(0, 100)
                .boxed()
                .collect(Collectors.toList());

        Observable<Integer> observable = Observable.fromIterable(testList);
        TestSubscriber<Integer> testSubscriber = observable
                .toFlowable(BackpressureStrategy.BUFFER)
                .observeOn(Schedulers.computation()).test();

        testSubscriber.awaitDone(200, TimeUnit.MILLISECONDS);

        List<Integer> receivedInts = testSubscriber.values()
                .stream()
                .mapToInt(object -> (int) object)
                .boxed()
                .collect(Collectors.toList());
        return receivedInts;
    }

    @RequestMapping("/bp/drop")
    public List<Integer> backPressureDropStrat() {
        //keep the upperbound of the range high enough for dropping to actually happen
        List<Integer> testList = IntStream.range(0, 100000)
                .boxed()
                .collect(Collectors.toList());
        Observable observable = Observable.fromIterable(testList);
        TestSubscriber<Integer> testSubscriber = observable
                .toFlowable(BackpressureStrategy.DROP)
                .observeOn(Schedulers.computation())
                .test();
        testSubscriber.awaitDone(200, TimeUnit.MILLISECONDS);
        List<Integer> receivedInts = testSubscriber.values()
                .stream()
                .mapToInt(object -> (int) object)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Recv items less than initial? " + (receivedInts.size() < testList.size()));
        return receivedInts;
    }

    @RequestMapping("/bp/latest")
    public List<Integer> backPressureLatestStrat() {
        //keep the upperbound of the range high enough for only the latest ones to be picked
        List<Integer> testList = IntStream.range(0, 100000)
                .boxed()
                .collect(Collectors.toList());
        Observable observable = Observable.fromIterable(testList);
        TestSubscriber<Integer> testSubscriber = observable
                .toFlowable(BackpressureStrategy.LATEST)
                .observeOn(Schedulers.computation())
                .test();

        testSubscriber.awaitDone(200, TimeUnit.MILLISECONDS);
        List<Integer> receivedInts = testSubscriber.values()
                .stream()
                .mapToInt(object -> (int) object)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("Recv items less than initial ? " + (receivedInts.size() < testList.size()));
        return receivedInts;
    }

    @RequestMapping("/bp/error")
    public void backPressureErrorStart() {
        Observable observable = Observable.range(1, 100000);
        TestSubscriber subscriber = observable
                .toFlowable(BackpressureStrategy.ERROR)
                .observeOn(Schedulers.computation())
                .test();

        subscriber.awaitDone(200, TimeUnit.MILLISECONDS);
        subscriber.assertError(MissingBackpressureException.class);
        //to throw an exception in this case
    }

    //should overflow
    @RequestMapping("/bp/missing")
    public void backPressureMissingStrat() {
        Observable observable = Observable.range(1, 100000);
        TestSubscriber subscriber = observable
                .toFlowable(BackpressureStrategy.MISSING)
                .observeOn(Schedulers.computation())
                .test();
        subscriber.awaitDone(200, TimeUnit.MILLISECONDS);
        subscriber.assertError(MissingBackpressureException.class);
    }

    @RequestMapping("/maybe/to/1")
    public TestObserver<Integer> maybe1() {
        return Maybe.just(1)
                .map(x -> x + 7)
                .filter(x -> x > 0)
                .test()
                .assertResult(8);
    }

    @RequestMapping("/maybe/1")
    public List<Integer> maybe2() {
        List<Integer> integers = new ArrayList<>();
        Maybe.just(1)
                .subscribe(
                        x -> {
                            integers.add(x);
                            System.out.print("Emitted item: " + x);
                        },
                        ex -> System.out.println("Error: " + ex.getMessage()),
                        () -> System.out.println("Completed. No items.")
                );
        return integers;
    }

    @RequestMapping("/maybe/3")
    public List<String> maybe3() throws InterruptedException {
        List<String> res = new ArrayList<>();
        Flowable<String> visitors = Flowable.just("c1", "a2", "d3", "s4");
        visitors
                .skip(1)
                .firstElement()
                .subscribe(
                        v -> {
                            res.add(v);
                            System.out.println("1000th visitor: " + v + " won the prize");
                        },
                        ex -> System.out.print("Error: " + ex.getMessage()),
                        () -> System.out.print("We need more marketing"));
        Thread.sleep(200);
        return res;
    }

    @RequestMapping("/completable/1")
    public Boolean getCompletable() {
        final Boolean[] res = {null};
        Completable
                .complete()
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        res[0] = true;
                        System.out.println("Completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        res[0] = false;
                        e.printStackTrace();
                    }
                });
        return res[0];
    }

    @RequestMapping("/from/flowable")
    public Completable fromFlowable() {
        Flowable<String> flowable = Flowable
                .just("request received", "user logged in");
        Completable flowableCompletable = Completable
                .fromPublisher(flowable);
        Completable singleCompletable = Single.just(1)
                .ignoreElement();
        return singleCompletable;
    }

    @RequestMapping("/comp1")
    public void comp1() {
        Completable first = Completable
                .fromSingle(Single.just(1));
        Completable second = Completable
                .fromRunnable(() -> {
                });

        Completable.ambArray(first, Completable.never(), second)
                .test()
                .assertComplete();
    }

    @RequestMapping("/merge1")
    public void mergeComp1() {
        Completable first = Completable
                .fromSingle(Single.just(1));
        Completable second = Completable
                .fromRunnable(() -> {
                });

        Throwable throwable = new RuntimeException();
        Completable error = Single.error(throwable)
                .ignoreElement();

        Completable.mergeArray(first, second)
                .test()
                .assertComplete();

        Completable.mergeArray(first, second, error)
                .test()
                .assertError(throwable);
    }
}
