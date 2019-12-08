package com.jkramr.java10cycles.archive.interview;


import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Scanner;

class HotelReview {

  private static HashMap<Integer, Integer> hotels = new HashMap<>();

  public static void main(String[] args) {

    mockInput();

    Scanner inputScanner = new Scanner(System.in);

    String[] keyWords = inputScanner.nextLine().split(" ");

    int numberOfReviews = Integer.valueOf(inputScanner.nextLine());

    for (int i = 0; i < numberOfReviews; i++) {
      int hotelId = Integer.valueOf(args[2 + i]);

      String review = inputScanner.nextLine();

      int score = hotels.getOrDefault(hotelId, 0);

      for (String keyWord : keyWords) {
        if (review.contains(keyWord)) {
          score++;
        }
      }

      hotels.put(hotelId, score);

    }
  }

  private static void mockInput() {
    String lines = "breakfast beach citycenter location metro view staff price\n" +
                   "5\n" +
                   "1\n" +
                   "This hotel has a nice view of the citycenter. The location is perfect.\n" +
                   "2\n" +
                   "The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth.\n" +
                   "1\n" +
                   "Location is excellent, 5 minutes from citycenter. There is also a metro station very close to the hotel.\n" +
                   "1\n" +
                   "They said I couldn't take my dog and there were other guests with dogs! That is not fair.\n" +
                   "2\n" +
                   "Very friendly staff and good cost-benefit ratio. Location is a bit far from citycenter.";

    ByteArrayInputStream in = new ByteArrayInputStream(lines.getBytes());
    System.setIn(in);

  }

}
