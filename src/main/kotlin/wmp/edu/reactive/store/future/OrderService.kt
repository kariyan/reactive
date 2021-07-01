package wmp.edu.reactive.store.future

import org.springframework.util.StopWatch
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Future

val countDownLatch = CountDownLatch(2)

class OrderService {
    fun process(input: Input): Future<Output> {
        return FutureShoppingCart().calculate(input)
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()

    val futures = mutableListOf<Future<Output>>()
    futures.add(OrderService().process(Input(1L)))
    futures.add(OrderService().process(Input(2L)))

    for (future in futures) {
        println("results: ${future.get()}")
    }
    stopWatch.stop()
    println(stopWatch.lastTaskTimeMillis)
}
