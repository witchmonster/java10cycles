package com.juliakram.java10cycles.interview;


import java.util.*;

/**
 * Created by jkramr on 2/6/17.
 */
class ZombieClusters {

    static int zombieCluster(String[] zombies) {

        if (zombies.length < 1 || zombies.length > 300) {
            return 0;
        }

        Graph zombieGraph = populateGraph(zombies);

        if (zombieGraph == null) {
            return 0;
        }

        return dfsrCountConnectedComponents(zombieGraph);
    }

    private static int dfsrCountConnectedComponents(Graph zombieGraph) {
        TreeSet<Graph.Node> unvisitedNodes = new TreeSet<>();

        unvisitedNodes.addAll(zombieGraph.getNodes().values());

        int connectedComponents = 0;

        while (!unvisitedNodes.isEmpty()) {

            dfsi(unvisitedNodes);
//            dfs(unvisitedNodes);
//            bfs(unvisitedNodes);

            connectedComponents++;
        }

        return connectedComponents;
    }

    private static void dfsi(TreeSet<Graph.Node> unvisitedNodes) {
        Graph.Node current = unvisitedNodes.first();

        Stack<Graph.Node> stack = new Stack<>();
        stack.push(current);
        unvisitedNodes.remove(current);

        while (!stack.isEmpty()) {
            Graph.Node node = stack.peek();

            TreeSet<Graph.Node> neighbours = node.getNeighbours();

            if (neighbours.isEmpty()) {
                stack.pop();
            }

            for (Graph.Node neighbor : neighbours) {
                if (unvisitedNodes.contains(neighbor)) {
                    stack.push(neighbor);
                    unvisitedNodes.remove(neighbor);
                } else {
                    stack.pop();
                }
            }
        }
    }

    private static void bfs(TreeSet<Graph.Node> unvisitedVertices) {
        Queue<Graph.Node> queue = new PriorityQueue<>();

        Graph.Node clusterRoot = unvisitedVertices.first();
        queue.add(clusterRoot);

        unvisitedVertices.remove(clusterRoot);

        while (!queue.isEmpty()) {
            Graph.Node node = queue.poll();

            TreeSet<Graph.Node> neighbours = node.getNeighbours();
            if (neighbours.isEmpty()) {
                return;
            }

            for (Graph.Node neighbor : neighbours) {
                if (unvisitedVertices.contains(neighbor)) {
                    queue.add(neighbor);
                    unvisitedVertices.remove(neighbor);
                }
            }
        }
    }

    private static void dfs(TreeSet<Graph.Node> unvisitedNodes) {
        Graph.Node current = unvisitedNodes.first();

        unvisitedNodes.remove(current);

        for (Graph.Node neighbor : current.getNeighbours()) {
            if (unvisitedNodes.contains(neighbor)) {
                dfs(unvisitedNodes);
            }
        }
    }

    private static Graph populateGraph(String[] zombies) {
        Graph zombieGraph = new Graph(zombies.length);

        for (int i = 0; i < zombies.length; i++) {
            if (zombies[i].length() != zombies.length) {
                zombieGraph = null;
            }

            for (int j = i + 1; j < zombies.length; j++) { //matrix is symmetrical
                if (i != j && zombies[i].charAt(j) == '1') {
                    zombieGraph.add(i, j);
                }
            }
        }
        return zombieGraph;
    }


    private static class Graph {

        private HashMap<Integer, Node> nodes;

        public Graph(int nodeCount) {
            this.setNodes(new HashMap<>());
            for (int i = 0; i < nodeCount; i++) {
                Node node = new Node(i);
                getNodes().put(i, node);
            }
        }

        public void add(int i, int j) {
            Node node = getNodes().get(i);
            Node neighborNode = getNodes().get(j);
            node.add(neighborNode);
            neighborNode.add(node);
        }

        public HashMap<Integer, Node> getNodes() {
            return nodes;
        }

        void setNodes(HashMap<Integer, Node> nodes) {
            this.nodes = nodes;
        }

        private class Node implements Comparable<Node> {

            TreeSet<Node> neighbours;
            private Integer id;

            Node(Integer id) {
                this.id = id;
                this.neighbours = new TreeSet<>();
            }

            void add(Node node) {
                neighbours.add(node);
            }

            TreeSet<Node> getNeighbours() {
                return neighbours;
            }

            @Override
            public int compareTo(Node o) {
                return this.id.compareTo(o.getId());
            }

            Integer getId() {
                return id;
            }

        }
    }

    public static void main(String[] args) {

//        args = new String[]{
//                new String("5"),
//                new String("10000"),
//                new String("01000"),
//                new String("00100"),
//                new String("00010"),
//                new String("00001")};

//        args = new String[]{
//                new String("4"),
//                new String("1000"),
//                new String("0100"),
//                new String("0011"),
//                new String("0011")};

        args = new String[]{
                new String("7"),
                new String("1100000"),
                new String("1100000"),
                new String("0010100"),
                new String("0001100"),
                new String("0011100"),
                new String("0000010"),
                new String("0000001")};

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
}
