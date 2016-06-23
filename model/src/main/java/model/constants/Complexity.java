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
    return "{time=" + time +
            ", space=" + space +
            '}';
  }

  public enum BigO {
    LOGARITHMIC("O(lgn)"),
    CONSTANT("O(1)"),
    LINEAR("O(n)"),
    POLY_2("O(nˆ2)"),
    POLY_3("O(nˆ3)"),
    EXPONENTIAL("O(2ˆn)");

    private final String label;


    BigO(String label) {
      this.label = label;
    }

    @Override
    public String toString() {
      return label;
    }
  }
}