package com.juliakram.core.algorithms.cake;

import com.juliakram.core.Algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.constants.BigO;
import model.constants.Complexity;

import static com.juliakram.core.algorithms.cake.WordCloud.Constants.delimiters;
import static com.juliakram.core.algorithms.cake.WordCloud.Constants.minLength;
import static com.juliakram.core.algorithms.cake.WordCloud.Constants.names;

/**
 * https://www.interviewcake.com/question/java/word-cloud
 */
public interface WordCloud extends Algorithm {

  Map<String, Integer> buildCloud(String text);

  class Constants {
    static Set<Character> delimiters = new HashSet<>();
    static Set<String> names = new HashSet<>();

    static int minLength = 3;

    static {
      delimiters.add('.');
      delimiters.add(',');
      delimiters.add('/');
//            delimiters.add('&');
      delimiters.add('(');
      delimiters.add(')');
      delimiters.add('\'');
    }

    static {
      names.add("Bill");
    }
  }

  class Naive implements WordCloud {

    private static void addNotEmptyWord(Map<String, Integer> result, String word) {
      if (word.length() >= minLength) {

        word = names.contains(word) ? word : word.toLowerCase();

        Integer count = result.get(word);

        result.put(word, count == null ? 1 : count + 1);
      }
    }

    public Map<String, Integer> buildCloud(String text) {
      return easyCloud(text);
    }

    private Map<String, Integer> easyCloud(String text) {
      Map<String, Integer> result = new HashMap<>();

      StringBuilder delim = new StringBuilder();

      delimiters.forEach(delim::append);

      text = text.replaceAll("[" + delim.toString() + "]", "");

      String[] split = text.split(" ");

      for (String word : split) {
        addNotEmptyWord(result, word);
      }

      return result;
    }

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.LINEAR);
    }
  }

  class Linear implements WordCloud {

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.LINEAR);
    }

    public Map<String, Integer> buildCloud(String text) {
      Map<String, Integer> result = new HashMap<>();

      StringBuilder word = new StringBuilder();

      for (int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);

        if (isLetter(c)) {
          word.append(c);
          if (i == text.length() - 1) {
            addNotEmptyWord(result, word.toString());
          }
        } else {
          addNotEmptyWord(result, word.toString());
          word = new StringBuilder();
        }
      }

      return result;
    }

    private boolean isLetter(char c) {
      return !(delimiters.contains(c) || c == ' ');
      //        return "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".contains("" + c);
    }

    private void addNotEmptyWord(Map<String, Integer> result, String word) {
      if (word.length() >= minLength) {

        word = names.contains(word) ? word : word.toLowerCase();

        Integer count = result.get(word);

        result.put(word, count == null ? 1 : count + 1);
      }
    }
  }
}
