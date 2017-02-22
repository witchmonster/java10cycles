package com.juliakram.java10cycles.interview;


import java.util.*;

/**
 * Created by jkramr on 2/6/17.
 */
class OverloadedCallCenter {
    public void solve(String[] args) {

        if (args.length < 2) {
            return;
        }

        int executives = Integer.parseInt(args[0]);
        int dataPoints = Integer.parseInt(args[1]);

        if (args.length < 2 + dataPoints) {
            return;
        }

        List<Interval> calls = new ArrayList<>();

        for (int i = 2; i < 2 + dataPoints; i++) {
            String[] call = args[i].split(" ");

            calls.add(new Interval(call));
        }

        calls.sort(Comparator.comparing(o -> o.startTime));

        Interval previousCall = calls.get(0);

        int overlaps = 0;
        int maxOverlaps = 1;
        PriorityQueue<Interval> overlappingCalls = new PriorityQueue<>();
        overlappingCalls.add(calls.get(0));

        for (Interval call : calls) {
            if (!overlappingCalls.peek().overlaps(call)) {
                maxOverlaps = overlappingCalls.poll().overlapCount;
            } else {

            }
        }

        System.out.println(executives < overlaps ? overlaps - executives : 0);
    }

    private class Interval {

        private int overlapCount;
        private final Long startTime;
        private final Long endTime;


        public Interval(String[] call) {
            this.startTime = Long.valueOf(call[0]);
            this.endTime = Long.valueOf(call[1]);
            this.overlapCount = 1;
        }

        public boolean overlaps(Interval previousCall) {
            return this.endTime > previousCall.startTime;
        }
    }


    public static void main(String[] args) {
        OverloadedCallCenter algorithm = new OverloadedCallCenter();

        args = new String[]{
                new String("1"),
                new String("3"),
                new String("1481122000 1481122020"),
                new String("1481122000 1481122040"),
                new String("1481122030 1481122035")};

        algorithm.solve(args);
    }
}
