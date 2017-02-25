package com.juliakram.java10cycles.interview;


import java.util.Scanner;

/**
 * Created by jkramr on 2/6/17.
 */
class _EmptySolution {
    public Output solution(Input input) {
        return null;
    }


    public static void main(String[] args) {
        _EmptySolution algorithm = new _EmptySolution();

        //read from STDIN
        Input input = readFromSTDIN();

//        input = mockInput();

        System.out.println(algorithm.solution(input));
    }

    private static Input mockInput() {
        String[] lines;

        lines = new String[]{
                "1",
                "ftgyhujikl;",
                "sadfafsdjhk",
                "asdfukajhskdf",
        };

        return new Input(lines);
    }

    private static Input readFromSTDIN() {
        Scanner inputScanner = new Scanner(System.in);

        String[] firstLine = inputScanner.nextLine().split(" ");

        int inputCount = Integer.parseInt(firstLine[0]);

        String[] lines = new String[inputCount];

        for (int i = 0; i < inputCount; i++) {
            lines[i] = inputScanner.nextLine();
        }

        return new Input(lines);
    }

    private static class Input {
        private String[] lines;

        public Input(String[] lines) {
            this.lines = lines;
        }
    }

    private class Output {

        int value;

        @Override
        public String toString() {
            return "" + value;
        }
    }
}
