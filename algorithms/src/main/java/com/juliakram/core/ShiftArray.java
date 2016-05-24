package com.juliakram.core;

import model.constants.Complexity;

import java.util.Arrays;

public interface ShiftArray extends Algorithm {

    int[] shift(int[] array, int offset);

    static void main(String[] args) {
        ShiftArray polynomial = new Polynomial();

        test1(polynomial);
        test2(polynomial);
        test3(polynomial);

        System.out.println(-1 % 6);
    }

    class Polynomial implements ShiftArray {

        @Override
        public Complexity complexity() {
            return null;
        }

        @Override
        public int[] shift(int[] array, int offset) {
            offset = offset % array.length;

            if (offset == 0 || array.length < 2) {
                return array;
            }

            shift(array);

            return shift(array, offset - 1);
        }
        private void shift(int[] array) {
            int tmp = array[array.length - 1];

            for (int i = array.length - 1; i > 0; i--) {
                array[i] = array[i - 1];
            }

            array[0] = tmp;
        }

    }

    static void test1(ShiftArray shifter) {
        int[] array = {1, 2, 3, 4, 5, 6};
        int offset = 3;

        test(shifter, array, offset);
    }

    static void test2(ShiftArray shifter) {
        int[] array = {1, 2, 3, 4, 5, 6};
        int offset = 1;

        test(shifter, array, offset);
    }

    static void test3(ShiftArray shifter) {
        int[] array = {1, 2, 3, 4, 5, 6};
        int offset = -2;

        test(shifter, array, offset);
    }

    static void test(ShiftArray shifter, int[] array, int offset) {
        System.out.println(Arrays.toString(shifter.shift(array, offset)));
    }


}
