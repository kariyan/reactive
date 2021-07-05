package com.example.reactor.temperature.sse

import com.example.reactor.shared.logger
import com.example.reactor.temperature.domain.Temperature
import org.springframework.context.event.EventListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@RestController
@RequestMapping("/see")
class TemperatureRestController {
    val sseEmitters = mutableListOf<SseEmitter>()

    @GetMapping("temperature-stream")
    fun prove(): SseEmitter {
        return SseEmitter(60_000).also { emitter ->
            sseEmitters.add(emitter)
            emitter.onCompletion { sseEmitters.remove(emitter) }
            emitter.onError { sseEmitters.remove(emitter) }
        }
    }

    @EventListener
    fun eventReceiver(temperature: Temperature) {
        val deadEmitter = mutableListOf<SseEmitter>()
        sseEmitters.forEach {
            try {
                it.send(temperature)
                logger().info("received temperature: $temperature")
            } catch (e: Exception) {
                deadEmitter.add(it)
            }
        }

        if (deadEmitter.isNotEmpty()) {
            sseEmitters.removeAll(deadEmitter)
        }
    }
}
