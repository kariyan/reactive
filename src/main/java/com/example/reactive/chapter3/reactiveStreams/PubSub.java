package com.example.reactive.chapter3.reactiveStreams;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"SubscriberImplementation", "PublisherImplementation"})
public class PubSub {

    public static void main(String[] args) {

        List<Integer> collect = Stream.iterate(1, a -> a + 1)
                .limit(10)
                .collect(Collectors.toList());
        Iterator<Integer> iter = collect.iterator();

        Publisher<Integer> pub =
                s -> s.onSubscribe(new Subscription() {

                    @Override
                    public void request(long n) {

                        int i = 0;
                        try {
                            while (i++ < n) {
                                if (iter.hasNext()) {
                                    s.onNext(iter.next());
                                } else {
                                    s.onComplete();
                                    break;
                                }
                            }
                        } catch (RuntimeException e) {
                            s.onError(e);
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                });

        pub.subscribe(new Subscriber<Integer>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;

                log.info("onSubscribe");
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                log.info("onNext: {}", integer);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                log.info("onError : {}", t.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        });
    }
}
