package com.example.reactive.chapter1.imperative;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

import org.springframework.util.StopWatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final ShoppingCartService scService;

    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        new OrderService(new BlockingShoppingCartService()).process();
        new OrderService(new BlockingShoppingCartService()).process();

        stopWatch.stop();
        log.info("took ... : {} ms", stopWatch.getTotalTimeMillis());
    }

    private void process() {

        Output output = scService.calculate(new Input());
    }
}
