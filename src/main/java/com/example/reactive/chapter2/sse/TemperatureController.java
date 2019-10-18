package com.example.reactive.chapter2.sse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TemperatureController {

    private List<SseEmitter> emitters = new ArrayList<>();

    @GetMapping("temperature-stream")
    public SseEmitter temperatureStream() {

        SseEmitter emitter = new SseEmitter(60000L);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitters.add(emitter);

        return emitter;
    }

    @Async
    @EventListener
    public void onEvent(Temperature temperature) {

        List<SseEmitter> deadEmitters = new ArrayList<>();

        log.info("[{}] Event received : {}", Thread.currentThread().getName(), temperature.getValue());

        emitters.forEach(emitter -> {
            try {
                emitter.send(temperature);

            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        });

        emitters.removeAll(deadEmitters);
    }
}
