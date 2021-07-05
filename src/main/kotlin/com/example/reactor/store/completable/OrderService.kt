package com.example.reactor.store.completable

import com.example.reactor.store.domain.Input
import com.example.reactor.store.domain.Output
import org.springframework.util.StopWatch
import java.util.concurrent.CompletableFuture

class OrderService {
    fun process(): CompletableFuture<Output> {
        return CompletableFutureShoppingCartService().calculate(Input())
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()

    CompletableFuture.allOf(
        OrderService().process(),
        OrderService().process(),
    ).join()

    stopWatch.stop()
    println("elapsed: ${stopWatch.totalTimeMillis}")
}
