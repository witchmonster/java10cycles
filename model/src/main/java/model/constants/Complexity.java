package model.constants;

public class Complexity {

  private BigO time;
  private BigO space;

  private Complexity(BigO time, BigO space) {
    this.time = time;
    this.space = space;
  }

  public static Complexity of(BigO time, BigO space) {
    return new Complexity(time, space);
  }

  @Override
  public String toString() {
    return "time=" + time +
            ", space=" + space;
  }

}