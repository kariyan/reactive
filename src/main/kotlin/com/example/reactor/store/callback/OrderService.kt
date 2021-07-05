package com.example.reactor.store.callback

import com.example.reactor.store.domain.Input
import org.springframework.util.StopWatch
import java.util.concurrent.CountDownLatch

val countDownLatch = CountDownLatch(2)

class OrderService {
    fun process() {
        CallbackShoppingCartService().calculate(Input()) {
            countDownLatch.countDown()
        }
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()

    OrderService().process()
    OrderService().process()

    countDownLatch.await()
    stopWatch.stop()
    println("elapsed: ${stopWatch.totalTimeMillis}")
}
