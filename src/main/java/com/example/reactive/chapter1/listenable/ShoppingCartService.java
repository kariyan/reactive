package com.example.reactive.chapter1.listenable;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

import org.springframework.util.concurrent.ListenableFuture;

public interface ShoppingCartService {

    ListenableFuture<Output> calculate(Input input);
}
