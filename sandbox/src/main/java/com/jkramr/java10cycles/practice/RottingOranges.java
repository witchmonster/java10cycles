package com.jkramr.java10cycles.practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/rotting-oranges/
public class RottingOranges {
    public static void main(String[] args) {
        System.out.println(calculate(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        System.out.println(calculate(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
    }
    
    private static int calculate(final int[][] orangeMatrix) {
        
        if (orangeMatrix.length == 0 || orangeMatrix[0].length == 0) {
            return -1;
        }
        
        //additional O(n*m) space
        int[][] matrix = orangeMatrix;
        int n = matrix.length;
        int m = matrix[0].length;
        
        //worst case O(n), n > m or O(m), m > n space
        Map<Integer, Integer> timeDepth = new HashMap<>();
        //worst case O(n*m) space
        LinkedList<Integer> queue = new LinkedList<>();
        //O(n*m)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 2) {
                    final int code = i * m + j;
                    queue.add(code);
                    timeDepth.put(code, 0);
                }
            }
        }
        
        int result = -1;
        
        //O(n*m)
        while (!queue.isEmpty()) {
            int current = queue.remove();
            final int newDepth = timeDepth.get(current) + 1;
            int i = current / m;
            int j = current % m;
            List<Integer> neighbors = findFreshNeighbors(i, j, matrix);
            for (int neighbor : neighbors) {
                int ni = neighbor / m;
                int nj = neighbor % m;
                if (matrix[ni][nj] == 1) {
                    matrix[ni][nj] = 2;
                    final int ncode = ni * m + nj;
                    queue.add(ncode);
                    timeDepth.put(ncode, newDepth);
                    result = newDepth;
                }
            }
        }
    
        //O(n*m)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    return -1;
                }
            }
        }
        
        return result;
    }
    
    private static List<Integer> findFreshNeighbors(final int i, final int j, final int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        final List<Integer> neighbors = new LinkedList<>();
        if (i - 1 >= 0 && matrix[i - 1][j] == 1) {
            neighbors.add((i - 1) * m + j);
        }
        if (i + 1 < n && matrix[i + 1][j] == 1) {
            neighbors.add((i + 1) * m + j);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] == 1) {
            neighbors.add(i * m + j - 1);
        }
        if (j + 1 < m && matrix[i][j + 1] == 1) {
            neighbors.add(i * m + j + 1);
        }
        return neighbors;
    }
}
