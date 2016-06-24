package com.juliakram.core;

import java.util.Arrays;

/**
 * Created by juliakram on 24/06/16.
 */
public interface TestableAlgorithm<I> extends Algorithm<I> {

  void test();

  default void test(I... data) {
    input(data);

    System.out.println("Input data: " + Arrays.toString(data));

    output();

    printComplexity();

    System.out.println();
  }

  void output();

  void input(I... data);

}
