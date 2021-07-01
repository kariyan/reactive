package wmp.edu.reactive.store.listenable

import org.springframework.util.concurrent.ListenableFuture
import wmp.edu.reactive.store.common.Input
import wmp.edu.reactive.store.common.Output

interface ShoppingCartService {
    fun calculate(input: Input): ListenableFuture<Output>
}
