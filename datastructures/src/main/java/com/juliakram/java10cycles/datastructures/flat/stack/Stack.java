package com.juliakram.java10cycles.datastructures.flat.stack;

import com.juliakram.java10cycles.datastructures.DataStructure;

public interface Stack<T> extends DataStructure<T> {

  T peek();

  void push(T elem);

  T pop();


  boolean contains(T object);

  boolean isEmpty();

  int size();

}
