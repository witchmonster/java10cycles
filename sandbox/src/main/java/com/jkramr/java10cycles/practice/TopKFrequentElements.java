package com.jkramr.java10cycles.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//https://leetcode.com/problems/top-k-frequent-elements/
public class TopKFrequentElements {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequentNumbers(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }
    
    private static int[] topKFrequentNumbers(final int[] ints, final int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        PriorityQueue<Integer> frequentElements = new PriorityQueue<>(Comparator.comparingInt(counts::get));
        
        //O(n)
        for (int i = 0; i < ints.length; i++) {
            int current = ints[i];
            Integer oldCount = counts.getOrDefault(current, 0);
            counts.put(current, oldCount + 1);
        }
    
        //O(nlogk)
        for (int n : counts.keySet()) {
            frequentElements.add(n);
            if (frequentElements.size() > k) {
                frequentElements.poll();
            }
        }
        
        int[] topKfrequentElements = new int[k];
        
        //O(klogk)
        for (int i = 0; i < k; i++) {
            topKfrequentElements[k-i-1] = frequentElements.poll();
        }
    
        return topKfrequentElements;
        //total: O(nlogk)
    }
    
}
