package org.example;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MyHashMap<K, V> {

    private int initLength = 10;
    private Object[] keys = new Object[initLength];
    private Object[] values = new Object[initLength];
    private int size;

    public V put(K key, V value) {
        if (size > 0) {
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] != null && keys[i].equals(key)) {
                    V removed = (V) values[i];
                    values[i] = value;
                    return removed;
                }
            }
        }
        if(size == initLength) {
            initLength += 10;
            keys = Arrays.copyOf(keys, initLength);
            values = Arrays.copyOf(values, initLength);
        }
        keys[size] = key;
        values[size] = value;
        size++;

        return null;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        return IntStream.range(0, keys.length)
                .filter(e -> keys[e].equals(key))
                .mapToObj(e -> (V) values[e])
                .findFirst()
                .orElse(null);
    }

    public V remove(K key) {
        V removed;
        for(int i=0; i<keys.length; i++) {
            if(keys[i] != null && keys[i].equals(key)) {
                removed = (V) values[i];
                for(int j=i+1; j<size; j++) {
                    keys[j] = keys[j + 1];
                    keys[j + 1] = null;
                    values[j] = values[j + 1];
                    values[j + 1] = null;
                }
                size--;
                return removed;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return Arrays.stream(keys)
                .anyMatch(e -> e != null && e.equals(key));
    }

    public boolean containsValue(V value) {
        return Arrays.stream(values)
                .anyMatch(e -> e != null && e.equals(value));
    }

    public void clear() {
        size = 0;
        initLength = 10;
        keys = new Object[initLength];
        values = new Object[initLength];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i=0; i<size; i++) {
            sb.append(keys[i]).append("=").append(values[i]);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("}");

        return sb.toString();
    }
}
