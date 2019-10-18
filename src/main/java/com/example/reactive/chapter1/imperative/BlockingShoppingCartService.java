package com.example.reactive.chapter1.imperative;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockingShoppingCartService implements ShoppingCartService {

    @Override
    public Output calculate(Input input) {

        try {
            log.info("Imperative Style calculating...");
            Thread.sleep(2000);
            log.info("Complete!");

        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        return new Output();
    }
}
