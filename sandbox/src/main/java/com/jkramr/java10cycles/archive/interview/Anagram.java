package com.jkramr.java10cycles.archive.interview;


import java.util.HashMap;

/**
 * Created by jkramr on 2/6/17.
 */
class Anagram {

  public static void main(String[] args) {
    args = new String[]{
            "aaabbb",
            "ab",
            "abc",
            "mnop",
            "abcdabcd"
    };

    for (int i = 0; i < args.length; i++) {
      System.out.println(checkForAnagram(args[i]));
    }

  }

  private static int checkForAnagram(String string) {

    if (string.length() % 2 != 0) {
      return -1;
    }

    int halfLength = string.length() / 2;

    int neededPermutations = 0;

    String left  = string.substring(0, halfLength);
    String right = string.substring(halfLength, string.length());

    HashMap<Character, Character> map = toCharacterMap(left);

    for (char c : right.toCharArray()) {
      if (map.get(c) == null) {
        neededPermutations++;
        map.remove(c);
      }
    }

    return neededPermutations;
  }

  private static HashMap<Character, Character> toCharacterMap(String a) {
    HashMap<Character, Character> map = new HashMap<>();

    for (char c : a.toCharArray()) {
      map.put(c, c);
    }

    return map;
  }

}
