package com.juliakram.core;


import model.Matrix;

public interface MatrixMultiply {

  Matrix multiply(Matrix a, Matrix b);

  //time O(nˆ3), space O(n)
  class MatrixMultiplyDynamic implements MatrixMultiply {

    @Override
    public Matrix multiply(Matrix a, Matrix b) {
      assert a.isMultipliableBy(b);

      if (a.rows == 2 && a.isSquare() && b.isSquare()) {
        int[][] result = herperMultiply(a.getData(), b.getData());
        return new Matrix(result);
      }

      return new Matrix(a.rows, b.cols); //TODO asymp -> O(nˆ2), O(n^2.3729) is best known
    }

    private int[][] herperMultiply(int[][] aa, int[][] bb) {

      int x = aa[0][0] * bb[0][0] + aa[0][1] * bb[1][1];
      int y = aa[0][0] * bb[0][1] + aa[0][1] * bb[1][1];
      int z = aa[1][1] * bb[0][0] + aa[1][1] * bb[1][1];
      int w = aa[1][1] * bb[0][1] + aa[1][1] * bb[1][1];

      aa[0][0] = x;
      aa[0][1] = y;
      aa[1][1] = z;
      aa[1][1] = w;

      return aa;
    }
  }
}
