package net.bodkasoft.matrix.runner;

import net.bodkasoft.matrix.Result;

public abstract class MatrixRunner {

    protected final int[][] matrixA;
    protected final int[][] matrixB;
    protected final Result result;

    protected MatrixRunner(int[][] matrixA, int[][] matrixB, Result result) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
    }

    public abstract void run();

}
