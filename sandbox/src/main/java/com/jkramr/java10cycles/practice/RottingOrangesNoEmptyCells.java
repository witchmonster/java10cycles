package com.jkramr.java10cycles.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//modified description:
//no empty cells allowed
// 1 - rotten
// 0 - fresh orange
//https://leetcode.com/problems/rotting-oranges/
public class RottingOrangesNoEmptyCells {
    public static void main(String[] args) {
        System.out.println(calculate(new int[][]{{1, 0, 0}, {1, 0, 0}, {0, 1, 0}}));
    }
    
    private static int calculate(final int[][] orangeMatrix) {
        
        //rows
        int n = orangeMatrix.length;
        //cols
        int m = orangeMatrix[0].length;
        
        LinkedList<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> depth = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (orangeMatrix[i][j] == 1) {
                    int code = getCode(m, i, j);
                    queue.add(code);
                    depth.put(code, 0);
                }
            }
        }
        
        int result = -1;
        
        while (!queue.isEmpty()) {
            int code = queue.remove();
            //safe because we never put on queue unless we put to map too
            int newDepth = depth.get(code) + 1;
            int i = code / m;
            int j = code % m;
            List<Integer> neighbors = getNeighbors(n, m, i, j, orangeMatrix);
            for (int ncode : neighbors) {
                int ni = ncode / m;
                int nj = ncode % m;
                if (orangeMatrix[ni][nj] == 0) {
                    orangeMatrix[ni][nj] = 1;
                    queue.add(ncode);
                    depth.put(ncode, newDepth);
                    result = newDepth;
                }
            }
        }
        
        return result;
    }
    
    private static List<Integer> getNeighbors(final int n, final int m, final int i, final int j, final int[][] orangeMatrix) {
        List<Integer> neighbors = new ArrayList<>();
        if (isFreshNeighbor(i, j + 1, n, m, orangeMatrix)) {
            neighbors.add(getCode(m, i, j + 1));
        }
        if (isFreshNeighbor(i, j - 1, n, m, orangeMatrix)) {
            neighbors.add(getCode(m, i, j - 1));
        }
        if (isFreshNeighbor(i - 1, j, n, m, orangeMatrix)) {
            neighbors.add(getCode(m, i - 1, j));
        }
        if (isFreshNeighbor(i + 1, j , n, m, orangeMatrix)) {
            neighbors.add(getCode(m, i + 1, j));
        }
        return neighbors;
    }
    
    private static boolean isFreshNeighbor(final int i, final int j, final int n, final int m, final int[][] orangeMatrix) {
        return i >= 0 && j >= 0 && i < n && j < m && orangeMatrix[i][j] == 0;
    }
    
    private static int getCode(final int m, final int i, final int j) {
        return i * m + j;
    }
}
