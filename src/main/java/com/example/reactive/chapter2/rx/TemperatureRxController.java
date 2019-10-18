package com.example.reactive.chapter2.rx;

import com.example.reactive.chapter2.sse.Temperature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SuppressWarnings("ResultOfMethodCallIgnored")
public class TemperatureRxController {

    private TemperatureRxSensor temperatureRxSensor;

    public TemperatureRxController(TemperatureRxSensor temperatureRxSensor) {
        this.temperatureRxSensor = temperatureRxSensor;
    }

    @GetMapping("temperature-stream")
    public SseEmitter temperatureStream() {

        SseEmitter emitter = new SseEmitter(100000L);
        appendSensor(emitter);
        return emitter;
    }

    private void appendSensor(SseEmitter emitter) {

        Observable<Temperature> temperatureObservable = temperatureRxSensor.getTemperatureStream();

        Disposable disposable = temperatureObservable.subscribe(temperature -> {
            log.info("[{}] Event received : {}", Thread.currentThread().getName(), temperature.getValue());
            emitter.send(temperature);
        }, e -> log.error("disposed."));

        temperatureObservable.doOnDispose(disposable::dispose);
    }
}
