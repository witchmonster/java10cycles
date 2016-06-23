package foo.presentation;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by juliakram on 15/06/16.
 */
public class LambdaDemo {

  private final Lock lock = new ReentrantLock();

  @Test
  public void oldWay() {
    Locker.runLocked(lock, new Runnable() {

      @Override
      public void run() {
        System.out.println("hello world");
      }

    });
  }

  @Test
  public void java8Way() {
    Locker.runLocked(lock, () -> System.out.println("hello world"));
  }

  private static class Locker {
    static void runLocked(Lock lock, Runnable block) {
      lock.lock();
      try {
        block.run();
      } finally {
        lock.unlock();
      }
    }
  }

}
