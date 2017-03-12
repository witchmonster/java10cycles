package com.jkramr.java10cycles.datastructures.flat.list;

import com.jkramr.java10cycles.datastructures.flat.base.array.AbstractArrayStructure;

public class ArrayList<T>
        extends AbstractArrayStructure<T>
        implements List<T> {

  public ArrayList() {
  }

  public ArrayList(T[] data) {
    super(data);
  }

  @SuppressWarnings("unchecked")
  @Override
  public T get(int i) {
    requireIndexInBounds(i);
    return (T) data[i];
  }

  @Override
  public int indexOf(T object) {
    for (int i = 0; i < size; i++) {
      if (object == null ? data[i] == null : object.equals(data[i])) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public void set(int index, T object) {
    requireIndexInBounds(index);
    data[index] = object;
  }

  @Override
  public void add(T object) {
    ensureCapacity(size + 1);
    data[size++] = object;
  }

}
