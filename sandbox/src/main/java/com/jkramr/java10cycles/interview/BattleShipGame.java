package com.jkramr.java10cycles.interview;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by jkramr on 2/6/17.
 */
class BattleShipGame {

  public static void main(String[] args) {
    BattleShipGame algorithm = new BattleShipGame();

    System.out.println(algorithm.solution(4, "1B 2C,2D 3D 4D,6D", "2B 2C 2D 3D 4D 4A 6D"));
    System.out.println(algorithm.solution2(4, "1B 2C,2D 3D 4D,6D", "2B 2C 2D 3D 4D 4A 6D"));
  }

  private String solution(int i, String S, String T) {

    int sunkShips = 0;
    int hitShips  = 0;

    HashSet<String> shots = new HashSet<>(Arrays.asList(T.split(" ")));

    HashMap<String, Ship> ships = populateShips(S);

    for (String shot : shots) {
      Ship ship = ships.get(shot);
      if (ship != null) {
        if (!ship.isHit) {
          ship.isHit = true;
          hitShips++;
        }

        ship.hp--;
        if (ship.hp == 0) {
          sunkShips++;
        }
      }
    }

    return sunkShips + "," + hitShips;
  }

  private HashMap<String, Ship> populateShips(String s) {
    String[] stringShips = s.split(",");

    HashMap<String, Ship> ships = new HashMap<>();

    for (String stringShip : stringShips) {
      String[] cells = stringShip.split(" ");
      Ship     ship  = new Ship(cells.length);
      for (String cell : cells) {
        ships.put(cell, ship);
      }
    }

    return ships;
  }

  public String solution2(int N, String S, String T) {

    int sunkShips = 0;
    int hitShips  = 0;

    TreeSet<String> shots = new TreeSet<>(Arrays.asList(T.split(" ")));

    HashMap<String, String> ships = populateShips2(S);

    while (!shots.isEmpty()) {
      String shot         = shots.first();
      String neighborCell = ships.get(shot);

      if (neighborCell != null) {
        hitShips++;

        //single ship, it dies
        if (neighborCell.equals(shot)) {
          sunkShips++;
        } else {
          String current = neighborCell;
          int    totalHP = 0;

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

      shots.remove(shot);
    }

    return sunkShips + "," + hitShips;
  }

  private HashMap<String, String> populateShips2(String s) {
    String[] stringShips = s.split(",");

    HashMap<String, String> ships = new HashMap<>();

    for (String stringShip : stringShips) {
      String[] hits = stringShip.split(" ");

      String head    = hits[0];
      String current = head;

      for (int i = 0; i < hits.length; i++) {
        String next = i == hits.length - 1 ? head : hits[i + 1];
        ships.put(current, next);
        current = next;
      }
    }

    return ships;
  }

  private class Ship {
    public  boolean isHit;
    private int     hp;

    public Ship(int hp) {
      this.hp = hp;
    }

  }
}
