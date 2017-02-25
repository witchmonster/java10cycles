package com.juliakram.java10cycles.hackerrank;

import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/synchronous-shopping
 */
class SynchronousShopping {
    public Output solution(Input input) {

        ShoppingCenter start = input.shoppingCenters.get(1);
//        ShoppingCenter end = input.shoppingCenters.get(input.shoppingCenters.size());

        HashMap<ShoppingCenter, Integer> distances = new HashMap<>();
        HashMap<ShoppingCenter, ShoppingCenter> cat1Path = new HashMap<>();

        KittyQueue queue = new KittyQueue();

        distances.put(start, 0);

        input.shoppingCenters.values().forEach(sc -> {
            if (!sc.equals(start)) {
                distances.put(sc, Integer.MAX_VALUE);
                cat1Path.put(sc, null);
            }
            queue.add(sc, distances.get(sc));
        });


        while (!queue.isEmpty()) {
            ShoppingCenter current = queue.poll();
            Integer currentDistanceFromStart = distances.get(current);

            current.neighbors.forEach((neighbor, distance) -> {
                int distanceFromStart = currentDistanceFromStart + distance;
                if (distanceFromStart < distances.get(neighbor)) {
                    distances.put(neighbor, distanceFromStart);
                    cat1Path.put(neighbor, current);

                    queue.changePriority(neighbor, distanceFromStart);
                }
            });
        }

        return null;
    }

    public static void main(String[] args) {
        SynchronousShopping algorithm = new SynchronousShopping();

        mockInput();

        //read from STDIN
        Input input = readFromSTDIN();

        System.out.println(algorithm.solution(input));
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

    private class Output {

        int value;

        @Override
        public String toString() {
            return "" + value;
        }
    }

    private class KittyQueue extends PriorityQueue<ShoppingCenter> {

        public KittyQueue() {
            super(Comparator.comparing(o -> o.distance));
        }

        public void add(ShoppingCenter sc, Integer distance) {
            sc.distance = distance;
            add(sc);
        }

        public void changePriority(ShoppingCenter neighbor, Integer distance) {
            remove(neighbor);
            neighbor.distance = distance;
            add(neighbor);
        }
    }
}
