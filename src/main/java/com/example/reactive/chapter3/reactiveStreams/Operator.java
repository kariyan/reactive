package com.example.reactive.chapter3.reactiveStreams;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;

/*
interPub -> [Data1] -> mapPub -> [Data2] -> logSub
                 <- subscribe(logSub)
                 -> onSubscribe(logSub)
                 -> onNext
                 -> onNext
                 -> onComplete
 1. map (a1 -> f -> a2)
*/

@Slf4j
@SuppressWarnings({"SubscriberImplementation", "PublisherImplementation", "DuplicatedCode"})
public class Operator {

    public static void main(String[] args) {

        List<Integer> iterator = Stream.iterate(1, a -> a + 1)
                .limit(10)
                .collect(Collectors.toList());

        Publisher<Integer> pub = iterPub(iterator);
        Publisher<Integer> mapPub = mapPub(pub, x -> x * -1);
        Publisher<Integer> sumPub = sumPub(mapPub);
        Publisher<String> map2Pub = mapPub(sumPub, x -> "hello" + x);
        Publisher<String> reducePub = reducePub(map2Pub, "", (x, y) -> x + "-" + y);
        reducePub.subscribe(logSub());
    }

    private static Publisher<Integer> iterPub(List<Integer> iter) {

        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> s) {
                s.onSubscribe(new Subscription() {

                    @Override
                    public void request(long n) {

                        iter.forEach(s::onNext);
                        s.onComplete();
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };
    }

    private static <T,R> Publisher<R> mapPub(Publisher<T> pub, Function<T, R> func) {

        return new Publisher<R>() {
            @Override
            public void subscribe(Subscriber<? super R> sub) {

                pub.subscribe(new DelegateSub<T,R>(sub) {
                    @Override
                    public void onNext(T i) {
                        sub.onNext(func.apply(i));
                    }
                });
            }
        };
    }

    private static Publisher<Integer> sumPub(Publisher<Integer> mapPub) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> sub) {

                mapPub.subscribe(new DelegateSub<Integer, Integer>(sub) {

                    int sum = 0;

                    @Override
                    public void onNext(Integer i) {
                        sum = sum + i;
                    }

                    @Override
                    public void onComplete() {
                        sub.onNext(sum);
                        sub.onComplete();
                    }
                });
            }
        };
    }

    private static <T,R> Publisher<R> reducePub(Publisher<T> pub, R init, BiFunction<R, T, R> func) {

        return new Publisher<R>() {
            @Override
            public void subscribe(Subscriber<? super R> sub) {

                pub.subscribe(new DelegateSub<T,R>(sub) {
                    R x = init;

                    @Override
                    public void onNext(T y) {

                        x = func.apply(x, y);
                    }

                    @Override
                    public void onComplete() {

                        sub.onNext(x);
                        sub.onComplete();
                    }
                });
            }
        };
    }

    private static <T> Subscriber<T> logSub() {
        return new Subscriber<T>() {
            Subscription sub;

            @Override
            public void onSubscribe(Subscription sub) {
                this.sub = sub;

                log.info("onSubscribe");
                this.sub.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(T integer) {
                log.info("onNext: {}", integer);
            }

            @Override
            public void onError(Throwable t) {
                log.info("onError : {}", t.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        };
    }
}
