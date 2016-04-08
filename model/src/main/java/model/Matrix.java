package model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Matrix {

    public final int cols;
    public final int rows;

    @Setter
    private int[][] data;

    public Matrix(int m, int n) {
        this.data = new int[m][n];
        this.cols = n;
        this.rows = m;
    }

    public Matrix(int[][] result) {
        this(result.length, result[0].length);
    }

    public boolean isSquare() {
        return rows == cols;
    }

    public boolean isMultipliableBy(Matrix b) {
        return cols == b.rows;
    }

    public int get(int i, int j) {
        return data[i][j];
    }

    public void set(int i, int j, int datum) {
        data[i][j] = datum;
    }
}
