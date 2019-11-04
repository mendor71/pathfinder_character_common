package com.mendor.pathfinder.common.util;

public class Tuple2<T,E> {
    private T value1;
    private E value2;

    public Tuple2(T value1, E value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public E getValue2() {
        return value2;
    }
}
