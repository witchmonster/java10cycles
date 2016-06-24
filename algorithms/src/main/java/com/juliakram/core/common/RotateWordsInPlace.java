package com.juliakram.core.common;

import com.juliakram.core.Algorithm;

import model.constants.BigO;
import model.constants.Complexity;

public interface RotateWordsInPlace extends Algorithm {

  String rotateByWord(String string);

  class Solution implements RotateWordsInPlace {

    private char delimiter = ' ';

    public static void main(String[] args) {
      testString("I love Taxify", "I evol yfixaT");

      testString(" I love Taxify", " I evol yfixaT");

      testString(" ", " ");

      testString("   sdf   123  098", "   fds   321  890");
    }

    private static void testString(String input, String expected) {
      RotateWordsInPlace solution = new Solution();

      String result = solution.rotateByWord(input);

      System.out.println("\"" + input + "\" : \"" + result + "\"");

      assert result.equals(expected);
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.CONSTANT);
    }

    //the best we can do without additional array is O(n), space complexity will be O(1)
    public String rotateByWord(String string) {
      if (string == null || string.isEmpty() || string.length() < 2) {
        return string;
      }

      char[] charArray = string.toCharArray();

      int i = 1;

      //assumption: word is anything between delimiters
      while (i < charArray.length) {
        if (charArray[i - 1] == delimiter && charArray[i] != delimiter) {
          int start = i;

          int end = walkForward(charArray, i);

          //rotates word in place and returns index position at the end of word + 1
          //O(wordLength) time, O(1) space
          i = rotateWord(charArray, start, end);
        }

        i++;
      }

      //this is additional linear operation of copying back into string
      //in convenience purposes only, but still preserves O(n)
      return String.valueOf(charArray);
    }

    private int walkForward(char[] charArray, int i) {
      while (i < charArray.length && charArray[i] != delimiter) {
        i++;
      }

      return i - 1;
    }

    private int rotateWord(char[] charArray, int start, int end) {
      int wordLength = end - start + 1;

      //walk a word one more time, still linear
      for (int j = start; j < start + wordLength / 2; j++) {
        char tmp = charArray[j];
        charArray[j] = charArray[start + end - j];
        charArray[start + end - j] = tmp;
      }

      //jump to the next char
      return end + 1;
    }
  }
}