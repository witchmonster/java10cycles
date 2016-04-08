package com.juliakram.core.matrices;

/**
 * Assume matrix is nxn
 */
public class RotateMatrixInPlace {

    private static int[][] rotate(int[][] matrix) {
        int n = matrix.length;
        int layers = n / 2;
        for (int l = 0; l < layers; l++) {
            for (int i = l; i < n - l - 1; i++) {

                int top = matrix[l][i];

                matrix[l][i] = matrix[n - i - 1][l];
                matrix[n - i - 1][l] = matrix[n - l - 1][n - i - 1];
                matrix[n - l - 1][n - i - 1] = matrix[i][n - l - 1];
                matrix[i][n - l - 1] = top;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(rotate(matrix4));
        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printMatrix(rotate(matrix3));
    }

    private static void printMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int[] aMatrix : matrix) {
            for (int j = 0; j < n; j++) {
                System.out.print(aMatrix[j] + " ");
            }
            System.out.println();
        }
    }
}