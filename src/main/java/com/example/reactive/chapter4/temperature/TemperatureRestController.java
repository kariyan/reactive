package com.example.reactive.chapter4.temperature;

import com.example.reactive.chapter2.sse.Temperature;
import io.reactivex.Observable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@SpringBootApplication
public class TemperatureRestController {

    private final Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(TemperatureRestController.class, args);
    }

    @GetMapping(value = "/flux")
    public Flux<String> flux() {
        return Flux.interval(Duration.ofSeconds(1)).map(seq -> "message: " + seq + ", time: " + LocalTime.now().toString());
    }

    @GetMapping(value = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1)).map(seq -> "message: " + seq + ", time: " + LocalTime.now().toString());
    }

    @GetMapping("/stream-see")
    public Flux<ServerSentEvent<String>> streamSee() {
        return Flux.interval(Duration.ofSeconds(1)).map(seq -> ServerSentEvent.<String>builder()
                .data("message: " + seq + ", time: " + LocalTime.now().toString())
                .build());
    }

    @GetMapping("/temperature-stream")
    public Flux<ServerSentEvent<Temperature>> streamTemperature() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> ServerSentEvent.<Temperature>builder()
                        .data(new Temperature(random.nextGaussian() * 10 + 16))
                        .build());
    }

    @GetMapping("/temperature-stream-rx")
    public Observable<ServerSentEvent<Temperature>> streamTemperatureRx() {
        return Observable.interval(1, 1, TimeUnit.SECONDS)
                .map(seq -> ServerSentEvent.<Temperature>builder()
                        .data(new Temperature(random.nextGaussian() * 10 + 16))
                        .build());
    }
}
