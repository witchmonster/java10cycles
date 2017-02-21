package com.juliakram.java10cycles.interview;


/**
 * Created by jkramr on 2/6/17.
 */
class Solution6 {
    public static void solution(int n) {

        for (int i = 1; i <= n; i++) {
            printPyramidRow(i, n);
        }
    }

    private static void printPyramidRow(int i, int n) {
        for (int j = 0; j < n - i; j++) { //todo bounds
            System.out.print(' ');
        }

        for (int j = n - i; j < n; j++) { //todo bounds
            System.out.print('#');
        }

        System.out.print('\n');
    }

    public static void main(String[] args) {
        Solution6 algorithm = new Solution6();

        solution(4);
    }

}
