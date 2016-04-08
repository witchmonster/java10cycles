package com.juliakram.core;

import model.constants.Complexity;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public interface Fibonacci {
    int fib(int i);

    abstract class Abstract extends AbstractAlgorithm<Integer, Integer> implements Fibonacci {

        public Abstract(Complexity oTime, Complexity oSpace) {
            super(oTime, oSpace);
        }

        @Override
        public Integer solve(Integer input) {
            return fib(input);
        }
    }

    class BinetFormula extends Abstract {

        public BinetFormula() {
            super(Complexity.LINEAR, Complexity.CONSTANT);
        }

        @Override
        public int fib(int i) {
            double phi = (1 + sqrt(5)) / 2;

            return (int) ((pow(phi, i) - pow(1 - phi, i)) / sqrt(5));
        }
    }

    class Dynamic extends Abstract {

        public Dynamic() {
            super(Complexity.LINEAR, Complexity.CONSTANT);
        }

        @Override
        public int fib(int i) {

            if (i < 0) {
                throw new IllegalArgumentException();
            }

            if (i == 0 || i == 1) {
                return i;
            }

            int prev = 0;
            int prevPrev = 1;
            int current = 0;

            for (int j = 0; j < i; j++) {
                current = prev + prevPrev;
                prevPrev = prev;
                prev = current;
            }

            return current;
        }
    }

    class Matrix extends Abstract {

        public Matrix() {
            super(Complexity.LOGARITHMIC, Complexity.CONSTANT);
        }

        @Override
        public int fib(int i) {
            return 0; //TODO
        }

    }

    class RecursiveMemo extends Abstract {
        Map<Integer, Integer> memo = new HashMap<>();

        public RecursiveMemo() {
            super(Complexity.CONSTANT, Complexity.CONSTANT);
        }

        @Override
        public int fib(int i) {
            if (i < 0) {
                throw new IllegalArgumentException();
            }

            if (i == 0 || i == 1) {
                return i;
            }

            if (memo.get(i) != null) {
                return memo.get(i);
            }

            int result = fib(i - 1) + fib(i - 2);

            memo.put(i, result);

            return result;
        }
    }

    class Recursive extends Abstract {

        public Recursive() {
            super(Complexity.EXPONENTIAL, Complexity.LINEAR);
        }

        @Override
        public int fib(int i) {
            if (i < 0) {
                throw new IllegalArgumentException();
            }

            if (i == 0 || i == 1) {
                return i;
            }

            return fib(i - 1) + fib(i - 2);
        }
    }
}
