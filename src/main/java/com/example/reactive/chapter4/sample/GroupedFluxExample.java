package com.example.reactive.chapter4.sample;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.LinkedList;

@Slf4j
public class GroupedFluxExample {
    public static void main(String[] args) {
        Flux.range(1, 7)
                .groupBy(e -> e % 2 == 0 ? "Even" : "Odd")
                .subscribe(groupFlux -> groupFlux.scan(new LinkedList<>(), (list, elem) -> {
                    list.add(elem);
                    if (list.size() > 2) {
                        list.remove(0);
                    }
                    return list;
                }).filter(arr -> !arr.isEmpty())
                        .subscribe(data ->
                                log.info("{}: {}", groupFlux.key(), data)));
    }
}
