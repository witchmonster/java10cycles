package com.juliakram.core.algorithms.cake;

import com.juliakram.core.TestableAlgorithm;
import com.juliakram.core.common.ArrayOps;
import model.constants.BigO;
import model.constants.Complexity;

/**
 * https://www.interviewcake.com/question/java/stock-price
 */
public interface AppleStocks
        extends TestableAlgorithm<Integer> {

  static void main(String[] args) {
    new Greedy().test();
  }

  int getMaxProfit(int[] stockPrices);

  @Override
  default void test() {
    test(0);
    test(0, 0);
    test(2, 0);
    test(10, 9, 5, 8, 11, 9);
    test(10, 7, 5, 8, -11, 9, 4);
    test(1, 100, 20, 30, 10, 11);
  }

  @Override
  default void printResult(Integer... data) {
    System.out.println("Max profit: " + getMaxProfit(ArrayOps.autoUnbox(data)));
  }

  class Greedy
          implements AppleStocks {
    @Override
    public int getMaxProfit(int[] stockPrices) {
      if (stockPrices.length < 2) {
        System.out.println("Stock prices must have at least 2 entries");
        return 0;
      }

      int buy = stockPrices[0];

      int maxProfit = stockPrices[1] - buy;

      int currentProfit;

      for (int i = 2; i < stockPrices.length; i++) {
        if (stockPrices[i - 1] < buy) {
          buy = stockPrices[i - 1];
        }

        currentProfit = stockPrices[i] - buy;

        if (currentProfit > maxProfit) {
          maxProfit = currentProfit;
        }
      }

      return maxProfit;
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.CONSTANT);
    }
  }

}
