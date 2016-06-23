package com.juliakram.core;

import model.Matrix;

public interface MatrixExponentiation {

  Matrix pow(Matrix arg, int scalar);

  //time O(n^3), space O(nˆ2)
  class Dynamic implements MatrixExponentiation {

    MatrixMultiply matrixMultiplyOps;


    @Override
    public Matrix pow(Matrix a, int n) {
      if (n % 2 == 0) {
        return evenPow(a, n);
      } else {
        return matrixMultiplyOps.multiply(a, evenPow(a, n));
      }
    }

    private Matrix evenPow(Matrix a, int n) {
      for (int i = 0; i < n / 2; i++) {
        a = matrixMultiplyOps.multiply(a, a);
      }

      return a;
    }
  }
}
