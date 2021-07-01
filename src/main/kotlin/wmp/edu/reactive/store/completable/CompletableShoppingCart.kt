package wmp.edu.reactive.store.completable

import wmp.edu.reactive.shared.logger
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import java.util.concurrent.CompletableFuture

class CompletableShoppingCart : ShoppingCartService {
    override fun calculate(input: Input): CompletableFuture<Output> {
        return CompletableFuture.supplyAsync {
            Thread.sleep(2_000)
            logger().info("input : $input")
            Output("test")
        }
    }
}
