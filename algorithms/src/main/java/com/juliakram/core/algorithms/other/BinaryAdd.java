package com.juliakram.core.algorithms.other;

import com.juliakram.core.Algorithm;

import model.constants.BigO;
import model.constants.Complexity;

public interface BinaryAdd extends Algorithm {

  static void main(String[] args) {

  }

  String add(String n1, String n2);

  class Linear implements BinaryAdd {

    @Override
    public Complexity complexity() {
      return Complexity.of(BigO.LINEAR, BigO.CONSTANT);
    }

    @Override
    public String add(String n1, String n2) {
      StringBuilder sb = new StringBuilder();

      int carry = 0;

      int l1 = n1.length() - 1;
      int l2 = n2.length() - 1;

      while (l1 >= 0 || l2 >= 0) {
        int sum = 0;

        char i1 = l1 >= 0 ? n1.charAt(l1--) : '0';
        char i2 = l2 >= 0 ? n2.charAt(l2--) : '0';

        sum += i1 - '0' + i2 - '0' + carry;

        carry = sum >> 1;

        sum = sum & 1;

        sb.append(sum);
      }

      if (carry == 1) {
        sb.append(carry);
      }

      sb.reverse();

      return sb.toString();
    }
  }
}
