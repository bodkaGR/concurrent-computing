package net.bodkasoft.matrix;

import net.bodkasoft.matrix.utils.MatrixUtils;

public class MatrixMultiplicationApplication {

    private static final int THREADS_AMOUNT = 2;
    private static final int MATRIX_SIZE = 10;

    public static void main(String[] args) {
        int[][] matrixA = MatrixUtils.generateMatrix(MATRIX_SIZE, MATRIX_SIZE);
        int[][] matrixB = MatrixUtils.generateMatrix(MATRIX_SIZE, MATRIX_SIZE);

        System.out.printf("Matrices size: %dx%d\n\n", matrixA.length, matrixA[0].length);

        MatrixMultiplication.run(matrixA, matrixB, THREADS_AMOUNT);
    }
}