package com.example.reactive.chapter1.future;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private static final List<Future<Output>> futures = new ArrayList<>();
    private final ShoppingCartService scService;

    public static void main(String[] args) {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        new OrderService(new FutureShoppingCartService()).process();
        new OrderService(new FutureShoppingCartService()).process();

        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage(), e);
            }
        });

        stopWatch.stop();
        log.info("took ... : {} ms", stopWatch.getTotalTimeMillis());
    }

    private void process() {
        futures.add(scService.calculate(new Input()));
    }
}
