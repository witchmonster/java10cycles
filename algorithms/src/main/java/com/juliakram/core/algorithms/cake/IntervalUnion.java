package com.juliakram.core.algorithms.cake;

import com.juliakram.core.TestableAlgorithm;
import model.constants.BigO;
import model.constants.Complexity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.juliakram.core.algorithms.cake.IntervalUnion.Meeting.of;

/**
 * https://www.interviewcake.com/question/java/merging-ranges
 */
public interface IntervalUnion
        extends TestableAlgorithm<IntervalUnion.Meeting> {

  static void main(String[] args) {
    new Linear().test();
  }

  List<Meeting> getUnion(List<Meeting> meetings);

  default void test() {
    test(of(1, 2), of(3, 4), of(2, 3), of(1, 3));
    test(of(1, 2), of(9, 10), of(2, 4), of(1, 3));
  }

  default void printResult(Meeting... meetings) {
    System.out.println(getUnion(Arrays.asList(meetings)));
  }

  class Linear
          implements IntervalUnion {

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEARITHMIC, BigO.CONSTANT);
    }

    @Override
    public List<Meeting> getUnion(List<Meeting> meetings) {
      List<Meeting> union = new ArrayList<>();

      if (meetings.isEmpty()) {
        return union;
      }

      meetings.sort((m1, m2) -> m1.startTime >= m2.startTime ? 1 : -1);

      Meeting current = meetings.get(0);

      for (Meeting meeting : meetings) {
        if (!current.contains(meeting)) {
          if (current.isDisjoint(meeting)) {
            union.add(current);
            current = meeting;
          } else {
            current.endTime = meeting.endTime;
          }
        }
      }
      union.add(current);

      return union;
    }

  }

  class Meeting {

    int startTime;
    int endTime;

    public Meeting(int startTime, int endTime) {
      // number of 30 min blocks past 9:00 am
      this.startTime = startTime;
      this.endTime = endTime;
    }

    public static Meeting of(int low, int high) {
      return new Meeting(low, high);
    }

    public String toString() {
      return String.format("(%d, %d)", startTime, endTime);
    }

    public boolean contains(Meeting that) {
      return this.startTime >= that.startTime && this.endTime <= that.endTime;
    }

    public boolean isDisjoint(Meeting that) {
      return this.startTime > that.endTime || that.startTime > this.endTime;
    }
  }
}
