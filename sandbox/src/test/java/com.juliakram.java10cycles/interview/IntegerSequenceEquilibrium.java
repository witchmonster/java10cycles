package com.juliakram.java10cycles.interview;


/**
 * Created by jkramr on 2/6/17.
 */
class IntegerSequenceEquilibrium {
    public int solution(int[] A) {

        int n = A.length;

        if (n < 3) {
            return -1;
        }

        long[] leftSum = new long[n];
        long[] rightSum = new long[n];

        leftSum[0] = 0;
        rightSum[n - 1] = 0;

        for (int i = 1; i < n - 1; i++) {
            leftSum[i] += leftSum[i - 1] + A[i - 1];
            rightSum[n - i - 1] += rightSum[n - i] + A[n - i];
        }

        for (int i = 1; i < n - 2; i++) {
            if (rightSum[i] == leftSum[i]) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        IntegerSequenceEquilibrium algorithm = new IntegerSequenceEquilibrium();

        System.out.println(algorithm.solution(new int[]{-1, 3, -4, 5, 1, -6, 2, 1}));
        System.out.println(algorithm.solution(new int[]{1, 2, -3, 3, -1, -2, 5}));
    }

}
