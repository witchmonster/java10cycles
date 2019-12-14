package com.jkramr.java10cycles.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import lombok.Data;

//https://leetcode.com/problems/reorder-data-in-log-files/
public class ReorderLogFiles {
    
    public static final char DELIMITER = ' ';
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(reorderLogs(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"})));
        System.out.println(Arrays.toString(reorderLogs(new String[]{"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero", "let2 art can"})));
    }
    
    private static String[] reorderLogs(final String[] logs) {
        String[] result = new String[logs.length];
    
        Set<LogEntry> letterSet = new TreeSet<>();
        List<String> digitList = new ArrayList<>();
    
        //O(nlogn)
        for (int i = 0; i < logs.length; i++) {
            LogEntry logEntry = getLogEntry(logs[i]);
            if (logEntry.isLetter()) {
                //O(logn)
                letterSet.add(logEntry);
            }
            
            if (logEntry.isDigit()) {
                digitList.add(logs[i]);
            }
        }
    
        int i = 0;
        //O(n)
        for (LogEntry stringLog: letterSet) {
            result[i] = stringLog.getLog();
            i++;
        }
        for (String digitLog: digitList) {
            result[i] = digitLog;
            i++;
        }
        
        return result;
    }
    
    private static LogEntry getLogEntry(final String log) {
        for (int i = 0; i < log.length(); i++) {
            if (log.charAt(i) == DELIMITER) {
                //by description it's allowed to assume there will be at least one word
                return new LogEntry(log.substring(0, i), log.substring(i + 1), log);
            }
        }
        throw new IllegalArgumentException("should be at least one word in tail input");
    }
    
    @Data
    public static class LogEntry implements Comparable {
        private final String id;
        private final String tail;
        private final String log;
    
        public LogEntry(final String id, final String tail, final String log) {
            this.id = id;
            this.tail = tail;
            this.log = log;
        }
    
        public boolean isLetter() {
            return Character.isLetter(tail.charAt(0));
        }
    
        public boolean isDigit() {
            return Character.isDigit(tail.charAt(0));
        }
    
        @Override
        public int compareTo(final Object o) {
            String oId = ((LogEntry) o).getId();
            String oLog = ((LogEntry) o).getTail();
            int compareTo = tail.compareTo(oLog);
            if (compareTo == 0) {
                compareTo = id.compareTo(oId);
            }
            return compareTo;
        }
    }
}
