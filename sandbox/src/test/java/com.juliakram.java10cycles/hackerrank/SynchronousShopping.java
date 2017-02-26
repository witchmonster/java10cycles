package com.juliakram.java10cycles.hackerrank;

import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/synchronous-shopping
 */
class SynchronousShopping {

    public static void main(String[] args) {
        mockInput();

        Dijekstra dijekstra = new Dijekstra();

        dijekstra.traverse();
    }

    private static class Dijekstra {

        int n;
        int m;
        int k;

        HashMap<Integer, Shop> shoppingCenters;

        private Shop start;
        private Shop end;

        private PriorityQueue<VirtualShop> queue;

        Dijekstra() {
            readFromSTDIN();

            start = shoppingCenters.get(1);
            end = shoppingCenters.get(n);

            queue = new PriorityQueue<>(
                    (o1, o2) ->
                              o1.time > o2.time         ?  1
                            : o1.time < o2.time         ? -1
                            : o1.id.compareTo(o2.id)
            );
        }

        void traverse() {

            while (!queue.isEmpty()) {
                VirtualShop current = queue.poll();

                HashSet<Integer> fishBasket = current.basket;

//                    Integer currentTime = current.time;

//                    Set<Integer> currentBasket = current.fishBasketSoFar;

//                    current.neighbors.forEach((neighbor, distance) -> {
//                        int neighborTime = currentTime + distance;
//                        if (neighborTime < neighbor.time) {
//                            neighbor.fishBasketSoFar.addAll(currentBasket);
//
//                            queue.change(neighbor, neighborTime);
//                        }
//                    });
            }

            int ans = Integer.MAX_VALUE;

            System.out.println(ans);
        }

        private void readFromSTDIN() {

            Scanner inputScanner = new Scanner(System.in);
            String[] firstLine = inputScanner.nextLine().split(" ");

            n = Integer.parseInt(firstLine[0]);
            m = Integer.parseInt(firstLine[0]);
            k = Integer.parseInt(firstLine[0]);

            shoppingCenters = new HashMap<>();

            for (int i = 0; i < n; i++) {
                shoppingCenters.put(i + 1, parseShoppingCenter(
                        i + 1,
                        inputScanner.nextLine().split(" ")
                ));
            }

            for (int i = 0; i < m; i++) {
                parseRoad(shoppingCenters, inputScanner.nextLine().split(" "));
            }
        }

        private static void parseRoad(HashMap<Integer, Shop> shoppingCenters, String[] roadData) {
            int a = Integer.parseInt(roadData[0]);
            int b = Integer.parseInt(roadData[1]);
            int distance = Integer.parseInt(roadData[2]);

            Shop shopA = shoppingCenters.get(a);
            Shop shopB = shoppingCenters.get(b);

            shopA.add(shopB, distance);
            shopB.add(shopA, distance);
        }

        private static Shop parseShoppingCenter(int id, String[] shoppingCenterData) {
            int n = Integer.parseInt(shoppingCenterData[0]);

            Set<Integer> innerTypes = new HashSet<>();

            for (int i = 1; i < n + 1; i++) {
                int fish = Integer.parseInt(shoppingCenterData[i]);
                innerTypes.add(fish);
            }

            return new Shop(id, innerTypes);
        }
    }

    private static class VirtualShop extends Shop {

        public int time;

        public HashSet<Integer> basket;

        VirtualShop(int id, Set<Integer> fishToSell) {
            super(id, fishToSell);
        }
    }

    private static class Shop {
        protected final Integer id;

        private final Set<Integer> fishToSell;

        private HashMap<Shop, Integer> neighbors = new HashMap<>();

        Shop(int id, Set<Integer> fishToSell) {
            this.id = id;
            this.fishToSell = fishToSell;
        }

        void add(Shop shopB, int distance) {
            this.neighbors.put(shopB, distance);
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

}
