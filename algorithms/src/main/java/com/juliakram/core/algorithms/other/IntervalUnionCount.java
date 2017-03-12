package com.juliakram.core.algorithms.other;

import java.util.Iterator;
import java.util.TreeSet;

public class IntervalUnionCount {

  public static final int MIN_INTERVAL_BOUND = -1000000000;
  public static final int MAX_INTERVAL_BOUND = 1000000000;
  public static final int MAX_N              = 100000;

  public int solution(int[] A, int[] B) {
    arraysShouldHaveSameLength(A, B);

    int N = A.length;

    lengthShouldBeWithinRange(N);

    TreeSet<Interval> intervals = composeIntervals(A, B); //O(N*log(N)) time; O(N) space

    return countUnionIntervals(intervals); //O(N) time; O(1) additional space
  }

  private int countUnionIntervals(TreeSet<Interval> sortedIntervals) {
    Iterator<Interval> iterator = sortedIntervals.iterator();

    if (!iterator.hasNext()) {
      return 0;
    }

    int countDisjointUnionIntervals = 1;

    Interval current = iterator.next();

    while (iterator.hasNext()) {
      Interval interval = iterator.next();

      if (!current.contains(interval)) {
        if (current.isDisjoint(interval)) {
          countDisjointUnionIntervals++;
          current = interval;
        } else {
          current.setB(interval.getB());
        }
      }
    }

    return countDisjointUnionIntervals;
  }

  private TreeSet<Interval> composeIntervals(int[] A, int[] B) {
    TreeSet<Interval> intervals = new TreeSet<>();

    for (int i = 0; i < A.length; i++) {
      shouldBeValidInterval(A[i], B[i]); //O(1)

      intervals.add(new Interval(A[i], B[i])); //O(log(N))
    }

    return intervals;
  }

  private void shouldBeValidInterval(int a, int b) {
    if (a < MIN_INTERVAL_BOUND || a > b || b > MAX_INTERVAL_BOUND) {
      throw new IllegalIntervalBoundsException();
    }
  }

  private void lengthShouldBeWithinRange(int length) {
    if (length > MAX_N) {
      throw new IllegalArrayLengthException();
    }
  }

  private void arraysShouldHaveSameLength(int[] A, int[] B) {
    if (A.length != B.length) {
      throw new ArraysHaveDifferentLengthException();
    }
  }

  private class ArraysHaveDifferentLengthException
          extends RuntimeException {
  }

  private class IllegalArrayLengthException
          extends RuntimeException {
  }

  private class IllegalIntervalBoundsException
          extends RuntimeException {
  }

  class Interval
          implements Comparable<Interval> {

    private int a;

    private int b;

    public Interval(int a, int b) {
      this.a = a;
      this.b = b;
    }

    public Integer getA() {
      return a;
    }

    public Integer getB() {
      return b;
    }

    public void setB(int b) {
      this.b = b;
    }

    @Override
    public int compareTo(Interval that) {
      return this.getA().compareTo(that.getA());
    }

    public boolean isDisjoint(Interval that) {
      return this.getB() < that.getA() || that.getB() < this.getA();
    }

    public boolean contains(Interval that) {
      return this.getA() <= that.getA() && this.getB() >= that.getB();
    }
  }
}
