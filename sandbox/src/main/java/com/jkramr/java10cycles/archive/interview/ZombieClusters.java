package com.jkramr.java10cycles.archive.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by jkramr on 2/6/17.
 */
class ZombieClusters {

  static int zombieCluster(String[] zombies) {

    if (zombies.length < 1 || zombies.length > 300) {
      return 0;
    }

    HashMap<Integer, ZombieNode> zombieGraph = populateGraph(zombies);

    if (zombieGraph == null) {
      return 0;
    }

    return countGraphConnectedComponents(zombieGraph);
  }

  private static int countGraphConnectedComponents(HashMap<Integer, ZombieNode> zombieGraph) {
    TreeSet<ZombieNode> unvisitedNodes = new TreeSet<>();

    unvisitedNodes.addAll(zombieGraph.values());

    int connectedComponents = 0;

    while (!unvisitedNodes.isEmpty()) {

      ZombieNode current = unvisitedNodes.first();

      dfs(current, unvisitedNodes);

      connectedComponents++;
    }

    return connectedComponents;
  }

  private static void dfs(ZombieNode current, TreeSet<ZombieNode> unvisitedNodes) {
    unvisitedNodes.remove(current);
    int currentDepth = current.depth;

    for (ZombieNode neighbor : current.neighbors) {
      if (unvisitedNodes.contains(neighbor)) {
        neighbor.depth = currentDepth + 1;
        dfs(neighbor, unvisitedNodes);
      }
    }
  }

  private static HashMap<Integer, ZombieNode> populateGraph(String[] zombies) {
    HashMap<Integer, ZombieNode> graph = new HashMap<>();

    for (int i = 0; i < zombies.length; i++) {
      graph.put(i, new ZombieNode(i));
    }

    for (int i = 0; i < zombies.length; i++) {
      if (zombies[i].length() != zombies.length) {
        return null;
      }

      for (int j = i + 1; j < zombies.length; j++) { //matrix is symmetrical
        if (i != j && zombies[i].charAt(j) == '1') {
          ZombieNode node         = graph.get(i);
          ZombieNode neighborNode = graph.get(j);
          node.add(neighborNode);
          neighborNode.add(node);
        }
      }
    }

    return graph;
  }

  public static void main(String[] args) {

//      args = testCase1();

//      args = testCase2();
    
      args = testCase3();
    
      if (args.length < 2) {
      return;
    }

    int zombieCount = Integer.parseInt(args[0]);

    if (args.length < zombieCount + 1) {
      return;
    }

    String[] zombies = Arrays.copyOfRange(args, 1, args.length);

    System.out.println(zombieCluster(zombies));

  }
    
    private static String[] testCase1() {
        return new String[]{
                "5",
                "10000",
                "01000",
                "00100",
                "00010",
                "00001"};
    }
    
    private static String[] testCase2() {
        return new String[]{
                "4",
                "1000",
                "0100",
                "0011",
                "0011"};
    }
    
    private static String[] testCase3() {
        return new String[]{
                "7",
                "1100000",
                "1100000",
                "0010100",
                "0001100",
                "0011100",
                "0000010",
                "0000001"
        };
    }
    
    private static class ZombieNode implements Comparable{

    public  int                 depth;
    private Integer             value;
    private HashSet<ZombieNode> neighbors;

    public ZombieNode(int id) {
      neighbors = new HashSet<>();
      this.value = id;
    }

    public Integer getValue() {
      return value;
    }

    public void add(ZombieNode node) {
      neighbors.add(node);
    }
  
    @Override
    public int compareTo(final Object o) {
      return this.value.compareTo(((ZombieNode) o).getValue());
    }
  }
}
