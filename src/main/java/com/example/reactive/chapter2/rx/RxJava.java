package com.example.reactive.chapter2.rx;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("ResultOfMethodCallIgnored")
public class RxJava {

    public static void main(String[] args) throws InterruptedException {

//        directCall();
//        useJust();
//        fromArray()
//        concat();
        interval();
    }

    private static void interval() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Disposable intervalDisposal = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(x -> {
                    log.info("input : {}", x);
                    countDownLatch.countDown();
                });

        countDownLatch.await();
        intervalDisposal.dispose();
    }

    private static void concat() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> longTask = executorService.submit(() -> {
            log.info("long task start ...");
            Thread.sleep(1000);
            return "long tasks is succeed!";
        });

        Observable<String> longObservable = Observable.fromFuture(longTask);
        Observable<String> shortObservable = Observable.fromCallable(() -> {
            log.info("short task start ...");
            return "short task is succeed!";
        });

        Observable<String> concatObservable = Observable.concat(shortObservable, longObservable);

        concatObservable
                .subscribe(log::info,
                        throwable -> {
                        },
                        executorService::shutdown);
    }

    private static void fromArray() {
        Integer[] intArray = new Integer[10];
        for (int i = 0; i < 10; i++) {
            intArray[i] = i;
        }
        Observable<Integer> longObservable = Observable.fromArray(intArray);
        longObservable.subscribe(i -> log.info("input : {}", i));
    }

    private static void useJust() {
        Observable<String> observable = Observable.just("Hello", ", ", "stranger", "!");
        observable.subscribe(s -> {
                    log.info("[onNext]");
                    log.info(s);
                },
                e -> log.info("[onError]"));
    }

    private static void directCall() {
        Observable<String> observable = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onNext(", ");
            emitter.onNext("stranger");
            emitter.onNext("!");
            emitter.onComplete();
        });

        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

                log.info("[onSubscribe]");
            }

            @Override
            public void onNext(String s) {

                log.info("[onNext]");
                log.info(s);
            }

            @Override
            public void onError(Throwable t) {

                log.info("[onError]");
            }

            @Override
            public void onComplete() {

                log.info("[onComplete]");
            }
        };

        observable.subscribe(observer);
    }
}
