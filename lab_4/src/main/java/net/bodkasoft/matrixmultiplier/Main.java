package net.bodkasoft.matrixmultiplier;

import net.bodkasoft.matrixmultiplier.matrix.Matrix;
import net.bodkasoft.matrixmultiplier.multiplier.MatrixMultiplier;
import net.bodkasoft.matrixmultiplier.multiplier.impl.ConsistentMatrixMultiplier;
import net.bodkasoft.matrixmultiplier.multiplier.impl.StripMatrixMultiplier;
import net.bodkasoft.matrixmultiplier.utils.MatrixUtils;

public class Main {

    private static final MatrixMultiplier stripForkMultiplier = new StripMatrixMultiplier();
    private static final MatrixMultiplier consistentMatrixMultiplier = new ConsistentMatrixMultiplier();

    public static void main(String[] args) {
        int[] matrixSizes = new int[] { 504, 1008, 1512, 2016, 2520, 3024 };

        System.out.println("<---Matrix multiplication experiment--->");
        for (int matrixSize : matrixSizes) {
            int[][] matrixA = MatrixUtils.generateMatrix(matrixSize, matrixSize);
            int[][] matrixB = MatrixUtils.generateMatrix(matrixSize, matrixSize);

            System.out.printf("---Matrices size: %dx%d", matrixA.length, matrixA[0].length);

            long consistentMultiplicationTime = getMultiplicationTime(consistentMatrixMultiplier, matrixA, matrixB);
            System.out.println("\nConsistent: " + consistentMultiplicationTime + "ms");

            long forkMultiplicationTime = getMultiplicationTime(stripForkMultiplier, matrixA, matrixB);
            System.out.println("ForkJoin: " + forkMultiplicationTime + "ms");

            double speedUp = Math.round(((double) consistentMultiplicationTime / forkMultiplicationTime) * 100.0) / 100.0;
            System.out.println("Speed-up: " + speedUp + "ms");
            System.out.println();
        }
    }

    private static long getMultiplicationTime(MatrixMultiplier matrixMultiplier, int[][] matrixA, int[][] matrixB) {
        long start = System.currentTimeMillis();
        matrixMultiplier.multiply(new Matrix(matrixA), new Matrix(matrixB));
        long end = System.currentTimeMillis();
        return end - start;
    }
}
