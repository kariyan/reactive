package wmp.edu.reactive.observer

class Subject {
    private val observable = mutableListOf<Observer>()

    fun notify(event: Event) {
        for (observer in observable) {
            observer.notify(event)
        }
    }

    fun registerObserver(observer: Observer) {
        observable.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observable.remove(observer)
    }
}
