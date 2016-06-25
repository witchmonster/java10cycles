package com.juliakram.core.algorithms.other;


import com.juliakram.java10cycles.datastructures.flat.stack.SequenceStack;
import com.juliakram.java10cycles.datastructures.flat.stack.Stack;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class InfixToPostfix {

  public static final String EXPRESSIONS_ALLOWED_PATTERN = "[A-Z]";

  public static final Map<String, Integer> operators;

  static {
    Map<String, Integer> aMap = new HashMap<String, Integer>();
    aMap.put("+", 1);
    aMap.put("-", 1);
    aMap.put("*", 2);
    aMap.put("/", 2);
    aMap.put("^", 3);
    operators = Collections.unmodifiableMap(aMap);
  }

  public static void main(String[] args) {
    String infix = "A+B*C-D^(E*F)";
    String expectedPostfix = "ABC*+DEF*^-";
    String postfix = infixToPostfix(infix);
    System.out.println(postfix);
  }

  private static String infixToPostfix(String infix) {
    String postfix = "";
    Stack<String> stack = new SequenceStack<String>();
    for (int i = 0; i < infix.length(); i++) {
      String s = String.valueOf(infix.charAt(i));
      if (isExpression(s)) {
        postfix += s;
      } else if (isOperator(s)) {
        while (!stack.isEmpty() && !"(".equals(stack.peek()) && stackOperatorHasNotLessPriorityThanCurrent(stack.peek(), s)) {
          postfix += stack.pop();
        }
        stack.push(s);
      } else if ("(".equals(s)) {
        stack.push(s);
      } else if (")".equals(s)) {
        while (!stack.isEmpty() && !"(".equals(stack.peek())) {
          postfix += stack.pop();
        }
        stack.pop();
      }
    }
    while (!stack.isEmpty()) {
      postfix += stack.pop();
    }
    return postfix;
  }

  private static boolean stackOperatorHasNotLessPriorityThanCurrent(String peek, String s) {
    return operators.get(peek) >= operators.get(s);
  }

  private static boolean isOperator(String s) {
    return operators.containsKey(s);
  }

  private static boolean isExpression(String s) {
    return Pattern.matches(EXPRESSIONS_ALLOWED_PATTERN, s);
  }
}
