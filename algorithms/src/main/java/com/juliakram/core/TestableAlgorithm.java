package com.juliakram.core;

import java.util.Arrays;

/**
 * Created by juliakram on 24/06/16.
 */
public interface TestableAlgorithm<I> extends Algorithm {

  void test();

  default void test(I... data) {
    System.out.println("Test for " + getClass().getName());
    System.out.println("Input data: " + Arrays.toString(data));

    out(data);

    printComplexity();

    System.out.println();
  }

  void out(I... data);
}
