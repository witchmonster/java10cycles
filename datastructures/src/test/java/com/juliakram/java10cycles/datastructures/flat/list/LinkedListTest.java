package com.juliakram.java10cycles.datastructures.flat.list;

import com.juliakram.java10cycles.datastructures.DataStructure;

public class LinkedListTest extends ListTest {
  @Override
  protected DataStructure<String> createEmpty() {
    return new LinkedList<>();
  }
}
