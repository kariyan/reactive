package wmp.edu.reactive.store.listenable

import org.springframework.util.StopWatch
import wmp.edu.reactive.shared.logger
import wmp.edu.reactive.store.common.Input
import java.util.concurrent.CountDownLatch

val countDownLatch = CountDownLatch(2)

class OrderService {
    fun process(input: Input) {
        return ListenableFutureShoppingCart().calculate(input).addCallback(
                {
                    println("results: $it")
                    countDownLatch.countDown()
                },
                {
                    logger().error(it.message, it)
                    countDownLatch.countDown()
                }
            )
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()

    OrderService().process(Input(1L))
    OrderService().process(Input(2L))

    countDownLatch.await()
    stopWatch.stop()
    println(stopWatch.lastTaskTimeMillis)
}
