package com.example.reactive.chapter2.observer;

public interface Publisher<T> {

    void registerObserver(Observer<T> observer);
    void unregisterObserver(Observer<T> observer);
    void notifyObservers(T event);
}
