package com.juliakram.java10cycles.hackerrank;


import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jkramr on 2/6/17.
 */
class SynchronousShopping {
    public Output solution(Input input) {

        ShoppingCenter start = input.shoppingCenters.get(1);
        ShoppingCenter end = input.shoppingCenters.get(input.shoppingCenters.size());

        List<Integer> fishLeftToShop = new ArrayList<>(input.fishTypes);

        //remove fish types which will be inevitably shopped anyway
//        start.types.forEach(fishLeftToShop::remove);
//        end.types.forEach(fishLeftToShop::remove);
        Queue<ShoppingCenter> cat1 = new PriorityQueue<>();
        Queue<ShoppingCenter> cat2 = new PriorityQueue<>();

        while (!fishLeftToShop.isEmpty()) {

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
        private Set<Integer> types;
        private HashMap<ShoppingCenter, Integer> neighbors = new HashMap<>();

        public ShoppingCenter(int id, Set<Integer> types) {
            this.id = id;
            this.types = types;
        }

        public void add(ShoppingCenter shoppingCenterB, int distance) {
            this.neighbors.put(shoppingCenterB, distance);
        }

        @Override
        public String toString() {
            return "" + id + ": " + types + " " +
                    "neighbors: " +
                    neighbors.keySet()
                            .stream()
                            .map(k -> "" + k.id + ":" + neighbors.get(k))
                            .collect(Collectors.toSet());
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
