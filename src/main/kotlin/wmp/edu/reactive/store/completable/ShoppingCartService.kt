package wmp.edu.reactive.store.completable

import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output
import java.util.concurrent.CompletableFuture

interface ShoppingCartService {
    fun calculate(input: Input): CompletableFuture<Output>
}
