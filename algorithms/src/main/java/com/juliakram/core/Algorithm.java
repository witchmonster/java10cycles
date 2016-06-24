package com.juliakram.core;

import model.constants.Complexity;

/**
 * Created by juliakram on 22/05/16.
 */
public interface Algorithm<I> {

  Complexity complexity();

  default void printComplexity() {
    System.out.println("Complexity: " + complexity());
  }

}
