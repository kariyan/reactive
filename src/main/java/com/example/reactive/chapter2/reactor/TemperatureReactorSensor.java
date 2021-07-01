package com.example.reactive.chapter2.reactor;

import com.example.reactive.chapter2.sse.Temperature;
import lombok.Getter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

@Component
public class TemperatureReactorSensor {
    private final Random random = new Random();

    @Getter
    private final Flux<Temperature> temperatureStream =
            Flux.interval(Duration.ofSeconds(2), Duration.ofSeconds(1))
                    .map(aLong -> probe())
                    .share();

    private Temperature probe() {
        return new Temperature(16 + random.nextGaussian() * 10);
    }
}
