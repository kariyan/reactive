package com.example.reactive.chapter1.callback;

import java.util.function.Consumer;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

public interface ShoppingCartService {

    void calculate(Input input, Consumer<Output> consumer);
}
