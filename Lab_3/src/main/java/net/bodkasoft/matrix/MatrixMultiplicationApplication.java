package net.bodkasoft.matrix;

import net.bodkasoft.matrix.executor.MatrixMultiplicationExecutor;
import net.bodkasoft.matrix.utils.MatrixUtils;

public class MatrixMultiplicationApplication {

    private static final int THREADS_AMOUNT = 4;

    public static void main(String[] args) {
        int[][] matrixA = MatrixUtils.generateMatrix(8, 8);
        int[][] matrixB = MatrixUtils.generateMatrix(8, 8);

        MatrixMultiplicationExecutor.run(matrixA, matrixB, THREADS_AMOUNT);
    }
}