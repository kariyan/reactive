package com.example.reactive.chapter1.completable;

import java.util.concurrent.CountDownLatch;

import com.example.reactive.chapter1.commons.Input;

import org.springframework.util.StopWatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final ShoppingCartService scService;
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    private void process() {

        scService.calculate(new Input())
                .thenAccept(output -> {
                    log.info("Complete!");
                    countDownLatch.countDown();
                });
    }

    public static void main(String[] args) throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        new OrderService(new CompletableFutureShoppingCartService()).process();
        new OrderService(new CompletableFutureShoppingCartService()).process();

//        countDownLatch.await();

        stopWatch.stop();
        log.info("took ... : {} ms", stopWatch.getTotalTimeMillis());
    }
}
