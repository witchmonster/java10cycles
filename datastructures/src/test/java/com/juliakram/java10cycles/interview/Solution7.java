package com.juliakram.java10cycles.interview;


import java.util.Comparator;
import java.util.TreeMap;

/**
 * Created by jkramr on 2/6/17.
 */
class Solution7 {
    public void solve(String... args) {


    }

    public static void main(String[] args) {
        Solution7 algorithm = new Solution7();

        int a = 2437;
        int b = 845;

        int x = a;
        int y = b;

        while (x!=y) {
            if (x > y) {
                x = y;
            }

            if (x < y) {
                y = y - x;
            }
        }

        System.out.println(x);

//        args = new String[]{new String("breakfast beach citycenter location metro view staff price"),
//                new String("5"),
//                new String("1"),
//                new String("This hotel has a nice view of the citycenter. The location is perfect."),
//                new String("2"),
//                new String("The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth."),
//                new String("1"),
//                new String("Location is excellent, 5 minutes from citycenter. There is also a metro station very close to the hotel."),
//                new String("1"),
//                new String("They said I couldn't take my dog and there were other guests with dogs! That is not fair."),
//                new String("2"),
//                new String("Very friendly staff and good cost-benefit ratio. Location is a bit far from citycenter.")};

//        algorithm.solve(args);
    }

}
