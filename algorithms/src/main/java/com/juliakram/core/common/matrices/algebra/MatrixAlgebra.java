package com.juliakram.core.common.matrices.algebra;


import model.Matrix;

public interface MatrixAlgebra {

  Matrix multiply(Matrix a, Matrix b);

  Matrix pow(Matrix a, int n);

}
