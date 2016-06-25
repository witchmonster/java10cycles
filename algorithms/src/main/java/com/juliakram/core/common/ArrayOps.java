package com.juliakram.core.common;

/**
 * Created by juliakram on 25/06/16.
 */
public interface ArrayOps {

  static int[] autoUnbox(Integer[] data) {
    int[] array = new int[data.length];

    for (int i = 0; i < data.length; i++) {
      array[i] = data[i];
    }

    return array;
  }

  static Integer[] autobox(int[] data) {
    Integer[] array = new Integer[data.length];

    for (int i = 0; i < data.length; i++) {
      array[i] = data[i];
    }

    return array;
  }
}
