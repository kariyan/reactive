package wmp.edu.reactive.store.imperative

import wmp.edu.reactive.shared.logger
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output

class ImperativeShoppingCart : ShoppingCartService {
    override fun calculate(input: Input): Output {
        Thread.sleep(2_000)
        logger().info("input : $input")

        return Output("imperative!")
    }
}
