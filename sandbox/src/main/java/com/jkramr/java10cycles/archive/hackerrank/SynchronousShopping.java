package com.jkramr.java10cycles.archive.hackerrank;

import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.hackerrank.com/challenges/synchronous-shopping
 */
class SynchronousShopping {

  public static void main(String[] args) {
    mockInput();

    KittyDijekstra dijekstra = new KittyDijekstra();

    dijekstra.traverse();
  }

  private static void mockInput() {
//        String lines = testCase0();
    String lines = testCase1();

    ByteArrayInputStream in = new ByteArrayInputStream(lines.getBytes());
    System.setIn(in);
  }

  private static String testCase0() {
    return "5 5 5" + "\n" +
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
  }

  private static String testCase1() {
    return "8 20 2" + "\n" +
           "0" + "\n" +
           "1 1" + "\n" +
           "0" + "\n" +
           "1 1" + "\n" +
           "0" + "\n" +
           "0" + "\n" +
           "0" + "\n" +
           "2 1 2" + "\n" +
           "3 2 762" + "\n" +
           "7 4 727" + "\n" +
           "8 7 322" + "\n" +
           "8 1 207" + "\n" +
           "1 5 687" + "\n" +
           "2 6 556" + "\n" +
           "1 6 103" + "\n" +
           "6 8 237" + "\n" +
           "3 6 777" + "\n" +
           "5 6 698" + "\n" +
           "3 7 584" + "\n" +
           "6 4 25" + "\n" +
           "2 5 734" + "\n" +
           "3 5 667" + "\n" +
           "7 2 208" + "\n" +
           "7 5 669" + "\n" +
           "4 8 775" + "\n" +
           "8 3 229" + "\n" +
           "1 2 462" + "\n" +
           "4 2 562" + "\n";
  }

  private static class KittyDijekstra {

    int n;
    int m;
    int k;

    HashMap<Integer, Shop> shops;
    //id -> basket -> timeSoFar
    HashMap<Integer, HashMap<Integer, Integer>> bestTime;
    private Shop                     start;
    private PriorityQueue<ShopVisit> queue;
    private HashMap<Integer, List<ShopVisit>> visited = new HashMap<>();

    KittyDijekstra() {
      readFromSTDIN();

      start = shops.get(0);

      bestTime = new HashMap<>();

      for (int shopId = 0; shopId < n; shopId++) {
        bestTime.put(shopId, new HashMap<>());

        //0b11111, k = 5
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
                                                                                : o1.shop.id.compareTo(
                                                                                        o2.shop.id)
      );

      queue.add(new ShopVisit(start, start.fish, 0, 0));
    }

    private static void parseRoad(HashMap<Integer, Shop> shoppingCenters, String[] roadData) {
      int a        = Integer.parseInt(roadData[0]) - 1;
      int b        = Integer.parseInt(roadData[1]) - 1;
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

    void traverse() {

      while (!queue.isEmpty()) {
        ShopVisit current = queue.poll();

        int currentDepth = current.depth;

        visited.putIfAbsent(currentDepth, new ArrayList<>());
        visited.get(currentDepth).add(current);

        Integer currentTimeSoFar = current.timeSoFar;

        int fishBasket = current.basket;

        current.shop.neighbors.forEach((neighbor, timeToNeighbor) -> {

          int neighborTimeSoFar = currentTimeSoFar + timeToNeighbor;

          int neighborBasket = neighbor.fish | fishBasket;

          if (neighborTimeSoFar < bestTime.get(neighbor.id).get(neighborBasket)) {
            bestTime.get(neighbor.id).put(neighborBasket, neighborTimeSoFar);

            ShopVisit neighborVisit = new ShopVisit(
                    neighbor,
                    neighborBasket,
                    neighborTimeSoFar,
                    currentDepth + 1
            );

            queue.add(neighborVisit);
          }
        });
      }

      int result = Integer.MAX_VALUE;

      HashMap<Integer, Integer> bestTimesForNthShop = bestTime.get(n - 1);

      //0b11111, k = 5
      int allFish = (1 << k) - 1;

      for (int firstCatBasket = 0; firstCatBasket <= allFish; firstCatBasket++) {
        for (int secondCatBasket = 0; secondCatBasket <= allFish; secondCatBasket++) {

          int sumOfFishInBaskets = firstCatBasket | secondCatBasket;

          if (sumOfFishInBaskets == allFish) {
            Integer firstCat  = bestTimesForNthShop.get(firstCatBasket);
            Integer secondCat = bestTimesForNthShop.get(secondCatBasket);

            result = Math.min(result, Math.max(firstCat, secondCat));
          }
        }
      }

      System.out.println(result);
    }

    private void readFromSTDIN() {

      Scanner  inputScanner = new Scanner(System.in);
      String[] firstLine    = inputScanner.nextLine().split(" ");

      n = Integer.parseInt(firstLine[0]);
      m = Integer.parseInt(firstLine[1]);
      k = Integer.parseInt(firstLine[2]);

      shops = new HashMap<>();

      for (int i = 0; i < n; i++) {
        shops.put(i, parseShoppingCenter(i, inputScanner.nextLine().split(" ")));
      }

      for (int i = 0; i < m; i++) {
        parseRoad(shops, inputScanner.nextLine().split(" "));
      }
    }

    private static class ShopVisit {

      Shop shop;

      int depth;
      int basket;
      int timeSoFar;

      ShopVisit(Shop shop, int basket, int timeSoFar, int depth) {
        this.shop = shop;
        this.basket = basket;
        this.timeSoFar = timeSoFar;
        this.depth = depth;
      }

      @Override
      public String toString() {
        return "ShopVisit{" +
               "shop=" + shop +
               ", basket=" + basket +
               ", timeSoFar=" + timeSoFar +
               '}';
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
        return "" +
               id +
               ": neighbors: " +
               neighbors.keySet().stream().map(shop -> shop.id).collect(Collectors.toSet());
      }
    }
  }

}
