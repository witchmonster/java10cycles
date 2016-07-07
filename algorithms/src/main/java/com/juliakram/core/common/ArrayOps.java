package com.juliakram.core.common;

import java.math.BigInteger;

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

  static BigInteger[] toBigIntegerArray(int[] ints) {
    BigInteger[] result = new BigInteger[ints.length];

    for (int i = 0; i < ints.length; i++) {
      result[i] = BigInteger.valueOf(ints[i]);
    }

    return result;
  }
}
