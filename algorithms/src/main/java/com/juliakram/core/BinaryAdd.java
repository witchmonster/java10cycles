package com.juliakram.core;

import model.BinaryOps;
import model.constants.Complexity;

public interface BinaryAdd {
    String add(String left, String right);

    abstract class Abstract implements BinaryAdd, Algorithm<BinaryOps<String>, String> {

        @Override
        public String solve(BinaryOps<String> input) {
            return add(input.left, input.right);
        }
    }

    /**
     * Created by juliakram on 25/02/16.
     */
    class Linear extends Abstract {

        @Override
        public Complexity getOTime() {
            return Complexity.LINEAR;
        }

        @Override
        public Complexity getOSpace() {
            return Complexity.LINEAR;
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
