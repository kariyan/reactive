package com.example.reactive.chapter1.future;

import java.util.concurrent.Future;

import com.example.reactive.chapter1.commons.Input;
import com.example.reactive.chapter1.commons.Output;

public interface ShoppingCartService {

    Future<Output> calculate(Input input);
}
