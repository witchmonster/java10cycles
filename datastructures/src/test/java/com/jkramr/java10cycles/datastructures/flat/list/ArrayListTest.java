package com.jkramr.java10cycles.datastructures.flat.list;

import com.jkramr.java10cycles.datastructures.DataStructure;

public class ArrayListTest
        extends ListTest {
  @Override
  protected DataStructure<String> createEmpty() {
    return new ArrayList<>();
  }
}
