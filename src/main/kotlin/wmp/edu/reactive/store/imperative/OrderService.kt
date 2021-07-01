package wmp.edu.reactive.store.imperative

import org.springframework.util.StopWatch
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output

class OrderService {
    fun process(input: Input): Output {
        return ImperativeShoppingCart().calculate(input)
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()

    OrderService().process(Input(1L))
    OrderService().process(Input(2L))

    stopWatch.stop()
    println(stopWatch.lastTaskTimeMillis)
}
