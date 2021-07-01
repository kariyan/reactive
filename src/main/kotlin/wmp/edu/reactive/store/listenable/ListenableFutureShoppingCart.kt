package wmp.edu.reactive.store.listenable

import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureTask
import wmp.edu.reactive.shared.logger
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output

class ListenableFutureShoppingCart : ShoppingCartService {
    override fun calculate(input: Input): ListenableFuture<Output> {
        val future = ListenableFutureTask {
            Thread.sleep(2_000)
            logger().info("input : $input")
            Output("test")
        }
        Thread(future).start()
        return future
    }
}
