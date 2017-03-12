package com.jkramr.java10cycles.hackerrank;

import java.io.ByteArrayInputStream;
import java.util.*;

public class KittyShopping {

  public static void main(String[] args) {

    mockInput();

    Dijekstra dijekstra = new Dijekstra();

    dijekstra.init();

    dijekstra.solve();
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

  private static class Dijekstra {

    PriorityQueue<Integer[]> q;
    private int   n;
    private int   m;
    private int   k;
    private int[] availableFish;
    private int[]   neighborCount;
    private int[][] path;
    private int[][] time;
    private int[]   bestTime;
    private HashMap<Integer, ArrayList<Integer[]>> visited = new HashMap<>();

    public Dijekstra() {
      Scanner in = new Scanner(System.in);

      n = in.nextInt();
      m = in.nextInt();
      k = in.nextInt();

      readFish(in);

      readGraph(in);
    }

    private void readGraph(Scanner in) {
      neighborCount = new int[n];

      path = new int[n][n];
      time = new int[n][n];

      for (int i = 0; i < m; i++) {

        int x = in.nextInt() - 1;
        int y = in.nextInt() - 1;
        int z = in.nextInt();

        path[x][neighborCount[x]] = y;
        path[y][neighborCount[y]] = x;

        time[x][neighborCount[x]] = time[y][neighborCount[y]] = z;

        neighborCount[x]++;
        neighborCount[y]++;
      }
    }

    private void readFish(Scanner in) {
      availableFish = new int[n];

      for (int i = 0; i < n; i++) {
        availableFish[i] = 0;
        int t = in.nextInt();
        for (int j = 0; j < t; j++) {
          int a = in.nextInt() - 1;
          availableFish[i] |= 1 << a;
        }
      }
    }

    public void init() {
      bestTime = new int[n << k];

      Arrays.fill(bestTime, Integer.MAX_VALUE);

      bestTime[availableFish[0]] = 0;

      q = new PriorityQueue<>(
              (a, b) -> a[0] > b[0] ? 1
                                    : a[0] < b[0] ? -1
                                                  : a[1] - b[1]);

      q.add(new Integer[]{0, availableFish[0], 0});
    }

    public void solve() {
      while (!q.isEmpty()) {
        Integer[] current = q.poll();

        Integer currentDepth = current[2];

        visited.putIfAbsent(currentDepth, new ArrayList<>());
        visited.get(currentDepth).add(current);

        int currentTime = current[0];

        int currentId = current[1] >> k;

        int fishBasket = current[1] & (1 << k) - 1;

        for (int i = 0; i < neighborCount[currentId]; i++) {
          int neighborId = path[currentId][i];

          int neighborFishBasket = fishBasket | availableFish[neighborId];

          int neighbor = (neighborId << k) + neighborFishBasket;

          int timeToNeighbor = time[currentId][i];

          int nextCurrentTime = currentTime + timeToNeighbor;

          if (bestTime[neighbor] > nextCurrentTime) {
            bestTime[neighbor] = nextCurrentTime;

            q.add(new Integer[]{bestTime[neighbor], neighbor, currentDepth + 1});
          }
        }
      }

      int ans = Integer.MAX_VALUE;

      for (int i = (n - 1) << k; i < (n << k); i++) {
        for (int j = (n - 1) << k; j < (n << k); j++) {

          int nthShopWithAllFish = (n << k) - 1;

          if ((i | j) == nthShopWithAllFish) {
            int firstCat  = bestTime[i];
            int secondCat = bestTime[j];

            ans = Math.min(ans, Math.max(firstCat, secondCat));
          }
        }
      }

      System.out.println(ans);
    }

  }
}