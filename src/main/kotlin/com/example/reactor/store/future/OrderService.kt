package com.example.reactor.store.future

import com.example.reactor.store.domain.Input
import com.example.reactor.store.domain.Output
import org.springframework.util.StopWatch
import java.util.concurrent.Future

val futures = mutableListOf<Future<Output>>()

class OrderService {
    fun process(): Future<Output> {
        return FutureShoppingCartService().calculate(Input())
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()

    futures.add(OrderService().process())
    futures.add(OrderService().process())

    futures.forEach { it.get() }
    stopWatch.stop()
    println("elapsed: ${stopWatch.totalTimeMillis}")
}
