package wmp.edu.reactive.store.completable

import org.springframework.util.StopWatch
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import java.util.concurrent.CompletableFuture

class OrderService {
    fun process(input: Input): CompletableFuture<Output> {
        return CompletableShoppingCart().calculate(input)
    }
}

fun main() {
    val stopWatch = StopWatch()
    stopWatch.start()
    val function: (t: Output) -> Unit = { println("results: $it") }

    CompletableFuture.allOf(
        OrderService().process(Input(1L))
            .thenAccept(function),
        OrderService().process(Input(2L))
            .thenApply(function),
    ).join()

    stopWatch.stop()
    println(stopWatch.lastTaskTimeMillis)
}
