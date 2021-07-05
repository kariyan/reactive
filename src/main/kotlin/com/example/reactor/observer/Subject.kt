package com.example.reactor.observer

import com.example.reactor.shared.logger

class Subject {
    private val observers = mutableListOf<Observer>()

    fun registerObserver(observer: Observer) {
        observers.add(observer)
        logger().info("[${observer.name}] register")
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
        logger().info("[${observer.name}] removed")
    }

    fun notifyObservers(event: Event) {
        for (observer in observers) {
            observer.notify(event)
        }
    }
}
