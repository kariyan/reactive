package wmp.edu.reactive.observer

fun main() {
    val subject = Subject()
    var seq = 0L
    subject.notify(Event(++seq, "first"))
    subject.registerObserver(Observer("OJB-1"))
    subject.notify(Event(++seq, "secound"))
    val observer2 = Observer("OJB-2")
    subject.registerObserver(observer2)
    subject.notify(Event(++seq, "third"))
    subject.removeObserver(observer2)
    subject.notify(Event(++seq, "fourth"))
}
