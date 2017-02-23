package com.juliakram.java10cycles.interview;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by jkramr on 2/6/17.
 */
class BattleShipGame {
    public String solution(int N, String S, String T) {
        // write your code in Java SE 8

        int sunkShips = 0;
        int hitShips = 0;

        //used lib to split for simplicity because performance is not a priority
        //wasted a bit of space for the same reason
        TreeSet<String> shots = new TreeSet<>(Arrays.asList(T.split(" ")));

        HashSet<String> hits = new HashSet<>();
        HashMap<String, String> ships = populateShips(S);

        while (!shots.isEmpty()) {
            String shot = shots.first();
            String neighborCell = ships.get(shot);
            shots.remove(shot);

            if (neighborCell != null) {
                hitShips++;
                hits.add(shot);

                //single ship, it dies
                if (neighborCell.equals(shot)) {
                    sunkShips++;
                } else {
                    String current = neighborCell;
                    int totalHP = 0;

                    while (!current.equals(shot)) {
                        totalHP++;

                        if (shots.contains(current)) {
                            totalHP--;
                            shots.remove(current);
                        } else if (hits.contains(current)) {
                            totalHP--;
                        }

                        current = ships.get(current);
                    }

                    if (totalHP == 0) {
                        sunkShips++;
                    }
                }
            }
        }

        return sunkShips + "," + hitShips;
    }

    private HashMap<String, String> populateShips(String s) {
        String[] stringShips = s.split(",");

        HashMap<String, String> ships = new HashMap<>();

        for (String stringShip : stringShips) {
            String[] hits = stringShip.split(" ");

            String head = hits[0];
            String current = head;

            for (int i = 0; i < hits.length; i++) {
                String next = i == hits.length - 1 ? head : hits[i + 1];
                ships.put(current, next);
                current = next;
            }
        }

        return ships;
    }

    public static void main(String[] args) {
        BattleShipGame algorithm = new BattleShipGame();

        System.out.println(algorithm.solution(4, "1B 2C,2D 3D 4D,6D", "2B 2C 2D 3D 4D 4A 6D"));
    }

}
