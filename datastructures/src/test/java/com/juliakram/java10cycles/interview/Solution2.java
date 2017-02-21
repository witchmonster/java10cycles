package com.juliakram.java10cycles.interview;


/**
 * Created by jkramr on 2/6/17.
 */
class Solution2 {
    public int summation(int[] numbers) {

        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            sum+=numbers[i];
        }

        return sum;
    }


    public static void main(String[] args) {
        Solution2 algorithm = new Solution2();

        System.out.println(algorithm.summation(new int[]{4, 2, 3, 5, 7}));
    }

}
