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

        Output output = dijekstra.traverse();

        System.out.println(output.shortestDistance);
    }

    private static class Dijekstra {

        private Set<Integer> allFishTypes;
        private ShoppingCenter start;
        private ShoppingCenter end;
        private HashSet<Integer> shoppedFish;

        private KittyQueue queue;
        private int shortestDistance = 0;

        Dijekstra(Input input) {
            start = input.shoppingCenters.get(1);
            end = input.shoppingCenters.get(input.n);

            this.allFishTypes = input.fishTypes;

            start.distance = 0;

            shoppedFish = new HashSet<>();

            queue = new KittyQueue();

            input.shoppingCenters.values().forEach(sc -> {
                if (!sc.equals(start)) {
                    sc.distance = Integer.MAX_VALUE;
                }

                queue.add(sc);
            });
        }

        Output traverse() {

            while (!queue.isEmpty() || !allFishTypes.containsAll(shoppedFish)) {
                ShoppingCenter current = queue.poll();
                Integer currentDistanceFromStart = current.distance;
                shoppedFish.addAll(current.fishTypes);

                if (queue.isEmpty() && !current.equals(end)) {
                    queue.add(current);
                    traverseToMeetingPoint();
                } else {
                    current.neighbors.forEach((neighbor, distance) -> {
                        int distanceFromStart = currentDistanceFromStart + distance;
                        if (distanceFromStart < neighbor.distance && !shoppedFish.containsAll(neighbor.fishTypes)) {
                            queue.changePriority(neighbor, distanceFromStart);

                            shortestDistance = distanceFromStart;
                        }
                    });
                }
            }

            return new Output(shortestDistance);
        }

        private void traverseToMeetingPoint() {
            while (!queue.isEmpty()) {
                ShoppingCenter current = queue.poll();
                Integer currentDistanceFromStart = current.distance;

                current.neighbors.forEach((neighbor, distance) -> {
                    int neighborDistanceFromStart = currentDistanceFromStart + distance;
                    if (neighborDistanceFromStart < neighbor.distance) {
                        queue.add(neighbor);

                        shortestDistance = neighborDistanceFromStart;
                    }
                });
            }
        }

    }

    private static class KittyQueue extends PriorityQueue<ShoppingCenter> {

        KittyQueue() {
            super(Comparator.comparing(o -> o.distance));
        }

        void changePriority(ShoppingCenter neighbor, Integer distance) {
            remove(neighbor);
            neighbor.distance = distance;
            add(neighbor);
        }

    }

    private static class ShoppingCenter {
        private final Integer id;
        private Integer distance;

        private final Set<Integer> fishTypes;

        private HashMap<ShoppingCenter, Integer> neighbors = new HashMap<>();

        ShoppingCenter(int id, Set<Integer> fishTypes) {
            this.id = id;
            this.fishTypes = fishTypes;
        }

        void add(ShoppingCenter shoppingCenterB, int distance) {
            this.neighbors.put(shoppingCenterB, distance);
        }

        @Override
        public String toString() {
            return "" + id;
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
        HashSet<Integer> types = new HashSet<>();

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

    private static ShoppingCenter parseShoppingCenter(int id, String[] shoppingCenterData, HashSet<Integer> allTypes) {
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
        final int n;
        final int m;
        final int k;

        final HashMap<Integer, ShoppingCenter> shoppingCenters;
        private final Set<Integer> fishTypes;

        Input(int n, int m, int k, HashMap<Integer, ShoppingCenter> shoppingCenters, Set<Integer> fishTypes) {

            this.n = n;
            this.m = m;
            this.k = k;
            this.shoppingCenters = shoppingCenters;
            this.fishTypes = fishTypes;
        }
    }

    private static class Output {

        private int shortestDistance;

        Output(int shortestDistance) {
            this.shortestDistance = shortestDistance;
        }
    }
}
