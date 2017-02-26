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

        HashMap<Integer, Shop> shops;

        private Shop start;
        private PriorityQueue<ShopVisit> queue;

        //id -> basket -> timeSoFar
        HashMap<Integer, HashMap<Integer, Integer>> bestTime;

        Dijekstra() {
            readFromSTDIN();

            start = shops.get(0);

            bestTime = new HashMap<>();

            for (int shopId = 0; shopId < n; shopId++) {
                bestTime.put(shopId, new HashMap<>());

                int allFish = (1 << k) - 1;

                for (int fishCombinations = 0; fishCombinations <= allFish; fishCombinations++) {
                    bestTime.get(shopId).put(fishCombinations, Integer.MAX_VALUE);
                }
            }

            bestTime.get(0).put(start.fish, 0);

            queue = new PriorityQueue<>(
                    (o1, o2) ->
                            o1.timeSoFar > o2.timeSoFar ? 1
                                    : o1.timeSoFar < o2.timeSoFar ? -1
                                    : o1.shop.id.compareTo(o2.shop.id)
            );

            queue.add(new ShopVisit(start, start.fish, 0));
        }

        void traverse() {

            while (!queue.isEmpty()) {
                ShopVisit current = queue.poll();

                Integer currentTimeSoFar = current.timeSoFar;

                int fishBasket = current.basket;

                current.shop.neighbors.forEach((neighbor, timeToNeighbor) -> {

                    int neighborTimeSoFar = currentTimeSoFar + timeToNeighbor;

                    int neighborBasket = neighbor.fish | fishBasket;

                    if (neighborTimeSoFar < bestTime.get(neighbor.id).get(neighborBasket)) {
                        bestTime.get(neighbor.id).put(neighborBasket, neighborTimeSoFar);

                        ShopVisit neighborVisit = new ShopVisit(neighbor, neighborBasket, neighborTimeSoFar);

                        queue.add(neighborVisit);
                    }
                });
            }

            int result = Integer.MAX_VALUE;

            HashMap<Integer, Integer> bestTimesForNthShop = bestTime.get(n - 1);

            int allFish = (1 << k) - 1;

            for (int firstCatBasket = 0; firstCatBasket <= allFish; firstCatBasket++) {
                for (int secondCatBasket = 0; secondCatBasket <= allFish; secondCatBasket++) {

                    int sumOfFishInBaskets = firstCatBasket | secondCatBasket;

                    if (sumOfFishInBaskets == allFish) {
                        result = Math.min(result, Math.max(bestTimesForNthShop.get(firstCatBasket), bestTimesForNthShop.get(secondCatBasket)));
                    }
                }
            }

            System.out.println(result);
        }

        private static class ShopVisit {

            Shop shop;

            int basket;
            int timeSoFar;

            ShopVisit(Shop shop, int basket, int timeSoFar) {
                this.shop = shop;
                this.basket = basket;
                this.timeSoFar = timeSoFar;
            }
        }

        private static class Shop {
            final Integer id;

            final int fish;

            private HashMap<Shop, Integer> neighbors = new HashMap<>();

            Shop(int id, int fish) {
                this.id = id;
                this.fish = fish;
            }

            void add(Shop shopB, int distance) {
                this.neighbors.put(shopB, distance);
            }

            @Override
            public String toString() {
                return "" + id;
            }
        }

        private void readFromSTDIN() {

            Scanner inputScanner = new Scanner(System.in);
            String[] firstLine = inputScanner.nextLine().split(" ");

            n = Integer.parseInt(firstLine[0]);
            m = Integer.parseInt(firstLine[0]);
            k = Integer.parseInt(firstLine[0]);

            shops = new HashMap<>();

            for (int i = 0; i < n; i++) {
                shops.put(i, parseShoppingCenter(i, inputScanner.nextLine().split(" ")));
            }

            for (int i = 0; i < m; i++) {
                parseRoad(shops, inputScanner.nextLine().split(" "));
            }
        }

        private static void parseRoad(HashMap<Integer, Shop> shoppingCenters, String[] roadData) {
            int a = Integer.parseInt(roadData[0]) - 1;
            int b = Integer.parseInt(roadData[1]) - 1;
            int distance = Integer.parseInt(roadData[2]);

            Shop shopA = shoppingCenters.get(a);
            Shop shopB = shoppingCenters.get(b);

            shopA.add(shopB, distance);
            shopB.add(shopA, distance);
        }

        private static Shop parseShoppingCenter(int id, String[] shoppingCenterData) {
            int fishTypesCount = Integer.parseInt(shoppingCenterData[0]);

            int innerTypes = 0;

            for (int i = 1; i < fishTypesCount + 1; i++) {
                int fishType = Integer.parseInt(shoppingCenterData[i]) - 1;
                innerTypes |= 1 << fishType;
            }

            return new Shop(id, innerTypes);
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
