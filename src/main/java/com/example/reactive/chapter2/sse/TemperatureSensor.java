package com.example.reactive.chapter2.sse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TemperatureSensor {

    private final Random random;
    private final ScheduledExecutorService executor;
    private final ApplicationEventPublisher publisher;

    public TemperatureSensor(ApplicationEventPublisher publisher) {
        this.random = new Random();
        this.executor = Executors.newSingleThreadScheduledExecutor();
        this.publisher = publisher;
    }

    @PostConstruct
    private void initialize() {
        executor.schedule(this::probe, 1, TimeUnit.SECONDS);
    }

    private void probe() {
        publisher.publishEvent(new Temperature(16 + random.nextGaussian() * 10));
        executor.schedule(this::probe, 2, TimeUnit.SECONDS);
    }
}
