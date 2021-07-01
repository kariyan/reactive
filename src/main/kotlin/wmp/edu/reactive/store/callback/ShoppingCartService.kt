package wmp.edu.reactive.store.callback

import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import java.util.function.Function

interface ShoppingCartService {
    fun calculate(input: Input, function: Function<Input, Output>)
}
