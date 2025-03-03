package net.bodkasoft.matrix;

import net.bodkasoft.matrix.utils.MatrixUtils;

public class MatrixMultiplicationApplication {

    private static final int THREADS_AMOUNT = 2;

    public static void main(String[] args) {
        int[][] matrixA = MatrixUtils.generateMatrix(4, 4);
        int[][] matrixB = MatrixUtils.generateMatrix(4, 4);

        MatrixMultiplication.run(matrixA, matrixB, THREADS_AMOUNT);
    }
}