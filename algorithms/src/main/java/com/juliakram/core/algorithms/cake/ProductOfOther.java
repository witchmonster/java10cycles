package com.juliakram.core.algorithms.cake;

import com.juliakram.core.TestableAlgorithm;
import com.juliakram.core.common.ArrayOps;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import model.constants.BigO;
import model.constants.Complexity;

import static java.math.BigInteger.valueOf;

/**
 * https://www.interviewcake.com/question/java/product-of-other-numbers
 */
public interface ProductOfOther extends TestableAlgorithm<Integer> {

  static void main(String[] args) {
//    new Optimal().test();



    ArrayList<String> list = new ArrayList<>();
    list.add(null);
    list.add(null);
    list.add(null);
    list.add(null);

    System.out.println(list.stream().findAny());
  }

  BigInteger[] getProductsOfAllIntsExceptAtIndex(int[] ints);

  @Override
  default void test() {
    test(2, 7, 3, 4, 5);
    test(1, 7, 0, 4);
  }

  @Override
  default void printResult(Integer... data) {
    System.out.println(Arrays.toString(getProductsOfAllIntsExceptAtIndex(ArrayOps.autoUnbox(data))));
  }

  class Optimal implements ProductOfOther {

    @Override
    public BigInteger[] getProductsOfAllIntsExceptAtIndex(int[] ints) {

      int n = ints.length;
      if (n < 2) {
        return ArrayOps.toBigIntegerArray(ints);
      }

      BigInteger[] products = new BigInteger[n];

      BigInteger[] leftProduct = new BigInteger[n];
      BigInteger[] rightProduct = new BigInteger[n];

      leftProduct[0] = BigInteger.ONE;
      rightProduct[n - 1] = BigInteger.ONE;

      for (int i = 1; i < n; i++) {
        leftProduct[i] = leftProduct[i - 1].multiply(valueOf(ints[i]));
        rightProduct[n - i] = rightProduct[i - 1].multiply(valueOf(ints[i]));
      }

      for (int i = 0; i < n; i++) {
        products[i] = leftProduct[i].multiply(rightProduct[i]);
      }

      return products;
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.LINEAR);
    }
  }
}