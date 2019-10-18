package com.example.reactive.chapter1.completable;

import java.util.concurrent.CompletableFuture;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

public interface ShoppingCartService {

    CompletableFuture<Output> calculate(Input input);
}
