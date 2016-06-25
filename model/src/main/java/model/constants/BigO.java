package model.constants;

/**
 * Created by juliakram on 24/06/16.
 */
public enum BigO {
  CONSTANT("O(1)"),
  LOGARITHMIC("O(log(n))"),
  LINEARITHMIC("O(nlog(n))"),
  LINEAR("O(n)"),
  POLY_2("O(nˆ2)"),
  POLY_3("O(nˆ3)"),
  EXPONENTIAL("O(2ˆn)"),
  MOFN("O(M(n))"); //square root, exponentiation, division https://goo.gl/3Ddfdo

  private final String label;


  BigO(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
