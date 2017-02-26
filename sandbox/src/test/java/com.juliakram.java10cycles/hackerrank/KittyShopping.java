package com.juliakram.java10cycles.hackerrank;

import java.io.ByteArrayInputStream;
import java.util.*;

public class KittyShopping {

    public static void main(String[] args) {

        mockInput();

        Dijekstra dijekstra = new Dijekstra();

        dijekstra.init();

        dijekstra.traverse();
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

    private static class Dijekstra {
        private int n;
        private int m;
        private int k;

        private int[] availableFish;
        private int[] neighborCount;

        private int[][] path;
        private int[][] time;

        private int[] bestTime;

        PriorityQueue<Integer[]> q;

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

            q.add(new Integer[]{0, availableFish[0]});
        }

        public void traverse() {
            while (!q.isEmpty()) {
                Integer[] current = q.poll();

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

                        q.add(new Integer[]{bestTime[neighbor], neighbor});
                    }
                }
            }

            int ans = Integer.MAX_VALUE;

            for (int i = (n - 1) << k; i < (n << k); i++) {
                for (int j = (n - 1) << k; j < (n << k); j++) {

                    int nthShopWithAllFish = (n << k) - 1;

                    if ((i | j) == nthShopWithAllFish) {
                        ans = Math.min(ans, Math.max(bestTime[i], bestTime[j]));
                    }
                }
            }

            System.out.println(ans);
        }
    }
}