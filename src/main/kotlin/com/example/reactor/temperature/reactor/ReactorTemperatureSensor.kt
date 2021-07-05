package com.example.reactor.temperature.reactor

import com.example.reactor.temperature.domain.Temperature
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.*

@Component
class ReactorTemperatureSensor {
    private val random = Random()
    private val temperatureFlux = Flux.interval(Duration.ofSeconds(1))
        .map { Temperature(random.nextInt(20)) }
        .share()

    fun prove() = temperatureFlux
}
