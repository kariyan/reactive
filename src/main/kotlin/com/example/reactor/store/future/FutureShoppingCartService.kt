package com.example.reactor.store.future

import com.example.reactor.shared.logger
import com.example.reactor.store.domain.Input
import com.example.reactor.store.domain.Output
import java.util.concurrent.Future
import java.util.concurrent.FutureTask

class FutureShoppingCartService {
    fun calculate(input: Input): Future<Output> {
        val future = FutureTask {
            Thread.sleep(2_000)
            logger().info("calculated!")
            Output()
        }
        Thread(future).start()
        return future
    }
}
