package com.example.reactive.chapter1.completable;

import java.util.concurrent.CompletableFuture;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CompletableFutureShoppingCartService implements ShoppingCartService {

    @Override
    public CompletableFuture<Output> calculate(Input input) {

        log.info("CompletableFuture Style calculating...");

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            return new Output();
        });
    }
}
