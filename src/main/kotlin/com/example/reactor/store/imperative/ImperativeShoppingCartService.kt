package com.example.reactor.store.imperative

import com.example.reactor.shared.logger
import com.example.reactor.store.domain.Output

class ImperativeShoppingCartService {
    fun calculate(): Output {
        Thread.sleep(2_000)
        logger().info("calculated!")

        return Output()
    }
}
