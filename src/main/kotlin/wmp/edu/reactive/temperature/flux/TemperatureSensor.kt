package wmp.edu.reactive.temperature.flux

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration
import java.util.*

@Service
class TemperatureSensor {
    private val random = Random()

    fun prove(): Flux<Temperature> {
        return Flux.interval(Duration.ofSeconds(2), Duration.ofSeconds(1))
            .map { Temperature(random.nextInt(20)) }
            .share()
    }
}
