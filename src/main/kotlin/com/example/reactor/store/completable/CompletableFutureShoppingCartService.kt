package com.example.reactor.store.completable

import com.example.reactor.shared.logger
import com.example.reactor.store.domain.Input
import com.example.reactor.store.domain.Output
import java.util.concurrent.CompletableFuture

class CompletableFutureShoppingCartService {
    fun calculate(input: Input): CompletableFuture<Output> {
        return CompletableFuture.supplyAsync {
            Thread.sleep(2_000)
            logger().info("calculated!")
            Output()
        }
    }
}
