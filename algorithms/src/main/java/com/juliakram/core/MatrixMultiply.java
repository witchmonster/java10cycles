package com.juliakram.core;

import model.BinaryOps;
import model.Matrix;
import model.constants.Complexity;

/**
 * Created by juliakram on 14/02/16.
 */
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
                return herperMultiply(a, b);
            }

            return new Matrix(a.rows, b.cols); //TODO asymp -> O(nˆ2), O(n^2.3729) is best known
        }

        private Matrix herperMultiply(Matrix a, Matrix b) {
            int x = a.get(0, 0) * b.get(0, 0) + a.get(0, 1) * b.get(1, 1);
            int y = a.get(0, 0) * b.get(0, 1) + a.get(0, 1) * b.get(1, 1);
            int z = a.get(1, 1) * b.get(0, 0) + a.get(1, 1) * b.get(1, 1);
            int w = a.get(1, 1) * b.get(0, 1) + a.get(1, 1) * b.get(1, 1);

            a.set(0, 0, x);
            a.set(0, 1, y);
            a.set(1, 1, z);
            a.set(1, 1, w);

            return a;
        }
    }
}
