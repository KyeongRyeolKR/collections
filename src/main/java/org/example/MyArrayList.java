package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MyArrayList<T> {

    private int initLength = 10;
    private Object[] array = new Object[initLength];
    private int size;

    public boolean add(T o) {
        if(size == initLength) {
            initLength += 10;
            array = Arrays.copyOf(array, initLength);
        }
        array[size] = o;
        size++;
        return true;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        return (T) array[index];
    }

    public T remove(int index) {
        T removed = (T) array[index];
        for(int i=index+1; i<size; i++) {
            array[i-1] = array[i];
        }
        array[index + 1] = null;
        size--;
        return removed;
    }

    public T remove(T o) {
        return remove(indexOf(o));
    }

    public boolean contains(T o) {
        return IntStream.range(0, size)
                .anyMatch(e -> array[e].equals(o));
    }

    public int indexOf(T o) {
        return IntStream.range(0, size)
                .filter(e -> array[e].equals(o))
                .findFirst()
                .orElse(-1);
    }

    public void clear() {
        array = new Object[initLength];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
