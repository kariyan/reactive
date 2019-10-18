package com.example.reactive.chapter1.callback;

import java.util.function.Consumer;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CallbackShoppingCartService implements ShoppingCartService {

    @Override
    public void calculate(Input input, Consumer<Output> consumer) {

        log.info("Callback Style calculating...");

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
            consumer.accept(new Output());
        }).start();
    }
}
