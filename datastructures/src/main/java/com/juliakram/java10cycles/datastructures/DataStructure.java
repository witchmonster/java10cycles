package com.juliakram.java10cycles.datastructures;

public interface DataStructure<T> {

  void add(T object);

  void remove(T object);

  boolean contains(T object);

  boolean isEmpty();

  int size();
}
