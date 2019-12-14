package com.jkramr.java10cycles.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;

//https://leetcode.com/problems/design-search-autocomplete-system/
public class SearchAutocomplete {
    public static void main(String[] args) {
        System.out.println(suggest("mon"));
    }
    
    //    Expected output: [moneypot, monitor]
    private static final List<String> suggestions = List.of("mobile", "moneypot", "monitor", "mousepad", "mocktray");
    
    private static List<String> suggest(final String inputText) {
        PrefixTree prefixTree = populatePrefixTree();
        
        return prefixTree.getSuggestions(inputText);
    }
    
    
    private static PrefixTree populatePrefixTree() {
        PrefixTree prefixTree = new PrefixTree();
        for (String word : suggestions) {
            prefixTree.add(word);
        }
        return prefixTree;
    }
    
    public static class PrefixTree {
        public static final char END_WORD_CHAR = '.';
        private Node head;
        
        public PrefixTree() {
            this.head = new Node(null);
        }
        
        public List<String> getSuggestions(final String prefix) {
            final List<String> result = new ArrayList<>();
            
            Node currentNode = search(prefix);
            
            if (currentNode == null) {
                return result;
            }
            
            Set<Node> visited = new HashSet<>();
            dfs(prefix, new StringBuilder(prefix), result, visited, currentNode);
            
            return result;
        }
        
        public Node search(final String prefix) {
            Node currentNode = head;
            for (int i = 0; i < prefix.length(); i++) {
                Character currentChar = prefix.charAt(i);
                if (currentNode.getChildren().containsKey(currentChar)) {
                    currentNode = currentNode.getChildren().get(currentChar);
                } else {
                    return null;
                }
            }
            return currentNode;
        }
        
        private void dfs(String prefix, StringBuilder currentWord, final List<String> result, final Set<Node> visited, final Node currentNode) {
            visited.add(currentNode);
            for (Node child : currentNode.getChildren().values()) {
                if (child.value == END_WORD_CHAR) {
                    result.add(currentWord.toString());
                    currentWord.delete(prefix.length(), currentWord.length());
                    return;
                }
                if (!visited.contains(child)) {
                    dfs(prefix, currentWord.append(child.value), result, visited, child);
                }
            }
        }
        
        @Data
        public static class Node {
            final Character value;
            final HashMap<Character, Node> children;
            
            public Node(final Character value) {
                this.value = value;
                children = new HashMap<>();
            }
        }
        
        //O(n)
        public void add(final String word) {
            Node currentNode = head;
            for (int i = 0; i < word.length(); i++) {
                Character currentChar = word.charAt(i);
                if (currentNode.getChildren().containsKey(currentChar)) {
                    currentNode = currentNode.getChildren().get(currentChar);
                } else {
                    Node childNode = new Node(currentChar);
                    currentNode.getChildren().putIfAbsent(currentChar, childNode);
                    currentNode = childNode;
                }
            }
            finalizeWord(currentNode);
        }
        
        private void finalizeWord(final Node currentNode) {
            currentNode.getChildren().put(END_WORD_CHAR, new Node(END_WORD_CHAR));
        }
    }
}
