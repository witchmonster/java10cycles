package com.juliakram.java10cycles.interview;


import java.util.*;

/**
 * Created by jkramr on 2/6/17.
 */
class BattleShipGame {
    public String solution1(int N, String S, String T) {

        int sunkShips = 0;
        int hitShips = 0;

        TreeSet<String> shots = new TreeSet<>(Arrays.asList(T.split(" ")));

        HashMap<String, String> ships = populateShips1(S);

        while (!shots.isEmpty()) {
            String shot = shots.first();
            String neighborCell = ships.get(shot);
            shots.remove(shot);

            if (neighborCell != null) {
                hitShips++;

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

    private HashMap<String, String> populateShips1(String s) {
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

    private String solution2(int i, String S, String T) {

        int sunkShips = 0;
        int hitShips = 0;

        HashSet<String> shots = new HashSet<>(Arrays.asList(T.split(" ")));

        HashMap<String, Ship> ships = populateShips2(S);

        for (String shot : shots) {
            Ship ship = ships.get(shot);
            if (ship != null) {
                if (!ship.isHit) {
                    ship.isHit = true;
                    hitShips++;
                }

                ship.hp--;
                if (ship.hp == 0) {
                    sunkShips ++;
                }
            }
        }

        return sunkShips + "," + hitShips;
    }

    private HashMap<String, Ship> populateShips2(String s) {
        String[] stringShips = s.split(",");

        HashMap<String, Ship> ships = new HashMap<>();

        for (String stringShip : stringShips) {
            String[] cells = stringShip.split(" ");
            Ship ship = new Ship(cells.length);
            for (String cell : cells) {
                ships.put(cell, ship);
            }
        }

        return ships;
    }

    private class Ship {

        private int hp;
        public boolean isHit;
        public Ship(int hp) {
            this.hp = hp;
        }

    }

    public static void main(String[] args) {
        BattleShipGame algorithm = new BattleShipGame();

        System.out.println(algorithm.solution1(4, "1B 2C,2D 3D 4D,6D", "2B 2C 2D 3D 4D 4A 6D"));
        System.out.println(algorithm.solution2(4, "1B 2C,2D 3D 4D,6D", "2B 2C 2D 3D 4D 4A 6D"));
    }
}
