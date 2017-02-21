package com.juliakram.java10cycles.interview;


import java.util.Scanner;

/**
 * Created by jkramr on 2/6/17.
 */
class _EmptySolution {
    public int solution(int[] numbers) {
        return 0;
    }


    public static void main(String[] args) {
        _EmptySolution algorithm = new _EmptySolution();

        //reading from STDIN
        Scanner inputScanner = new Scanner(System.in);
        int a;
        a = inputScanner.nextInt();
        String b;
        b = inputScanner.nextLine();

        int[] integerArrayTestInput = {1, 1, 1, 1, 1};

        String[] stringArrayTestInput = new String[]{
                "1",
                "ftgyhujikl;",
                "sadfafsdjhk",
                "asdfukajhskdf",
        };

        System.out.println(algorithm.solution(integerArrayTestInput));
    }

}
