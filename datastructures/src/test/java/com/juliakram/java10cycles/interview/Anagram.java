package com.juliakram.java10cycles.interview;


/**
 * Created by jkramr on 2/6/17.
 */
class Anagram {

    public static void main(String[] args) {
        Anagram algorithm = new Anagram();

        args = new String[]{
                new String("5"),
                new String("aaabbb"),
                new String("ab"),
                new String("abc"),
                new String("abc"),
                new String("mnop"),
                new String("abcdabcd")};

        if (args.length < 2) {
            return;
        }

        int testCaseCount = Integer.parseInt(args[0]);

        if (args.length < testCaseCount + 1) {
            return;
        }

        for (int i = 1; i < args.length; i++) {
            checkForAnagram(args[i]);
        }

    }

    private static void checkForAnagram(String string) {

        if (string.length() % 2 != 0) {
            System.out.println(-1);
        }

        int halfLength = string.length() / 2;

        String aString = string.substring(0, halfLength);
        String bString = string.substring(halfLength, string.length());

        int neededPermutations = halfLength;

        for (int i = 0; i < halfLength; i++) {
            if (aString.charAt(i) == bString.charAt(halfLength - 1 - i)) {
                neededPermutations--;
            }
        }

        System.out.println(neededPermutations);
    }

}
