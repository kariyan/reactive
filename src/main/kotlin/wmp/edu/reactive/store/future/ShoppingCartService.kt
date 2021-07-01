package wmp.edu.reactive.store.future

import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import java.util.concurrent.Future

interface ShoppingCartService {
    fun calculate(input: Input): Future<Output>
}
