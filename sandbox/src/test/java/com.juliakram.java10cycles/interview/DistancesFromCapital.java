package com.juliakram.java10cycles.interview;


import java.util.*;

/**
 * Created by jkramr on 2/6/17.
 */
class Solution2 {
    public int[] solution(int[] T) {

        int M = T.length;

        Node capital = null;

        int[] distances = new int[M - 1];

        TreeMap<Integer, Node> map = new TreeMap<>();

        for (int i = 0; i < M; i++) {
            Node city = map.get(i);
            if (city == null) {
                city = new Node(i);
                map.put(i, city);
            }

            if (i == T[i]) {
                capital = city;
            } else {
                Node neighbor = map.get(T[i]);

                if (neighbor == null) {
                    neighbor = new Node(T[i]);
                    map.put(T[i], neighbor);
                }

                city.addNeighbor(neighbor);
                neighbor.addNeighbor(city);
            }
        }

        Queue<Node> bfsQueue = new PriorityQueue<>();

        int currentDepth = 0;
        bfsQueue.add(capital);

        while (!bfsQueue.isEmpty()) {
            Node current = bfsQueue.poll();
            currentDepth = current.depth;

            TreeSet<Node> neighbors = current.getNeighbors();

            for (Node neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    neighbor.depth = currentDepth + 1;
                    distances[currentDepth]++;
                    bfsQueue.add(neighbor);
                    current.setVisited();
                }
            }
        }

        return distances;
    }

    private class Node implements Comparable<Node> {
        private Integer id;
        private int depth;

        private TreeSet<Node> neighbors = new TreeSet<>();
        private boolean isVisited;

        public Node(int id) {
            this.id = id;
        }

        public void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }

        public void setVisited() {
            this.isVisited = true;
        }

        public boolean isVisited() {
            return isVisited;
        }

        public TreeSet<Node> getNeighbors() {
            return neighbors;
        }

        @Override
        public int compareTo(Node o) {
            return this.id.compareTo(o.id);
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }

        public boolean allNeighborsVisited() {
            return neighbors.stream().allMatch(Node::isVisited);
        }
    }

    public static void main(String[] args) {
        Solution2 algorithm = new Solution2();

        System.out.println(Arrays.toString(algorithm.solution(new int[]{9, 1, 4, 9, 0, 4, 8, 9, 0, 1})));
    }
}
