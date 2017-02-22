package com.juliakram.java10cycles.interview;

import com.juliakram.java10cycles.algorithms.GraphUtil;

import java.util.*;

import static com.juliakram.java10cycles.algorithms.GraphUtil.dfsRecursive;
import static com.juliakram.java10cycles.algorithms.GraphUtil.dfsRecursiveWithDepth;

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

//            dfsIterative(unvisitedNodes);
//            bfsIterative(unvisitedNodes);
//            bfsWithDepth(unvisitedNodes);
//            dfsRecursive(unvisitedNodes.first(), unvisitedNodes);
            dfsRecursiveWithDepth(unvisitedNodes.first(), unvisitedNodes);

            connectedComponents++;
        }

        return connectedComponents;
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
                    ZombieNode node = graph.get(i);
                    ZombieNode neighborNode = graph.get(j);
                    node.add(neighborNode);
                    neighborNode.add(node);
                }
            }
        }

        return graph;
    }


    private static class ZombieNode
            extends GraphUtil.Node
            implements Comparable<ZombieNode> {

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
