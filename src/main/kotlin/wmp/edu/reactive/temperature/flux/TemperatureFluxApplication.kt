package wmp.edu.reactive.temperature.flux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TemperatureFluxApplication

fun main(args: Array<String>) {
    runApplication<TemperatureFluxApplication>(*args)
}

