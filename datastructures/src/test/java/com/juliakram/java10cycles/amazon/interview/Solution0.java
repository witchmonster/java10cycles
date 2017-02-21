package com.juliakram.java10cycles.amazon.interview;


/**
 * Created by jkramr on 2/6/17.
 */
class Solution0 {
    public int solution(int[] A) {
        int equilibrium = -1;

        int n = A.length;

        if (n < 3) {
            return equilibrium;
        }

        long[] leftSum = new long[n];
        long[] rightSum = new long[n];

        long leftSumSoFar = 0;
        long rightSumSoFar = 0;

        for (int i = 1; i < n - 1; i++) {
            leftSumSoFar += A[i - 1];
            rightSumSoFar += A[n - i];
            leftSum[i] = leftSumSoFar;
            rightSum[n - i - 1] = rightSumSoFar;
        }

        for (int i = 1; i < n; i++) {
            if (leftSum[i] == rightSum[i]) {
                equilibrium = i;
            }
        }

        return equilibrium;
    }

    public static void main(String[] args) {
        Solution0 algorithm = new Solution0();

        System.out.println(algorithm.solution(new int[]{-1, 3, -4, 5, 1, -6, 2, 1}));
    }

}
