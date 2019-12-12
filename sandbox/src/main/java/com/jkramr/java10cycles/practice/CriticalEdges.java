package com.jkramr.java10cycles.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/critical-connections-in-a-network/
public class CriticalEdges {
    
    private static Map<Integer, Boolean> visited;
    
    public static void main(String[] args) {
        int nodes = 10;
        System.out.println(getCriticalConnections(nodes, createList(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {3, 6}, {6,7}, {7, 8}, {8, 9}, {6, 9}, {9, 10},{1, 3}}, nodes)));
    }
    
    private static List<Integer> createList(final int[][] connections, final int nodes) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < connections.length; i++) {
            list.add(code(connections[i][0], connections[i][1], nodes));
        }
        return list;
    }
    
    private static List<Integer> getCriticalConnections(final int nodes, final List<Integer> connections) {
        final List<Integer> list = new ArrayList<>();
        
        Map<Integer, HashSet<Integer>> neighborMap = buildNeighborMap(connections, nodes);
        
        for (Integer connection : connections) {
            visited = new HashMap<>();
            int a = decodeA(connection, nodes);
            int b = decodeB(connection, nodes);
            neighborMap.get(a).remove(b);
            neighborMap.get(b).remove(a);
            System.out.println(String.format("cutting [%s, %s] starting at %s", a, b, a));
            dfs(neighborMap, a);
            if (visited.size() != nodes) {
                list.add(connection);
            }
            neighborMap.get(a).add(b);
            neighborMap.get(b).add(a);
        }
        
        return list;
    }
    
    //O(n) time and space
    private static Map<Integer, HashSet<Integer>> buildNeighborMap(final List<Integer> connections, final int nodes) {
        Map<Integer, HashSet<Integer>> neighborMap = new HashMap<>();
        for (Integer connection : connections) {
            int a = decodeA(connection, nodes);
            int b = decodeB(connection, nodes);
            neighborMap.computeIfAbsent(a, k -> new HashSet<>());
            neighborMap.get(a).add(b);
            neighborMap.computeIfAbsent(b, k -> new HashSet<>());
            neighborMap.get(b).add(a);
        }
        return neighborMap;
    }
    
    public static void dfs(Map<Integer, HashSet<Integer>> neighborMap, int node) {
        visited.put(node, true);
        System.out.println(node);
        HashSet<Integer> neighbors = neighborMap.get(node);
        if (neighbors.size() != 0) {
            for (int neighbor : neighbors) {
                if (!visited.containsKey(neighbor)) {
                    dfs(neighborMap, neighbor);
                }
            }
        }
    }
    
    private static int decodeA(final int code, final int nodes) {
        return code / (nodes * 10);
    }
    
    private static int decodeB(final int code, final int nodes) {
        return code % (nodes * 10);
    }
    
    private static int code(final int a, final int b, final int nodes) {
        return a * nodes * 10 + b;
    }
}
