package com.juliakram.java10cycles.datastructures.flat.stack;

import com.juliakram.java10cycles.datastructures.flat.base.sequence.AbstractSequenceStructure;

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Created by yuliya.kramarenko on 30.11.2014.
 */
public class SequenceStack<T> extends AbstractSequenceStructure<T> implements Stack<T> {

    private final LinkedList<T> data;

    public SequenceStack() {
        this.data = new LinkedList<T>();
    }

    public SequenceStack(LinkedList<T> data) {
        this.data = data;
    }

    public void push(T elem) {
        data.add(elem);
    }

    public T pop() {
        if (data.size() == 0) {
            throw new EmptyStackException();
        }
        T top = data.get(data.size() - 1);
        data.remove(top);
        return top;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return data.get(data.size() - 1);
    }
}
