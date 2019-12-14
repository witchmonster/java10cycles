package com.jkramr.java10cycles.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/two-sum
public class TwoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
    
    private static int[] twoSum(final int[] ints, final int sum) {
        Map<Integer, Integer> compliments = new HashMap<>();
        for (int i = 0; i < ints.length; i++) {
            compliments.put(ints[i], i);
        }
        
        for (int i = 0; i < ints.length; i++) {
            int current = ints[i];
            Integer j = compliments.get(sum - current);
            if (j != null) {
                return new int[]{i, j};
            }
        }
        return null;
    }
}
