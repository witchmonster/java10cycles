package com.jkramr.java10cycles.datastructures.flat.stack;

import com.jkramr.java10cycles.datastructures.flat.base.sequence.AbstractSequenceStructure;

import java.util.EmptyStackException;

public class SequenceStack<T>
        extends AbstractSequenceStructure<T>
        implements Stack<T> {

  public void push(T elem) {
    add(elem);
  }

  public T pop() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    T top = head.getDatum();
    remove(top);
    return top;
  }

  public T peek() {
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return head.getDatum();
  }
}
