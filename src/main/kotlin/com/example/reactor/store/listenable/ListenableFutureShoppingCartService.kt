package com.example.reactor.store.listenable

import com.example.reactor.shared.logger
import com.example.reactor.store.domain.Input
import com.example.reactor.store.domain.Output
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureTask

class ListenableFutureShoppingCartService {
    fun calculate(input: Input): ListenableFuture<Output> {
        val future = ListenableFutureTask {
            Thread.sleep(2_000)
            logger().info("calculated!")
            Output()
        }
        Thread(future).start()
        return future
    }
}
