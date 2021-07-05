package com.example.reactor.observer

fun main() {
    val subject = Subject()

    subject.notifyObservers(Event(1))

    val observer = Observer("OBJ-1")
    subject.registerObserver(observer)
    subject.notifyObservers(Event(2))

    subject.registerObserver(Observer("OBJ-2"))
    subject.notifyObservers(Event(3))

    subject.removeObserver(observer)
    subject.notifyObservers(Event(4))
}
