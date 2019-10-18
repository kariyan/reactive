package com.example.reactive.chapter2.rx;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.example.reactive.chapter2.sse.Temperature;

import org.springframework.stereotype.Component;

import io.reactivex.Observable;
import lombok.Getter;

@Component
public class TemperatureRxSensor {

    private Random random = new Random();
    @Getter private Observable<Temperature> temperatureStream =
            Observable.interval(2, 1, TimeUnit.SECONDS)
                    .map(aLong -> probe())
                    .publish()
                    .refCount();

    private Temperature probe() {
        return new Temperature(16 + random.nextGaussian() * 10);
    }
}
