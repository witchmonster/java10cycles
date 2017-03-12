package com.juliakram.core.algorithms.other.arrays;

import com.juliakram.core.TestableAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import model.constants.BigO;
import model.constants.Complexity;

import java.util.Arrays;

/**
 * Created by juliakram on 14/08/16.
 */
public interface LargestSumSub
        extends TestableAlgorithm<Integer[]> {

  static void main(String[] args) {
    new Linear().test();
  }

  LargestSumSubArray getLSSA(Integer[] array);

  @Override
  default void test() {
    test(new Integer[]{1, 3, 4, 5, 6, 7});
    test(new Integer[]{1, -3, 4, 5, -6, 7});
    test(new Integer[]{1, 3, 4, 5, 6, -7});
    test(new Integer[]{-1, -10});
    test(new Integer[]{});
  }

  @Override
  default void printResult(Integer[]... data) {
    for (int i = 0; i < data.length; i++) {
      LargestSumSubArray lssa = getLSSA(data[i]);
      System.out.println("Largest sum: "
                         +
                         lssa.getMaxSum()
                         +
                         "\nSub array: "
                         +
                         Arrays.toString(Arrays.copyOfRange(data,
                                                            lssa.getStart(),
                                                            lssa.getEnd()
                         )));
    }
  }

  class Linear
          implements LargestSumSub {

    @Override
    public LargestSumSubArray getLSSA(Integer[] array) {
      int n = array.length;

      if (n <= 0) {
        return null;
      }

      int globalMax = array[0];
      int curMax    = array[0];
      int curStart  = 0;
      int start     = 0;
      int end       = 0;

      for (int i = 1; i < n; i++) {
        if (curMax < 0) {
          curMax = array[i];
          curStart = i;
        } else {
          curMax += array[i];
        }

        if (curMax > globalMax) {
          globalMax = curMax;
          start = curStart;
          end = i;
        }
      }

      return new LargestSumSubArray(start, end, globalMax);
    }


    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.CONSTANT);
    }
  }

  @Data
  @AllArgsConstructor
  class LargestSumSubArray {
    private int start;
    private int end;
    private int maxSum;
  }
}
