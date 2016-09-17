package com.juliakram.core.algorithms.other.arrays;

import com.juliakram.core.Algorithm;

import java.util.Arrays;

import model.constants.BigO;
import model.constants.Complexity;

public interface ShiftArray extends Algorithm {

  static void main(String[] args) {
//        test(new Polynomial());

    test(new Linear());
  }

  static void test(ShiftArray shift) {
    int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

//        printResult(shift, array, 0);
//        printResult(shift, array, 1);
//        printResult(shift, array, 2);
//        printResult(shift, array, 3);
    run(shift, array, 4);
    run(shift, array, 5);
    run(shift, array, 6);
    run(shift, array, 7);
    run(shift, array, 8);
  }

  static void run(ShiftArray shifter, int[] array, int offset) {
    System.out.println(shifter.getClass().getSimpleName() + output(shifter, array.clone(), offset));
  }

  static String output(ShiftArray shifter, int[] array, int offset) {
    return " shiftby " + offset + ": " +
            Arrays.toString(shifter.shift(array, offset));
  }

  int[] shift(int[] array, int offset);

  class Polynomial implements ShiftArray {

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.POLY_2, BigO.CONSTANT);
    }

    @Override
    public int[] shift(int[] array, int offset) {
      offset = offset % array.length;

      if (offset == 0 || array.length < 2) {
        return array;
      }

      shift(array);

      return shift(array, offset - 1);
    }

    private void shift(int[] array) {
      int tmp = array[array.length - 1];

      for (int i = array.length - 1; i > 0; i--) {
        array[i] = array[i - 1];
      }

      array[0] = tmp;
    }

  }

  class Linear implements ShiftArray {

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.CONSTANT);
    }

    @Override
    public int[] shift(int[] array, int offset) {
      if (array.length == 0) {
        return array;
      }

      int n = array.length;
      offset = mod(offset, n);

      int tmp = array[mod(n - offset, n)];
      int i = 0;
      int k = 0;

      for (int j = 0; j < n / k; j++) {
        int t = array[mod(i, n)];
        array[mod(i, n)] = tmp;
        tmp = t;
        i = mod(i + offset, n);
      }

      return array;
    }

    public int mod(int i, int n) {
      return i >= 0 ? i % n : (n + i) % n;
    }

    private int swap(int[] array, int i1, int i2) {
      int tmp = array[i1];
      array[i1] = array[i2];
      array[i2] = tmp;

      return tmp;
    }
  }

}
