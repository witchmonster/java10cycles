package com.juliakram.core;


import model.BinaryOps;
import model.Matrix;
import model.constants.Complexity;

public interface MatrixMultiply {

    Matrix multiply(Matrix a, Matrix b);

    /**
     * Created by juliakram on 14/02/16.
     */
    abstract class MatrixMultiplyAbstract implements MatrixMultiply, Algorithm<BinaryOps<Matrix>, Matrix> {

        @Override
        public Matrix solve(BinaryOps<Matrix> input) {
            return multiply(input.left, input.right);
        }
    }

    /**
     * Created by juliakram on 13/02/16.
     */
    class MatrixMultiplyDynamic extends MatrixMultiplyAbstract {

        @Override
        public Complexity getOTime() {
            return Complexity.POLY_3;
        }

        @Override
        public Complexity getOSpace() {
            return Complexity.LINEAR;
        }

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
