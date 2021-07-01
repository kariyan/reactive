package wmp.edu.reactive.store.callback

import wmp.edu.reactive.shared.logger
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import java.util.function.Function

class CallbackShoppingCart : ShoppingCartService {
    override fun calculate(input: Input, function: Function<Input, Output>) {
        Thread {
            Thread.sleep(2_000)
            logger().info("input : $input")

            function.apply(input)
        }.start()
    }
}
