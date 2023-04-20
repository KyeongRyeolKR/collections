package org.example;

import java.util.Arrays;

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
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        return null;
    }

    public V remove(K key) {
        for(int i=0; i<keys.length; i++) {
            if(keys[i] != null && keys[i].equals(key)) {
                V removed = (V) values[i];
                keys[i] = keys[i+1];
                keys[i+1] = null;
                keys = Arrays.copyOf(keys, initLength); // 이걸 안해주면 다음에 값을 put 할때 인덱스가 이어지지 않음
                values[i] = values[i+1];
                values[i+1] = null;
                values = Arrays.copyOf(values, initLength); // 이걸 안해주면 다음에 값을 put 할때 인덱스가 이어지지 않음
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
        for(int i=0; i<keys.length; i++) {
            if(keys[i] != null && values[i] != null) {
                sb.append(keys[i]).append("=").append(values[i]);
                if (i != keys.length - 1 && keys[i+1] != null) sb.append(", ");
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
