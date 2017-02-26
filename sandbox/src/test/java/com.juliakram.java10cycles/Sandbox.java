package com.juliakram.java10cycles;

/**
 * Created by jkramr on 2/26/17.
 */
public class Sandbox {
    public static void main(String[] args) {
        int bitArray;

        bitArray =  1 << 4;
        bitArray |= 1 << 2;
        System.out.println("dec: " + bitArray + ", bin: " + Integer.toBinaryString(bitArray));

        bitArray = bitArray >> 4;

        System.out.println("dec: " + bitArray + ", bin: " + Integer.toBinaryString(bitArray));
    }
}
