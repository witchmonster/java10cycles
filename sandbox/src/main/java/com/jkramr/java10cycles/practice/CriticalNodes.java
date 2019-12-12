package com.jkramr.java10cycles.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CriticalNodes {
    
    
    public static void main(String[] args) {
        System.out.println(getCriticalNodes(6, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}}));
    }
    
    private static List<Integer> getCriticalNodes(final int numNodes, final int[][] edges) {
        
        Map<Integer, HashSet<Integer>> neighborMap = buildNeighborMap(edges);
    
        List<Integer> criticalNodes = new ArrayList<>();
        for (int node = 0; node < numNodes; node++) {
            Map<Integer, Boolean> visited = new HashMap<>();
            Integer someNeighbor = cutNode(neighborMap, node);
            if (someNeighbor == null) {
                throw new IllegalArgumentException("disconnected graph in input");
            }
            dfs(visited, neighborMap, someNeighbor);
            if (visited.size() < numNodes - 1) {
                criticalNodes.add(node);
            }
            putNodeBack(neighborMap, node);
        }
        
        return criticalNodes;
    }
    
    private static Integer cutNode(final Map<Integer, HashSet<Integer>> neighborMap, final int node) {
        Integer someNeighbor = null;
        for (int neighbor : getNode(neighborMap, node)) {
            getNode(neighborMap, neighbor).remove(node);
            someNeighbor = neighbor;
        }
        return someNeighbor;
    }
    
    private static HashSet<Integer> getNode(final Map<Integer, HashSet<Integer>> neighborMap, final int node) {
        HashSet<Integer> neighbors = neighborMap.get(node);
        if (neighbors == null) {
            throw new IllegalArgumentException("disconnected graph in input");
        }
        return neighbors;
    }
    
    private static void putNodeBack(final Map<Integer, HashSet<Integer>> neighborMap, final int node) {
        for (int neighbor : getNode(neighborMap, node)) {
            getNode(neighborMap, neighbor).add(node);
        }
    }
    
    private static void dfs(final Map<Integer, Boolean> visited, final Map<Integer, HashSet<Integer>> neighborMap, final int node) {
        visited.put(node, true);
        for (int neighbor : getNode(neighborMap, node)) {
            if (!visited.containsKey(neighbor)) {
                dfs(visited, neighborMap, neighbor);
            }
        }
    }
    
    private static Map<Integer, HashSet<Integer>> buildNeighborMap(final int[][] edges) {
        Map<Integer, HashSet<Integer>> neighborMap = new HashMap<>();
        for (int[] edge : edges) {
            if (edge.length < 2) {
                throw new IllegalArgumentException("illegal edges input, edge should have exactly two nodes");
            }
            int a = edge[0];
            int b = edge[1];
            neighborMap.computeIfAbsent(a, nodeNotFound -> new HashSet<>());
            neighborMap.computeIfAbsent(b, nodeNotFound -> new HashSet<>());
            getNode(neighborMap, a).add(b);
            getNode(neighborMap, b).add(a);
        }
        return neighborMap;
    }
    
}
