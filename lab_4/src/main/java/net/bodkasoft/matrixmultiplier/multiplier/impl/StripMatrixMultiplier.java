package net.bodkasoft.matrixmultiplier.multiplier.impl;

import net.bodkasoft.matrixmultiplier.matrix.Matrix;
import net.bodkasoft.matrixmultiplier.multiplier.MatrixMultiplier;
import net.bodkasoft.matrixmultiplier.task.StripMultiplicationTask;

import java.util.concurrent.ForkJoinTask;

public class StripMatrixMultiplier implements MatrixMultiplier {

    @Override
    public Matrix multiply(Matrix leftMatrix, Matrix rightMatrix) {
        ForkJoinTask<Matrix> task = new StripMultiplicationTask(leftMatrix, rightMatrix);
        return task.fork().join();
    }
}
