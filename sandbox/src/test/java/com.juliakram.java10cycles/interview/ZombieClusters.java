package com.juliakram.java10cycles.interview;

import com.juliakram.java10cycles.algorithms.GraphUtil;
import java.util.*;

import static com.juliakram.java10cycles.algorithms.GraphUtil.dfs;

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
        TreeSet<Graph.ZombieNode> unvisitedNodes = new TreeSet<>();

        unvisitedNodes.addAll(zombieGraph.getNodes().values());

        int connectedComponents = 0;

        while (!unvisitedNodes.isEmpty()) {

            dfs(unvisitedNodes);
//            dfs(unvisitedNodes);
//            bfs(unvisitedNodes);

            connectedComponents++;
        }

        return connectedComponents;
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

        private HashMap<Integer, ZombieNode> nodes;

        public Graph(int nodeCount) {
            this.setNodes(new HashMap<>());
            for (int i = 0; i < nodeCount; i++) {
                ZombieNode node = new ZombieNode(i);
                getNodes().put(i, node);
            }
        }

        public void add(int i, int j) {
            ZombieNode node = getNodes().get(i);
            ZombieNode neighborNode = getNodes().get(j);
            node.add(neighborNode);
            neighborNode.add(node);
        }

        public HashMap<Integer, ZombieNode> getNodes() {
            return nodes;
        }

        void setNodes(HashMap<Integer, ZombieNode> nodes) {
            this.nodes = nodes;
        }

        private class ZombieNode extends GraphUtil.Node implements Comparable<ZombieNode> {

            private Integer value;

            public ZombieNode(int id) {
                this.value = id;
            }

            @Override
            public int compareTo(ZombieNode o) {
                return this.getValue().compareTo(o.getValue());
            }

            public Integer getValue() {
                return value;
            }
        }
    }

    public static void main(String[] args) {

//        args = new String[]{
//                "5",
//                "10000",
//                "01000",
//                "00100",
//                "00010",
//                "00001"};

//        args = new String[]{
//                "4",
//                "1000",
//                "0100",
//                "0011",
//                "0011"};

        args = new String[]{
                "7",
                "1100000",
                "1100000",
                "0010100",
                "0001100",
                "0011100",
                "0000010",
                "0000001"};

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
