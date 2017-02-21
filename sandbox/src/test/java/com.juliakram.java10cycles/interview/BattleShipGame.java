package com.juliakram.java10cycles.interview;


import java.util.HashMap;

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
        String[] hitArray = S.split(" ");

        HashMap<String, Integer> shipHitPoints = populateShips(S);

        for (String hit : hitArray) {
            Integer shipHitPointsLeft = shipHitPoints.get(hit);

            if (shipHitPointsLeft != null) {
                if (shipHitPointsLeft > 0) {
                    hitShips++;
                    shipHitPointsLeft--;

                    shipHitPoints.put(hit, shipHitPointsLeft);
                    if (shipHitPointsLeft == 0) {
                        sunkShips++;
                    }
                }
            }
        }

        return sunkShips + "," + hitShips;
    }

    private HashMap<String, Integer> populateShips(String s) {
        String[] stringShips = s.split(",");

        HashMap<String, Integer> shipsHitMap = new HashMap<>();

        for (String stringShip : stringShips) {

            String[] shipHitPoints = stringShip.split(" ");

            for (String shipHitPoint : shipHitPoints) {
                shipsHitMap.put(shipHitPoint, shipHitPoints.length);
            }
        }

        return shipsHitMap;
    }

    public static void main(String[] args) {
        BattleShipGame algorithm = new BattleShipGame();

        System.out.println(algorithm.solution(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A"));
    }

//    private class Ship {
//
//        private int hitPoints;
//        private boolean isHit;
//        private boolean isSunk;
//
//        public Ship(String[] shipHitPoints) {
//            this.hitPoints = shipHitPoints.length;
//        }
//
//        public int getHitPoints() {
//            return hitPoints;
//        }
//
//        public int hit() {
//            this.isHit = true;
//            this.hitPoints = hitPoints >= 1 ? hitPoints-- : 0;
//            this.isSunk = hitPoints == 0;
//        }
//    }
}
