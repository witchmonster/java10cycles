package com.juliakram.java10cycles.datastructures;

import java.util.Stack;

/**
 * Created by jkramr on 2/6/17.
 */
class Solution {
    public String solution(int N, String S, String T) {
        // write your code in Java SE 8

        int sunkShips = 0;
        int hitShips = 0;

        //used lib to split for simplicity because performance is not a priority
        //wasted a bit of space for the same reason
        String[] ships = S.split(",");
        String[] hitArray = S.split(" ");

        Stack<String> hits = new Stack<>();

        for (int i = 0; i < hitArray.length; i++) {
            hits.push(hitArray[i]);
        }

        for (int j = 0; j < ships.length; j++) {
            String[] hullCells = ships[j].split(" ");

            int hitPoints = hullCells.length;

            for (int k = 0; k < hullCells.length; k++) {
                if (hullCells[k].equals(hits.peek())) {
                    hitPoints--;
                    hits.pop();
                }

                if (hitPoints == 0) {
                    sunkShips++;
                }

                if (hitPoints > 0 && hitPoints < hullCells.length) {
                    hitShips++;
                }

                if (hits.isEmpty()) {
                    return sunkShips + "," + hitShips;
                }
            }
        }

        return sunkShips + "," + hitShips;
    }

    public static void main(String[] args) {
        Solution algorithm = new Solution();

        System.out.println(algorithm.solution(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A"));
    }
}
