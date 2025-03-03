package net.bodkasoft.matrix.thread;

import net.bodkasoft.matrix.matrixmultiplier.MatrixMultiplier;

public class StripMatrixTask implements Runnable {

    private final int[][] subMatrixA;
    private final int[][] matrixB;
    private final MatrixMultiplier multiplier;

    public StripMatrixTask(int[][] subMatrixA, int[][] matrixB, MatrixMultiplier multiplier) {
        this.subMatrixA = subMatrixA;
        this.matrixB = matrixB;
        this.multiplier = multiplier;
    }

    @Override
    public void run() {
        multiplier.multiply(subMatrixA, matrixB);
    }
}
