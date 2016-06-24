package com.juliakram.core.cake;

import com.juliakram.core.TestableAlgorithm;

import java.util.HashMap;
import java.util.Map;

import model.constants.BigO;
import model.constants.Complexity;

import static model.constants.Complexity.of;

/**
 * https://www.interviewcake.com/question/java/temperature-tracker
 */
public interface TempTracker extends TestableAlgorithm<Integer> {

  static void main(String[] args) {
    new Linear().test();
  }

  void insert(int temp);

  double getMean();

  int getMode();

  int getMin();

  int getMax();

  @Override
  default void input(Integer... data) {
    for (Integer datum : data) {
      insert(datum);
    }
  }

  @Override
  default void output() {
    System.out.println("Mean: " + getMean());
    System.out.println("Max: " + getMax());
    System.out.println("Min: " + getMin());
    System.out.println("Mode: " + getMode());
  }

  @Override
  default void test() {
    test(1, 2, 3, 4, 5, 6, 2, 3);
    test(1, 2, 3, 4, 3);
  }

  class Linear implements TempTracker {
    private int max;
    private int min;
    private double sum;
    private int count;
    private int modeCount;
    private int mode;

    //0...110
    private Map<Integer, Integer> scores = new HashMap<>();

    @Override
    public Complexity complexity() {
      return of(BigO.LINEAR, BigO.CONSTANT);
    }

    @Override
    public void insert(int temp) {
      Integer score = putScore(temp);

      if (score > modeCount) {
        mode = temp;
        modeCount = score;
      }

      count++;

      if (temp > max) {
        max = temp;
      }

      if (temp < min) {
        min = temp;
      }

      sum += temp;
    }

    public Integer putScore(int temp) {
      Integer score = scores.get(temp);

      score = score == null ? 1 : ++score;

      scores.put(temp, score);

      return score;
    }

    @Override
    public double getMean() {
      return sum / count;
    }

    @Override
    public int getMode() {
      return mode;
    }

    @Override
    public int getMin() {
      return min;
    }

    @Override
    public int getMax() {
      return max;
    }

  }
}
