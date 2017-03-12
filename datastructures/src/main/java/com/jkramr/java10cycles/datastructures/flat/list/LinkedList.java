package com.jkramr.java10cycles.datastructures.flat.list;

import com.jkramr.java10cycles.datastructures.flat.base.sequence.AbstractSequenceStructure;

public class LinkedList<T>
        extends AbstractSequenceStructure<T>
        implements List<T> {

  @Override
  public T get(int index) {
    requireIndexInBounds(index);
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next();
    }
    return current.getDatum();
  }

  protected void requireIndexInBounds(int i) {
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException("Index " +
                                          i +
                                          "out of bounds. Current size is " +
                                          size);
    }
  }

  @Override
  public void set(int index, T object) {
    requireIndexInBounds(index);
    Node<T> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next();
    }
    current.setDatum(object);
  }

  @Override
  public int indexOf(T object) {
    if (contains(object)) {
      Node<T> current = head;
      for (int i = 0; i < size(); i++) {
        if (object == null ? current.getDatum() == null : object.equals(current.getDatum())) {
          return i;
        }
        current = current.next();
      }
    }
    return -1;
  }
}
