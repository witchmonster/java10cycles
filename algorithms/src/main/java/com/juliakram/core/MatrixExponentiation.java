package com.juliakram.core;

import model.Matrix;
import model.ScalarOps;
import model.constants.Complexity;

public interface MatrixExponentiation {

    Matrix pow(Matrix arg, int scalar);

    abstract class Abstract extends AbstractAlgorithm<ScalarOps<Matrix, Integer>, Matrix> implements MatrixExponentiation {

        Abstract(Complexity oTime, Complexity oSpace) {
            super(oTime, oSpace);
        }

        @Override
        public Matrix solve(ScalarOps<Matrix, Integer> input) {
            return pow(input.arg, input.scalar);
        }
    }

    class Dynamic extends Abstract {

        MatrixMultiply matrixMultiplyOps;

        public Dynamic(MatrixMultiply matrixMultiplyOps) {
            super(Complexity.POLY_3, Complexity.POLY_2);

            this.matrixMultiplyOps = matrixMultiplyOps;
        }

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
