package com.jkramr.java10cycles.interviews;

public class GcdMultipleNumbers {
    
    public static void main(String[] args) {
        System.out.println(calculateGcd(new int[]{2, 3}));
        System.out.println(calculateGcd(new int[]{4, 12, 32}));
        System.out.println(calculateGcd(new int[]{3, 15, 33}));
        System.out.println(calculateGcd(new int[]{0, 10, 5}));
        System.out.println(calculateGcd(new int[]{3585, 5404, 2652, 2755, 2400, 9933, 5061, 9677, 3369}));
    }
    
    private static int calculateGcd(final int[] arr) {
        if (arr.length == 0) {
            return 1;
        }
        
        if (arr.length == 1) {
            return arr[0];
        }
        
        int currentGcd = GcdTwoNumbers.calculateGcd(arr[0], arr[1]);
        for (int i = 1; i < arr.length - 1; i++) {
            currentGcd = GcdTwoNumbers.calculateGcd(currentGcd, arr[i + 1]);
            
            if (currentGcd == 1) {
                return 1;
            }
        }
        
        return currentGcd;
    }
}
