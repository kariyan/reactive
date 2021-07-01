package wmp.edu.reactive.sse

import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import wmp.edu.reactive.shared.logger

@RestController
class TemperatureRestController {

    private val sseEmitters = mutableListOf<SseEmitter>()

    @GetMapping("temperature-stream")
    fun connect(): SseEmitter {
        val emitter = SseEmitter(60_000)
        emitter.onCompletion { sseEmitters.remove(emitter) }
        emitter.onError { sseEmitters.remove(emitter) }
        sseEmitters.add(emitter)
        return emitter
    }

    @Async
    @EventListener
    fun listener(temperature: Temperature) {
        logger().info("event recieved! : $temperature")

        val deadEmitters = mutableListOf<SseEmitter>()
        for (emitter in sseEmitters) {
            try {
                emitter.send(temperature)
            } catch (e: Exception) {
                deadEmitters.add(emitter)
            }
        }
        for (deadEmitter in deadEmitters) {
            sseEmitters.removeAll(deadEmitters)
        }
    }
}
