package com.jkramr.java10cycles.archive.interview;

import java.util.*;

/**
 * Created by jkramr on 2/6/17.
 */
class DistancesFromCapital {
  public static void main(String[] args) {
    DistancesFromCapital algorithm = new DistancesFromCapital();

    System.out.println(Arrays.toString(algorithm.solution(new int[]{
            9,
            1,
            4,
            9,
            0,
            4,
            8,
            9,
            0,
            1
    })));
  }

  public int[] solution(int[] T) {

    int M = T.length;

    Node capital = null;

    int[] distances = new int[M - 1];

    HashMap<Integer, Node> map = new HashMap<>();

    for (int i = 0; i < M; i++) {

      map.putIfAbsent(i, new Node(i));
      Node city = map.get(i);

      if (i == T[i]) {
        capital = city;
      } else {
        map.putIfAbsent(T[i], new Node(T[i]));
        Node neighbor = map.get(T[i]);

        city.neighbors.add(neighbor);
        neighbor.neighbors.add(city);
      }
    }

    Queue<Node>   queue   = new PriorityQueue<>();
    HashSet<Node> visited = new HashSet<>();

    int currentDepth;
    queue.add(capital);
    visited.add(capital);

    while (!queue.isEmpty()) {
      Node current = queue.poll();
      currentDepth = current.depth;

      for (Node neighbor : current.neighbors) {
        if (!visited.contains(neighbor)) {
          neighbor.depth = currentDepth + 1;
          distances[currentDepth]++;
          queue.add(neighbor);
          visited.add(neighbor);
        }
      }
    }

    return distances;
  }

  private class Node
          implements Comparable<Node> {
    private Integer id;
    private int     depth;

    private HashSet<Node> neighbors = new HashSet<>();

    public Node(int id) {
      this.id = id;
    }

    @Override
    public int compareTo(Node o) {
      return this.id.compareTo(o.id);
    }
  }
}
