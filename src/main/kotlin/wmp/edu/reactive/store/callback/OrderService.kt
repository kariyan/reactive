package wmp.edu.reactive.store.callback

import org.springframework.util.StopWatch
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import wmp.edu.reactive.store.future.OrderService
import java.util.concurrent.CountDownLatch

val countDownLatch = CountDownLatch(2)

class OrderService {
    fun process(input: Input) {
        CallbackShoppingCart().calculate(input) {
            countDownLatch.countDown()
            Output("callback!")
        }
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()

    OrderService().process(Input(1L))
    OrderService().process(Input(2L))

    wmp.edu.reactive.store.future.countDownLatch.await()
    stopWatch.stop()
    println(stopWatch.lastTaskTimeMillis)
}
