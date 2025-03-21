package net.bodkasoft.matrix;

import net.bodkasoft.matrix.executor.StripMatrixMultiplicationExecutor;
import net.bodkasoft.matrix.utils.MatrixUtils;
import net.bodkasoft.matrix.utils.Result;

public class MatrixMultiplicationApplication {

    private static final int THREADS_AMOUNT = 8;
    private static final int MATRIX_SIZE = 10;

    public static void main(String[] args) {

        int[] matrixSizes = new int[]{ 504, 1008, 1512, 2016, 2520, 3024 };

        for (int i = 0; i < matrixSizes.length; i++) {
            int[][] matrixA = MatrixUtils.generateMatrix(matrixSizes[i], matrixSizes[i]);
            int[][] matrixB = MatrixUtils.generateMatrix(matrixSizes[i], matrixSizes[i]);

            System.out.printf("Matrices size: %dx%d\n", matrixA.length, matrixA[0].length);

            Result result = new Result(matrixA.length, matrixB[0].length);

            long start = System.currentTimeMillis();
            new StripMatrixMultiplicationExecutor(result, THREADS_AMOUNT).execute(matrixA, matrixB);
            long end = System.currentTimeMillis();
            System.out.println("Strip matrix multiplication took: " + (end - start) + "ms");
            System.out.println();
        }

//        MatrixMultiplication.run(matrixA, matrixB, THREADS_AMOUNT);
    }
}