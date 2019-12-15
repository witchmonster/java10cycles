package com.jkramr.java10cycles.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/discuss/interview-question/373202
public class OptimalUtilization {
    public static void main(String[] args) {
//        sout(new int[][]{{1, 2}, {2, 4}, {3, 6}}, new int[][]{{1, 2}}, 7);
        sout(new int[][]{{1, 3}, {2, 5}, {3, 7}, {4, 10}}, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}, 10);
    }
    
    private static void sout(final int[][] a, final int[][] b, final int targetSum) {
        List<int[]> optimal = optimal(a, b, targetSum);
        for (int[] pair : optimal) {
            System.out.println(Arrays.toString(pair));
        }
    }
    
    private static List<int[]> optimal(final int[][] a, final int[][] b, final int targetSum) {
        
        Map<Integer, Integer> complementMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            complementMap.put(a[i][1], i);
        }
        
        return findOptimalComplement(a, b, complementMap, targetSum);
    }
    
    private static List<int[]> findOptimalComplement(final int[][] a, final int[][] b, final Map<Integer, Integer> complementMap, final int targetSum) {
        List<int[]> result = new ArrayList<>();
    
        int maxSum = 0;
        //O(targetSum*n)
        for (int i = 0; i < b.length; i++) {
            int currentValue = b[i][1];
            Integer complementIndex;
            int sum = targetSum;
            while (sum >= maxSum) {
                int complement = sum - currentValue;
                complementIndex = complementMap.get(complement);
                if (complementIndex != null) {
                    if (sum > maxSum) {
                        maxSum = sum;
                        result.clear();
                    }
                    result.add(new int[]{a[complementIndex][0], b[i][0]});
                }
                sum--;
            }
        }
        return result;
    }
    
}

