package com.juliakram.java10cycles.interview;


import java.util.*;

/**
 * Created by jkramr on 2/6/17.
 */
class HotelReview {
    public void solve(String... args) {

        if (args.length == 0) {
            return;
        }

        String[] keyWords = args[0].split(" ");
        int numberOfReviews = Integer.valueOf(args[1]);

        TreeMap<Integer, Integer> topRatedHotels = new TreeMap<>(Comparator.reverseOrder());

        for (int i = 0; i < numberOfReviews; i=i+2) {
            int hotelId = Integer.valueOf(args[2 + i]);

            String review = args[3 + i];

            int score = 0;

            for (String keyWord : keyWords) {
                if (review.contains(keyWord)) {
                    score++;
                }
            }

            if (!topRatedHotels.containsValue(hotelId)) {
                topRatedHotels.put(score, hotelId);
            }
        }

        for (Integer key: topRatedHotels.keySet()) {
            System.out.print(topRatedHotels.get(key) + " ");
        }
    }

    public static void main(String[] args) {
        HotelReview algorithm = new HotelReview();

        args = new String[]{new String("breakfast beach citycenter location metro view staff price"),
                new String("5"),
                new String("1"),
                new String("This hotel has a nice view of the citycenter. The location is perfect."),
                new String("2"),
                new String("The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth."),
                new String("1"),
                new String("Location is excellent, 5 minutes from citycenter. There is also a metro station very close to the hotel."),
                new String("1"),
                new String("They said I couldn't take my dog and there were other guests with dogs! That is not fair."),
                new String("2"),
                new String("Very friendly staff and good cost-benefit ratio. Location is a bit far from citycenter.")};

        algorithm.solve(args);
    }

}
