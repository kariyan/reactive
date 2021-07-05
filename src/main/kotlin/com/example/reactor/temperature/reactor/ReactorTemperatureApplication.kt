package com.example.reactor.temperature.reactor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactorTemperatureApplication

fun main(args: Array<String>) {
    runApplication<ReactorTemperatureApplication>(*args)
}
