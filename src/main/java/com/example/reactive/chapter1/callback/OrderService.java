package com.example.reactive.chapter1.callback;

import com.example.reactive.chapter1.commons.Input;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private static final CountDownLatch countDownLatch = new CountDownLatch(2);
    private final ShoppingCartService scService;

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        new OrderService(new CallbackShoppingCartService()).process();
        new OrderService(new CallbackShoppingCartService()).process();

        countDownLatch.await();

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
