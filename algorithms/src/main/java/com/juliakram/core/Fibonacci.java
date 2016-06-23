package com.juliakram.core;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public interface Fibonacci {

  public static void main(String[] args) {

  }

  int fib(int i);

  //time O(n), space O(1)
  class BinetFormula implements Fibonacci {

    @Override
    public int fib(int i) {
      double phi = (1 + sqrt(5)) / 2;

      return (int) ((pow(phi, i) - pow(1 - phi, i)) / sqrt(5));
    }
  }

  //time O(n), space O(1)
  class Dynamic implements Fibonacci {

    @Override
    public int fib(int i) {

      if (i < 0) {
        throw new IllegalArgumentException();
      }

      if (i == 0 || i == 1) {
        return i;
      }

      int prev = 0;
      int prevPrev = 1;
      int current = 0;

      for (int j = 0; j < i; j++) {
        current = prev + prevPrev;
        prevPrev = prev;
        prev = current;
      }

      return current;
    }
  }

  //time O(log(n)), space O(1)
//    class Matrix implements Fibonacci {
//
//        @Override
//        public int fib(int i) {
//            return 0; //TODO
//        }
//
//    }

  //time O(1), space O(1)
  class RecursiveMemo implements Fibonacci {

    Map<Integer, Integer> memo = new HashMap<>();

    @Override
    public int fib(int i) {
      if (i < 0) {
        throw new IllegalArgumentException();
      }

      if (i == 0 || i == 1) {
        return i;
      }

      if (memo.get(i) != null) {
        return memo.get(i);
      }

      int result = fib(i - 1) + fib(i - 2);

      memo.put(i, result);

      return result;
    }
  }

  //time O(exp(n)), space O(n)
  class Recursive implements Fibonacci {

    @Override
    public int fib(int i) {
      if (i < 0) {
        throw new IllegalArgumentException();
      }

      if (i == 0 || i == 1) {
        return i;
      }

      return fib(i - 1) + fib(i - 2);
    }
  }
}
