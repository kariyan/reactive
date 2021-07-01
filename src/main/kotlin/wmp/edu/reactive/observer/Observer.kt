package wmp.edu.reactive.observer

class Observer(val name: String) {
    fun notify(event: Event) {
        println("[$name] received event : $event")
    }
}
