package model.constants;

/**
 * Created by juliakram on 24/06/16.
 */
public enum BigO {
  LINEARITHMIC("O(nlgn)"),
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
