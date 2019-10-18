package com.example.reactive.chapter1.future;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FutureShoppingCartService implements ShoppingCartService {

    @Override
    public Future<Output> calculate(Input input) {

        log.info("Future Style calculating...");

        FutureTask<Output> futureTask = new FutureTask<>(() -> {
            Thread.sleep(2000);
            log.info("Complete!");
            return new Output();
        });

        new Thread(futureTask).start();

        return futureTask;
    }
}
