package com.juliakram.java10cycles.datastructures.flat.base.array;

import com.juliakram.java10cycles.datastructures.framework.DataStructure;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by yuliya.kramarenko on 04.12.2014.
 * Data structure that has array in the base, and can dynamically grow the inner array
 */
public abstract class AbstractArrayStructure<T> implements DataStructure<T> {

    protected static final int DEFAULT_CAPACITY = 10;
    protected static final Object[] EMPTY_ELEMENTDATA = {};
    private static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    protected Object[] data;
    protected int size;

    public AbstractArrayStructure() {
        this.data = EMPTY_ELEMENTDATA;
    }

    public AbstractArrayStructure(T[] data) {
        Objects.requireNonNull(data, "Can not create an array from null input");
        this.data = data;
        size = data.length;
    }

    protected void ensureCapacity(int capacity) {
        if (data == EMPTY_ELEMENTDATA) {
            capacity = Math.max(DEFAULT_CAPACITY, capacity);
        }
        if (capacity > data.length) {
            grow(capacity);
        }
    }

    protected void requireIndexInBounds(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index " + i + "out of bounds. Current size is " + size);
        }
    }

    private void grow(int requiredCapacity) {
        if (requiredCapacity < 0)
            throw new OutOfMemoryError();
        int newCapacity = data.length + (data.length / 2);
        if (requiredCapacity > newCapacity)
            newCapacity = requiredCapacity;
        if (newCapacity > MAX_ARRAY_LENGTH)
            newCapacity = MAX_ARRAY_LENGTH;
        data = Arrays.copyOf(data, newCapacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T object) {
        return indexOf(object) >= 0;
    }

    private int indexOf(T object) {
        for (int i = 0; i < size; i++)
            if (object == null ? data[i] == null : object.equals(data[i])) return i;
        return -1;
    }

    @Override
    public void add(T elem) {
        ensureCapacity(size + 1);
        data[size++] = elem;
    }

    @Override
    public void remove(T object) {
        int index = indexOf(object);
        if (index != -1) {
            System.arraycopy(data, index, data, index + 1, size - index - 1);
            data[size - 1] = null;
            size--;
        }
    }

}
