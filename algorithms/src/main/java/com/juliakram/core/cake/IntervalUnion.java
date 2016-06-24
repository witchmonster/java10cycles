package com.juliakram.core.cake;

import com.juliakram.core.Algorithm;

import java.util.ArrayList;
import java.util.List;

import model.constants.BigO;
import model.constants.Complexity;

/**
 * https://www.interviewcake.com/question/java/merging-ranges
 */
public interface IntervalUnion extends Algorithm {

  List<Meeting> getUnion(List<Meeting> meetings);

  class Linear implements IntervalUnion {

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
