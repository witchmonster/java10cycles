package com.juliakram.core.algebra;


import model.Matrix;

/**
 * Created by juliakram on 13/02/16.
 */
public interface MatrixAlgebra {

    Matrix multiply(Matrix a, Matrix b);

    Matrix pow(Matrix a, int n);

}
