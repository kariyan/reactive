package com.example.reactive.chapter1.callback;

import java.util.concurrent.CountDownLatch;

import com.example.reactive.chapter1.commons.Input;

import org.springframework.util.StopWatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    private final ShoppingCartService scService;

    public static void main(String[] args) throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        new OrderService(new CallbackShoppingCartService()).process();
        new OrderService(new CallbackShoppingCartService()).process();

//        countDownLatch.await();

        stopWatch.stop();
        log.info("took ... : {} ms", stopWatch.getTotalTimeMillis());
    }

    private void process() {

        scService.calculate(new Input(), output -> {
            log.info("Complete!!");
            countDownLatch.countDown();
        });
    }
}
