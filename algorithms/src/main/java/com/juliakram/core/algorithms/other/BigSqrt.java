package com.juliakram.core.algorithms.other;

import com.juliakram.core.TestableAlgorithm;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import model.constants.BigO;
import model.constants.Complexity;

/**
 * Created by juliakram on 25/06/16.
 */
public interface BigSqrt extends TestableAlgorithm<BigDecimal> {

  static void main(String[] args) {
    new NewtonRaphson().test();
  }

  BigDecimal bigSqrt(BigDecimal c, int precision);

  @Override
  default void test() {
    test(
            BigDecimal.ONE,
            BigDecimal.valueOf(100),
            BigDecimal.valueOf(2)
    );
  }

  @Override
  default void printResult(BigDecimal... data) {
    for (BigDecimal datum : data) {
      System.out.println("sqrt(" + datum + ")=" + bigSqrt(datum, 100));
    }
  }

  class NewtonRaphson implements BigSqrt {

    /**
     * Private utility method used to compute the square root of a BigDecimal.
     *
     * @author Luciano Culacciatti
     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
     */
    private static BigDecimal sqrtNewtonRaphson(BigDecimal c, BigDecimal xn, int precision) {
      BigDecimal fx = xn.pow(2).add(c.negate());

      BigDecimal fpx = xn.multiply(BigDecimal.valueOf(2));

      BigDecimal xn1 = fx.divide(fpx, 2 * precision, RoundingMode.HALF_DOWN);

      xn1 = xn.add(xn1.negate());

      if (isPreciseEnough(c, precision, xn1)) {
        return xn1;
      } else {
        return sqrtNewtonRaphson(c, xn1, precision);
      }
    }

    private static boolean isPreciseEnough(BigDecimal c, int precision, BigDecimal xn1) {
      BigDecimal currentPrecision = xn1.pow(2).subtract(c).abs();

      BigDecimal coPrecision = BigDecimal.ONE.divide(BigDecimal.TEN.pow(precision), MathContext.UNLIMITED);

      return currentPrecision.compareTo(coPrecision) <= -1;
    }

    /**
     * Uses Newton Raphson to compute the square root of a BigDecimal.
     *
     * @author Luciano Culacciatti
     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
     */
    @Override
    public BigDecimal bigSqrt(BigDecimal c, int precision) {
      return sqrtNewtonRaphson(c, BigDecimal.ONE, precision);
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.MOFN, BigO.CONSTANT);
    }
  }

}
