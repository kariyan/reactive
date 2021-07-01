package com.example.reactive.chapter4.temperature;

import com.example.reactive.chapter2.sse.Temperature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalTime;

@Slf4j
public class TemperatureClient {

    public static void main(String[] args) {
        TemperatureClient client = new TemperatureClient();
        client.consumeServerSentEvent();
    }

    public void consumeServerSentEvent() {

        WebClient client = WebClient.create("http://localhost:8080");
        ParameterizedTypeReference<ServerSentEvent<Temperature>> type
                = new ParameterizedTypeReference<ServerSentEvent<Temperature>>() {
        };

        Flux<ServerSentEvent<Temperature>> eventStream = client.get()
                .uri("/stream-temperature")
                .retrieve()
                .bodyToFlux(type);

        eventStream.subscribe(
                content -> log.info("Time: {} - event: name[{}], id [{}], content[{}] ",
                        LocalTime.now(), content.event(), content.id(), content.data()),
                error -> log.error("Error receiving SSE: {}", error),
                () -> log.info("Completed!!!"));
    }
}
