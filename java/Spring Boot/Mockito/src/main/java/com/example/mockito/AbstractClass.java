package com.example.mockito;

import java.util.Collection;

public abstract class AbstractClass<T> {
    public AbstractClass() {
    }

    public AbstractClass(String arg1, Integer arg2) {

    }

    public void addAll(Collection<T> collection) {
        collection.forEach(this::add);
    }

    public abstract void add(T value);
}
