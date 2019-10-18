package com.example.reactive.chapter1.listenable;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ListenableFutureShoppingCartService implements ShoppingCartService {

    @Override
    public ListenableFuture<Output> calculate(Input input) {

        log.info("ListenableFuture Style calculating...");

        ListenableFutureTask<Output> futureTask = new ListenableFutureTask<>(() -> {
            Thread.sleep(2000);
            return new Output();
        });
        new Thread(futureTask).start();

        return futureTask;
    }
}
