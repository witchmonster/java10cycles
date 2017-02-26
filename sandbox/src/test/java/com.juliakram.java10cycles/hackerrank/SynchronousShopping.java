package com.juliakram.java10cycles.hackerrank;

import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/synchronous-shopping
 */
class SynchronousShopping {

    public static void main(String[] args) {
        mockInput();

        //read from STDIN
        Input input = readFromSTDIN();

        Dijekstra dijekstra = new Dijekstra(input);

        dijekstra.traverse();

        System.out.println((Output) null);
    }

    private static class Dijekstra {

        private ShoppingCenter start;
        private HashMap<ShoppingCenter, ShoppingCenter> breadcrubms;

        private KittyQueue queue;

        public Dijekstra(Input input) {
            start = input.shoppingCenters.get(1);

            start.distance = 0;

            breadcrubms = new HashMap<>();

            queue = new KittyQueue();

            input.shoppingCenters.values().forEach(sc -> {
                if (!sc.equals(start)) {
                    breadcrubms.put(sc, null);
                    sc.distance = Integer.MAX_VALUE;
                }

                queue.add(sc);
            });
        }

        public void traverse() {
            while (!queue.isEmpty()) {
                ShoppingCenter current = queue.poll();
                Integer currentDistanceFromStart = current.distance;

                current.neighbors.forEach((neighbor, distance) -> {
                    int distanceFromStart = currentDistanceFromStart + distance;
                    if (distanceFromStart < neighbor.distance) {
                        breadcrubms.put(neighbor, current);

                        queue.changePriority(neighbor, distanceFromStart);
                    }
                });
            }
        }

    }

    private static class KittyQueue extends PriorityQueue<ShoppingCenter> {

        public KittyQueue() {
            super(Comparator.comparing(o -> o.distance));
        }

        public void changePriority(ShoppingCenter neighbor, Integer distance) {
            remove(neighbor);
            neighbor.distance = distance;
            add(neighbor);
        }

    }
    private static class ShoppingCenter {
        private Integer id;
        private Integer distance;

        private Set<Integer> types;

        private HashMap<ShoppingCenter, Integer> neighbors = new HashMap<>();

        public ShoppingCenter(int id, Set<Integer> types) {
            this.id = id;
            this.types = types;
        }

        public void add(ShoppingCenter shoppingCenterB, int distance) {
            this.neighbors.put(shoppingCenterB, distance);
        }

    }

    private static void mockInput() {
        String lines =
                "5 5 5" + "\n" +
                        "1 1" + "\n" +
                        "1 2" + "\n" +
                        "1 3" + "\n" +
                        "1 4" + "\n" +
                        "1 5" + "\n" +
                        "1 2 10" + "\n" +
                        "1 3 10" + "\n" +
                        "2 4 10" + "\n" +
                        "3 5 10" + "\n" +
                        "4 5 10" + "\n";

        ByteArrayInputStream in = new ByteArrayInputStream(lines.getBytes());
        System.setIn(in);
    }

    private static Input readFromSTDIN() {

        Scanner inputScanner = new Scanner(System.in);
        String[] firstLine = inputScanner.nextLine().split(" ");

        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[0]);

        HashMap<Integer, ShoppingCenter> shoppingCenters = new HashMap<>();
        List<Integer> types = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            shoppingCenters.put(i + 1, parseShoppingCenter(
                    i + 1,
                    inputScanner.nextLine().split(" "),
                    types
            ));
        }

        for (int i = 0; i < m; i++) {
            parseRoad(shoppingCenters, inputScanner.nextLine().split(" "));
        }

        return new Input(n, m, k, shoppingCenters, types);
    }

    private static void parseRoad(HashMap<Integer, ShoppingCenter> shoppingCenters, String[] roadData) {
        int a = Integer.parseInt(roadData[0]);
        int b = Integer.parseInt(roadData[1]);
        int distance = Integer.parseInt(roadData[2]);

        ShoppingCenter shoppingCenterA = shoppingCenters.get(a);
        ShoppingCenter shoppingCenterB = shoppingCenters.get(b);

        shoppingCenterA.add(shoppingCenterB, distance);
        shoppingCenterB.add(shoppingCenterA, distance);
    }

    private static ShoppingCenter parseShoppingCenter(int id, String[] shoppingCenterData, List<Integer> allTypes) {
        int n = Integer.parseInt(shoppingCenterData[0]);

        Set<Integer> innerTypes = new HashSet<>();

        for (int i = 1; i < n + 1; i++) {
            Integer fishType = Integer.valueOf(shoppingCenterData[i]);
            innerTypes.add(fishType);
            allTypes.add(fishType);
        }

        return new ShoppingCenter(id, innerTypes);
    }

    private static class Input {
        private final int n;
        private final int m;
        private final int k;

        private final HashMap<Integer, ShoppingCenter> shoppingCenters;
        private List<Integer> fishTypes;

        public Input(int n, int m, int k, HashMap<Integer, ShoppingCenter> shoppingCenters, List<Integer> fishTypes) {

            this.n = n;
            this.m = m;
            this.k = k;
            this.shoppingCenters = shoppingCenters;
            this.fishTypes = fishTypes;
        }
    }

    private class Output {

        int value;
        @Override
        public String toString() {
            return "" + value;
        }

    }
}
