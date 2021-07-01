package com.example.reactive.chapter2.reactor;

import com.example.reactive.chapter2.sse.Temperature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
@Controller
public class TemperatureReactorController {

    private final TemperatureReactorSensor temperatureReactorSensor;

    public TemperatureReactorController(TemperatureReactorSensor temperatureReactorSensor) {
        this.temperatureReactorSensor = temperatureReactorSensor;
    }

    @GetMapping("temperature-stream")
    public SseEmitter temperatureStream() {
        SseEmitter emitter = new SseEmitter(100000L);
        appendSensor(emitter);
        return emitter;
    }

    private void appendSensor(SseEmitter emitter) {
        Flux<Temperature> temperatureFlux = temperatureReactorSensor.getTemperatureStream();
        temperatureFlux.subscribe(temperature -> {
            log.info("[{}] Event received : {}", Thread.currentThread().getName(), temperature.getValue());
            try {
                emitter.send(temperature);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, e -> log.error("disposed."));
    }
}
