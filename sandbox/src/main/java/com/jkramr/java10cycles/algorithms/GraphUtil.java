package com.jkramr.java10cycles.algorithms;

import java.util.*;

/**
 * Created by jkramr on 2/22/17.
 */
public class GraphUtil {

  public static void bfsIterative(TreeSet<? extends Node> unvisitedVertices) {
    Queue<Node> queue = new PriorityQueue<>();

    Node clusterRoot = unvisitedVertices.first();
    queue.add(clusterRoot);

    unvisitedVertices.remove(clusterRoot);

    while (!queue.isEmpty()) {
      Node node = queue.poll();

      TreeSet<Node> neighbours = node.getNeighbours();

      if (neighbours.isEmpty()) {
        return;
      }

      for (Node neighbor : neighbours) {
        if (unvisitedVertices.contains(neighbor)) {
          queue.add(neighbor);
          unvisitedVertices.remove(neighbor);
        }
      }
    }
  }

  public static void dfsIterative(TreeSet<? extends Node> unvisitedNodes) {
    Node current = unvisitedNodes.first();

    Stack<Node> stack = new Stack<>();
    stack.push(current);
    unvisitedNodes.remove(current);

    while (!stack.isEmpty()) {
      Node node = stack.peek();

      TreeSet<Node> neighbours = node.getNeighbours();

      if (neighbours.isEmpty()) {
        stack.pop();
      }

      for (Node neighbor : neighbours) {
        if (unvisitedNodes.contains(neighbor)) {
          stack.push(neighbor);
          unvisitedNodes.remove(neighbor);
        } else {
          stack.pop();
        }
      }
    }
  }

  public static void dfsRecursive(Node current, TreeSet<? extends Node> unvisitedNodes) {
    unvisitedNodes.remove(current);

    for (Node neighbor : current.getNeighbours()) {
      if (unvisitedNodes.contains(neighbor)) {
        dfsRecursive(neighbor, unvisitedNodes);
      }
    }
  }

  public static void dfsRecursiveWithDepth(
          Node current,
          TreeSet<? extends Node> unvisitedNodes
  ) {
    unvisitedNodes.remove(current);
    int currentDepth = current.depth;

    for (Node neighbor : current.getNeighbours()) {
      if (unvisitedNodes.contains(neighbor)) {
        neighbor.depth = currentDepth + 1;
        dfsRecursiveWithDepth(neighbor, unvisitedNodes);
      }
    }
  }

  public static void bfsWithDepth(TreeSet<? extends Node> unvisitedNodes) {
    Queue<Node> queue = new ArrayDeque<>();

    int currentDepth = 0;
    Node clusterRoot = unvisitedNodes.first();
    clusterRoot.depth = currentDepth;
    unvisitedNodes.remove(clusterRoot);
    queue.add(clusterRoot);


    while (!queue.isEmpty()) {
      Node current = queue.poll();
      currentDepth = current.depth;

      TreeSet<Node> neighbours = current.getNeighbours();

      if (neighbours.isEmpty()) {
        return;
      }

      for (Node neighbour : neighbours) {
        if (unvisitedNodes.contains(neighbour)) {
          neighbour.depth = currentDepth + 1;
          queue.add(neighbour);
          unvisitedNodes.remove(neighbour);
        }
      }
    }
  }

  private static class Node {

    protected TreeSet<Node> neighbours = new TreeSet<>();
    int depth;

    public void add(Node node) {
      neighbours.add(node);
    }

    public TreeSet<Node> getNeighbours() {
      return neighbours;
    }
  }
}
