package com.example.reactive.chapter2.observer;

import org.junit.Test;

class IteratorTest {

    @Test
    public void testNext() {

        Iterator<Event> iterator = new Iterator<Event>() {
            @Override
            public Event next() {
                return null;
            }

            @Override
            public boolean hasNext() {
                return false;
            }
        };

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testHasNext() {
    }
}