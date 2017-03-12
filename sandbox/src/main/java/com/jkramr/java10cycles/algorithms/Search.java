package com.jkramr.java10cycles.algorithms;

public class Search {

  public static void main(String[] args) {
//        System.out.println(new BinarySearch(3, new int[]{1, 2, 3, 4}).search());
//        System.out.println(new BinarySearch(3, new int[]{0, 0, 0, 0}).search());
    System.out.println(new BinarySearch(3, new int[]{1, 1, 2, 3}).search());
  }

  public static class BinarySearch {

    private int   element;
    private int[] array;

    public BinarySearch(int element, int[] array) {
      this.element = element;
      this.array = array;
    }

    public int search() {
      return search(0, array.length - 1);
    }

    private int search(int start, int end) {
      int pivot = (start + end) / 2;

      if (start > end || start < 0 || end >= array.length) {
        return -1;
      }

      if (start == end) {
        return array[pivot] == element ? pivot : -1;
      } else if (array[pivot] < element) {
        return search(pivot + 1, end);
      } else if (array[pivot] > element) {
        return search(start, pivot - 1);
      } else {
        return pivot;
      }
    }
  }
}
