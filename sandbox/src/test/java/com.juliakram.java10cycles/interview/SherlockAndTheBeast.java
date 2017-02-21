package com.juliakram.java10cycles.interview;


import java.util.HashMap;

/**
 * Created by jkramr on 2/6/17.
 */
class SherlockAndTheBeast {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println(-1);
        }

        int testCaseCount = Integer.parseInt(args[0]);

        for (int i = 0; i < testCaseCount; i++) {
            int digitCount = Integer.parseInt(args[i + 1]);

            printBiggestDecent(digitCount);
        }

    }

    private static boolean printBiggestDecent(int digitCount) {

        return false;
    }

    private static int countDigits(int x) {
        return (int) (Math.log10(x) + 1);
    }

}
