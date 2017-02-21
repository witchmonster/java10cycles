package com.juliakram.java10cycles.interview;


import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by jkramr on 2/6/17.
 */
class Solution4 {
    public void solve(String input) {

        String[] integers = input.split(" ");

        if (integers.length < 1) {
            return;
        }

        int previousInteger = Integer.parseInt(integers[0]);

        System.out.print(previousInteger + " ");

        for (int i = 1; i < integers.length; i++) {
            Integer integer = Integer.parseInt(integers[i]);

            Integer delta = integer - previousInteger;

            if (delta < -127 || delta > 127) {
                System.out.print(-128 + " ");
            }

            previousInteger = integer;

            System.out.print(delta + " ");
        }
    }

    public static void main(String[] args) {
        Solution4 algorithm = new Solution4();

        algorithm.solve("25626 25757 24367 24267 16 100 2 7277");
    }

}
