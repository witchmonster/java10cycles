package com.jkramr.java10cycles.datastructures.flat.stack;

import com.jkramr.java10cycles.datastructures.flat.base.array.AbstractArrayStructure;

import java.util.EmptyStackException;

public class ArrayStack<T>
        extends AbstractArrayStructure<T>
        implements Stack<T> {


  public ArrayStack() {
    this.data = EMPTY_ELEMENTDATA;
  }

  public ArrayStack(int initialCapacity) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
    }
    this.data = new Object[initialCapacity];
  }

  public ArrayStack(T[] data) {
    super(data);
  }

  @Override
  @SuppressWarnings("unchecked")
  public T peek() {
    ensureNotEmpty();
    return (T) data[size - 1];
  }

  private void ensureNotEmpty() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
  }

  @Override
  public void push(T elem) {
    add(elem);
  }

  @Override
  @SuppressWarnings("unchecked")
  public T pop() {
    ensureNotEmpty();
    T top = (T) data[--size];
    data[size] = null;
    return top;
  }

}
