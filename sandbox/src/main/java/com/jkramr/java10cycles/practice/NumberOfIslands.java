package com.jkramr.java10cycles.practice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/number-of-islands/
public class NumberOfIslands {
    public static void main(String[] args) {
        System.out.println(countIslands(new int[][]{{0, 1, 1, 1}, {0, 0, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}}));
        System.out.println(countIslands(new int[][]{{1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}}));
    }
    
    private static int countIslands(final int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int islandCount = 0;
        HashSet<Integer> visited = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int code = code(i, j, m);
                if (matrix[i][j] == 1 && !visited.contains(code)) {
                    dfs(visited, code, matrix);
                    islandCount++;
                }
            }
        }
        
        return islandCount;
    }
    
    private static void dfs(final HashSet<Integer> visited, final int code, final int[][] matrix) {
        int m = matrix[0].length;
        visited.add(code);
        int i = decodeI(code, m);
        int j = decodeJ(code, m);
        List<Integer> unvisitedLandNeighbors = findUnvisitedLandNeighbors(i, j, matrix);
        System.out.println(unvisitedLandNeighbors);
        for (int ncode : unvisitedLandNeighbors) {
            if (!visited.contains(ncode)) {
                dfs(visited, ncode, matrix);
            }
        }
    }
    
    private static List<Integer> findUnvisitedLandNeighbors(final int i, final int j, final int[][] matrix) {
        final List<Integer> neighbors = new LinkedList<>();
        checkNeighbor(i - 1, j, matrix, neighbors);
        checkNeighbor(i + 1, j, matrix, neighbors);
        checkNeighbor(i, j - 1, matrix, neighbors);
        checkNeighbor(i, j + 1, matrix, neighbors);
        return neighbors;
    }
    
    private static void checkNeighbor(final int i, final int j, final int[][] matrix, final List<Integer> list) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (i >= 0 && j >= 0 && i < n && j < m && matrix[i][j] == 1) {
            list.add(code(i, j, m));
        }
    }
    
    private static int decodeI(final int code, final int m) {
        return code / m;
    }
    
    private static int decodeJ(final int code, final int m) {
        return code % m;
    }
    
    private static int code(final int i, final int j, final int m) {
        return i * m + j;
    }
}
