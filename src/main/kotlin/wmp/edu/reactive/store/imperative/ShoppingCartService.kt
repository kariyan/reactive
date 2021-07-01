package wmp.edu.reactive.store.imperative

import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output

interface ShoppingCartService {
    fun calculate(input: Input): Output
}
