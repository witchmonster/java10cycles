package model.constants;

public enum Complexity {
    LOGARITHMIC("O(lgn)"),
    CONSTANT("O(1)"),
    LINEAR("O(n)"),
    POLY_2("O(nˆ2)"),
    POLY_3("O(nˆ3)"),
    EXPONENTIAL("O(2ˆn)");

    private final String label;


    Complexity(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}