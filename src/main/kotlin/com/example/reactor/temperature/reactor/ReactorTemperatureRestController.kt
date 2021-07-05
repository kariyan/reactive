package com.example.reactor.temperature.reactor

import com.example.reactor.shared.logger
import com.example.reactor.temperature.domain.Temperature
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/reactor")
class ReactorTemperatureRestController(val temperatureSensor: ReactorTemperatureSensor) {
    @GetMapping("/temperature-stream")
    fun temperature(): Flux<ServerSentEvent<Temperature>> {
        return temperatureSensor.prove()
            .doOnNext { logger().info("$it") }
            .flatMap { Flux.just(ServerSentEvent.builder(it).build()) }
    }
}
