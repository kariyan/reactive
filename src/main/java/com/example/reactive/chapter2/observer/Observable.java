package com.example.reactive.chapter2.observer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Observable implements Observer<Event> {

    private String name;

    public void observe(Event event) {

        log.info("[{}] event received : {}", name, event.getValue());

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                log.info("[{}] job complete!", name);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
