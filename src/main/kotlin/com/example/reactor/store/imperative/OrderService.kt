package com.example.reactor.store.imperative

import org.springframework.util.StopWatch

class OrderService {
    fun process() {
        ImperativeShoppingCartService().calculate()
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()

    OrderService().process()
    OrderService().process()

    stopWatch.stop()
    println("elapsed: ${stopWatch.totalTimeMillis}")
}
