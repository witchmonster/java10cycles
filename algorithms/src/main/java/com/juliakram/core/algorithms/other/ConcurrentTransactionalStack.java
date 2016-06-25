package com.juliakram.core.algorithms.other;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class ConcurrentTransactionalStack {

  public static final int MAX_VALUE = 1000000000;

  private AtomicReference<Node> head;

  private ConcurrentLinkedDeque<Transaction> transactions;

  public ConcurrentTransactionalStack() {
    head = new AtomicReference<>();
    transactions = new ConcurrentLinkedDeque<>();
  }

  public static void test() {
    ConcurrentTransactionalStack stack = new ConcurrentTransactionalStack();

    new Thread() {
      public void run() {
        for (int i = 1; i < 100; i++) {
          stack.push(i);
        }
      }
    }.start();

    new Thread() {
      public void run() {
        for (int i = 0; i < 10; i++) {
          stack.pop();
        }
      }
    }.start();
  }

  public static void example1() {
    ConcurrentTransactionalStack stack = new ConcurrentTransactionalStack();
    stack.push(1000000000);
    stack.push(1);                    // stack: [5,2]
    assert stack.top() == 1000000000;
    stack.pop();                      // stack: [5]
    assert stack.top() == 1;

    ConcurrentTransactionalStack sol2 = new ConcurrentTransactionalStack();
    assert sol2.top() == 0;         // top of an empty stack is 0
    sol2.pop();                     // pop should do nothing
  }

  public static void example2() {
    ConcurrentTransactionalStack stack = new ConcurrentTransactionalStack();
    stack.push(4);
    stack.begin();                    // start transaction 1
    stack.push(7);                    // stack: [4,7]
    stack.begin();                    // start transaction 2
    stack.push(2);                    // stack: [4,7,2]

    boolean rollback1 = stack.rollback();
    assert rollback1;  // rollback transaction 2

    assert stack.top() == 7;          // stack: [4,7]
    stack.begin();                    // start transaction 3
    stack.push(10);                   // stack: [4,7,10]

    boolean commit1 = stack.commit();
    assert commit1;    // transaction 3 is committed

    assert stack.top() == 10;

    boolean rollback2 = stack.rollback();
    assert rollback2;  // rollback transaction 1

    assert stack.top() == 4;          // stack: [4]

    boolean commit2 = stack.commit();
    assert !commit2;   // there is no open transaction
  }

  public static void example3() {
    ConcurrentTransactionalStack stack = new ConcurrentTransactionalStack();

    for (int i = 0; i < 10; i++) {

    }
    stack.push(4);
    stack.begin();                    // start transaction 1
    stack.push(7);                    // stack: [4,7]
    stack.begin();                    // start transaction 2
    stack.push(2);                    // stack: [4,7,2]

    boolean rollback1 = stack.rollback();
    assert rollback1;  // rollback transaction 2

    assert stack.top() == 7;          // stack: [4,7]
    stack.begin();                    // start transaction 3
    stack.push(10);                   // stack: [4,7,10]

    boolean commit1 = stack.commit();
    assert commit1;    // transaction 3 is committed

    assert stack.top() == 10;

    boolean rollback2 = stack.rollback();
    assert rollback2;  // rollback transaction 1

    assert stack.top() == 4;          // stack: [4]

    boolean commit2 = stack.commit();
    assert !commit2;   // there is no open transaction
  }

  public static void main(String[] args) {
    example1();
    example2();

    test();
  }

  public void push(int value) {
    validateValue(value);

    doPush(value);

    if (!transactions.isEmpty()) {
      transactions.forEach(transaction -> transaction.getRollbackTasks().push(ConcurrentTransactionalStack::pop));
    }
  }

  private void validateValue(int value) {
    if (value < 1 || value > MAX_VALUE) {
      throw new IllegalValueException();
    }
  }

  private void doPush(int value) {
    Node newHead = new Node(value);
    Node oldHead;

    do {
      oldHead = head.get();
      newHead.next = oldHead;
    } while (!head.compareAndSet(oldHead, newHead));
  }

  //peek
  public int top() {
    Node top;

    do {
      top = head.get();
      if (top == null) {
        return 0;
      }
    } while (!head.compareAndSet(top, top));

    return top.item;
  }

  public void pop() {
    int top = top();

    doPop();

    if (!transactions.isEmpty()) {
      transactions.forEach(transaction -> transaction.getRollbackTasks().push(solution -> solution.push(top)));
    }
  }

  private void doPop() {
    Node oldHead;
    Node newHead;

    do {
      oldHead = head.get();
      if (oldHead == null) {
        return;
      }
      newHead = oldHead.next;
    } while (!head.compareAndSet(oldHead, newHead));
  }

  public void begin() {
    transactions.push(new Transaction());
  }

  public boolean rollback() {
    if (transactions.isEmpty()) {
      return false;
    }

    transactions.pop().getRollbackTasks().forEach(task -> task.accept(this));

    return true;
  }

  public boolean commit() {
    if (transactions.isEmpty()) {
      return false;
    }

    transactions.pop();

    return true;
  }

  private class Node {
    private int item;

    private Node next;

    public Node(int item) {
      this.item = item;
    }
  }

  private class Transaction {
    private ConcurrentLinkedDeque<Consumer<ConcurrentTransactionalStack>> rollbackTasks;

    private Transaction() {
      rollbackTasks = new ConcurrentLinkedDeque<>();
    }

    public ConcurrentLinkedDeque<Consumer<ConcurrentTransactionalStack>> getRollbackTasks() {
      return rollbackTasks;
    }
  }

  private class IllegalValueException extends RuntimeException {
  }
}
