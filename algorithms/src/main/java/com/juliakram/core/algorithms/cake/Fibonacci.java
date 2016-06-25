package com.juliakram.core.algorithms.cake;

import com.juliakram.core.TestableAlgorithm;
import com.juliakram.core.algorithms.other.BigSqrt;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import model.constants.BigO;
import model.constants.Complexity;

/**
 * https://www.interviewcake.com/question/java/nth-fibonacci
 */
public interface Fibonacci extends TestableAlgorithm<Integer> {

  static void main(String[] args) {
    new BinetFormula().test();
    new Dynamic().test();
    new RecursiveMemo().test();
//    new Recursive().test();
  }

  BigInteger fib(int i);

  @Override
  default void test() {
    test(1, 2, 3, 10, 20, 90);
  }

  @Override
  default void out(Integer... data) {
    for (Integer datum : data) {
      System.out.println("fib[" + datum + "]: " + fib(datum));
    }
  }

  class BinetFormula implements Fibonacci {

    private BigSqrt newtonRaphson = new BigSqrt.NewtonRaphson();

    @Override
    public BigInteger fib(int i) {
      BigDecimal sqrt5 =  newtonRaphson.bigSqrt(BigDecimal.valueOf(5), 150);

      BigDecimal phi = sqrt5
              .add(BigDecimal.ONE)
              .divide(BigDecimal.valueOf(2), MathContext.UNLIMITED);

      MathContext precision = new MathContext(300, RoundingMode.HALF_EVEN);

      //      (pow(phi, i) - pow(1 - phi, i)
      //               / sqrt(5));
      return phi
              .pow(i)
              .subtract(phi
                      .subtract(BigDecimal.ONE)
                      .pow(i))
              .divide(sqrt5, precision)
              .toBigInteger();
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.MOFN, BigO.CONSTANT);
    }
  }

  class Dynamic implements Fibonacci {

    @Override
    public BigInteger fib(int i) {

      if (i < 0) {
        throw new IllegalArgumentException();
      }

      if (i == 0 || i == 1) {
        return BigInteger.valueOf(i);
      }

      BigInteger prev = BigInteger.ZERO;
      BigInteger prevPrev = BigInteger.ONE;
      BigInteger current = BigInteger.ZERO;

      for (int j = 0; j < i; j++) {
        current = prev.add(prevPrev);
        prevPrev = prev;
        prev = current;
      }

      return current;
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.CONSTANT);
    }
  }

  class Matrix implements Fibonacci {

    @Override
    public BigInteger fib(int i) {
      throw new NotImplementedException();//TODO
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LOGARITHMIC, BigO.CONSTANT);
    }
  }

  class RecursiveMemo implements Fibonacci {

    Map<BigInteger, BigInteger> memo = new HashMap<>();

    @Override
    public BigInteger fib(int i) {
      if (i < 0) {
        throw new IllegalArgumentException();
      }

      BigInteger bi = BigInteger.valueOf(i);

      if (i == 0 || i == 1) {
        return bi;
      }

      if (memo.get(bi) != null) {
        return memo.get(bi);
      }

      BigInteger result = fib(i - 1).add(fib(i - 2));

      memo.put(bi, result);

      return result;
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.CONSTANT, BigO.CONSTANT);
    }
  }

  class Recursive implements Fibonacci {

    @Override
    public BigInteger fib(int i) {
      if (i < 0) {
        throw new IllegalArgumentException();
      }

      BigInteger bi = BigInteger.valueOf(i);

      if (i == 0 || i == 1) {
        return bi;
      }

      return fib(i - 1).add(fib(i - 2));
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.EXPONENTIAL, BigO.LINEAR);
    }
  }
}
