package com.jkramr.java10cycles.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentStringEntries {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequentStringEntries(2, new String[]{"google", "apple", "facebook", "uber", "amazon"},
                                                                     new String[]{
                                                                             "google is awesome, but apple is better",
                                                                             "facebook is awesome",
                                                                             "apple is awesome",
                                                                             "facebook is bad",
                                                                             "apple is awesome",
                                                                             "apple is bad",
                                                                             "uber is awesome",
                                                                             "facebook is awful",
                                                                             "apple is awesome",
                                                                             "google is awesome",
                                                                             "amazon is awesome",
                                                                             "google is awesome",
                                                                             "facebook is awesome",
                                                                             "uber is awesome",
                                                                             "amazon is awesome",
                                                                             "facebook is awesome",
                                                                             })));
    }
    
    private static String[] topKFrequentStringEntries(final int k, final String[] keys, String[] strings) {
        Map<String, Integer> counts = new HashMap<>();
        PriorityQueue<String> frequentElements = new PriorityQueue<>(Comparator.comparingInt(counts::get));
        
        int n = strings.length;
        
        //assume small/constant
        int m = keys.length;
        
        //O(m*n)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String currentKey = keys[j];
                if (strings[i].contains(currentKey)) {
                    final int oldCount = counts.getOrDefault(currentKey, 0);
                    counts.put(currentKey, oldCount + 1);
                }
            }
        }
        
        //O(mlogk)
        for (String key : counts.keySet()) {
            frequentElements.add(key);
            if (frequentElements.size() > k) {
                frequentElements.poll();
            }
        }
    
        System.out.println(counts);
        String[] topKfrequentElements = new String[k];
        
        //O(klogk)
        for (int i = 0; i < k; i++) {
            topKfrequentElements[k - i - 1] = frequentElements.poll();
        }
        
        return topKfrequentElements;
        //total: O(m*n)
    }
    
}
