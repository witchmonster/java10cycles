package com.juliakram.core.common;

import java.util.Arrays;

/**
 * Time complexity is O(n), space - O(n)
 */
public class MergeTwoSortedArrays {

  public static void main(String[] args) {
    int[] listm = new int[]{1, 3, 56, 102, 205, 1007};
    int[] listn = new int[]{25, 26, 102, 107, 206, 305, 677, 2328, 8879};

    System.out.println(merge(listm, listn));
  }

  private static String merge(int[] listm, int[] listn) {
    int i = listm.length - 1;
    int j = listn.length - 1;
    int k = i + j + 1;
    int[] resultList = new int[k + 1];
    while (k >= 0) {
      if (j < 0 || (i >= 0 && listm[i] > listn[j])) {
        resultList[k--] = listm[i--];
      } else {
        resultList[k--] = listn[j--];
      }
    }
    return Arrays.toString(resultList);
  }
}
