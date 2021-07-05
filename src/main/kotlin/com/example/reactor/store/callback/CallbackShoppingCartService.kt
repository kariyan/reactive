package com.example.reactor.store.callback

import com.example.reactor.shared.logger
import com.example.reactor.store.domain.Input

class CallbackShoppingCartService {
    fun calculate(input: Input, runnable: Runnable) {
        Thread {
            Thread.sleep(2_000)
            logger().info("calculated!")
            runnable.run()
        }.start()
    }
}
