package com.example.reactive.chapter2.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject implements Publisher<Event> {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {

        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {

        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {

        observers.forEach(observer -> observer.observe(event));
    }
}
