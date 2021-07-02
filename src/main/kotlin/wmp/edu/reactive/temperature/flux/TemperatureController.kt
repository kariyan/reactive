package wmp.edu.reactive.temperature.flux

import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import wmp.edu.reactive.shared.logger

@RestController
class TemperatureController(val sensor: TemperatureSensor) {
    @GetMapping("/temperature-stream")
    fun prove(): Flux<ServerSentEvent<Temperature>> {
        return sensor.prove()
            .map {
                logger().info("event received! : $it")
                ServerSentEvent.builder(it).build()
            }
    }
}
