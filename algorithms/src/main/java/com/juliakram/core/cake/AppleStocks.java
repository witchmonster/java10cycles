package com.juliakram.core.cake;

import com.juliakram.core.TestableAlgorithm;

import model.constants.Complexity;

/**
 * Created by juliakram on 25/06/16.
 */
public interface AppleStocks extends TestableAlgorithm<Integer> {

  int getMaxProfit(int[] stockPrices);

  @Override
  default void test() {

  }

  @Override
  default void run(Integer... data) {

  }

  class Optimal implements AppleStocks {
    @Override
    public int getMaxProfit(int[] stockPrices) {
      return 0;
    }

    @Override
    public Complexity complexity() {
      return null;
    }
  }

}
