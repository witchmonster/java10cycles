package com.jkramr.java10cycles.datastructures.flat.stack;

public class SequenceStackTest
        extends StackTest {

  @Override
  protected Stack<String> createEmpty() {
    return new SequenceStack<>();
  }
}
