package com.example.reactive.chapter2.observer;

public class Application {

    public static void main(String[] args) {

        Subject subject = new Subject();

        subject.notifyObservers(new Event(1));

        Observer obs1 = new Observable("obs1");
        subject.registerObserver(obs1);
        subject.notifyObservers(new Event(2));

        Observer obs2 = new Observable("obs2");
        subject.registerObserver(obs2);
        subject.notifyObservers(new Event(3));

        subject.unregisterObserver(obs1);
        subject.notifyObservers(new Event(4));
    }
}
