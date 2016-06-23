package foo.presentation;

/**
 * Created by juliakram on 15/06/16.
 */
public class MonadDemo {


  public static void main(String[] args) {
    UselessBox<Integer> box = UselessBox.of(1);
    box.isPresent();
  }

  private static class UselessBox<T> {
    private T obj;

    public UselessBox(T obj) {
      this.obj = obj;
    }

    public static <T> UselessBox<T> of(T obj) {
      return new UselessBox<>(obj);
    }

    public boolean isPresent() {
      return obj != null;
    }
  }
}
