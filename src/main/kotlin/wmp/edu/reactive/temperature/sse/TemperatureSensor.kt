package wmp.edu.reactive.temperature.sse

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Service
class TemperatureSensor(private val publisher: ApplicationEventPublisher) {
    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    private val random = Random()

    @PostConstruct
    fun initialize() {
        scheduler.schedule({ prove() }, 2, TimeUnit.SECONDS)
    }

    fun prove() {
        publisher.publishEvent(Temperature(random.nextInt(20)))
        scheduler.schedule({ prove() }, 1, TimeUnit.SECONDS)
    }
}
