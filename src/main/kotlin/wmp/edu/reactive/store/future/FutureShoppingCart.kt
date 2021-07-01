package wmp.edu.reactive.store.future

import wmp.edu.reactive.shared.logger
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import java.util.concurrent.Future
import java.util.concurrent.FutureTask

class FutureShoppingCart : ShoppingCartService {
    override fun calculate(input: Input): Future<Output> {
        val future = FutureTask {
            Thread.sleep(2_000)
            logger().info("input : $input")
            Output("test")
        }
        Thread(future).start()
        return future
    }
}
