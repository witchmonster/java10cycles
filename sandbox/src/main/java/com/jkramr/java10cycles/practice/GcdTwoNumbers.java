package com.jkramr.java10cycles.practice;

public class GcdTwoNumbers {
    
    public static void main(String[] args) {
        System.out.println(calculateGcd(4, 6));
        System.out.println(calculateGcd(4, 8));
        System.out.println(calculateGcd(12, 3));
        System.out.println(calculateGcd(0, 10));
    }
    
    public static int calculateGcd(final int a, final int b) {
        if (a == 0) {
            return b;
        }
        
        if (b == 0) {
            return a;
        }
        
        if (a == b) {
            return a;
        }
        
        if (a > b) {
            return calculateGcd(a - b, b);
        } else {
            return calculateGcd(a, b - a);
        }
    }
}
