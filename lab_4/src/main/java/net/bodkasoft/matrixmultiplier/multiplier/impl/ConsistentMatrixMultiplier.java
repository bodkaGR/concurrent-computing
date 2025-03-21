package net.bodkasoft.matrixmultiplier.multiplier.impl;

import net.bodkasoft.matrixmultiplier.matrix.Matrix;
import net.bodkasoft.matrixmultiplier.multiplier.MatrixMultiplier;

public class ConsistentMatrixMultiplier implements MatrixMultiplier {

    @Override
    public Matrix multiply(Matrix leftMatrix, Matrix rightMatrix) {
        int rows = leftMatrix.getRows();
        int cols = rightMatrix.getCols();
        int[][] results = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int item = 0;
                for (int k = 0; k < leftMatrix.getCols(); k++) {
                    item += leftMatrix.getItem(i, k) * rightMatrix.getItem(k, j);
                }
                results[i][j] = item;
            }
        }
        return new Matrix(results);
    }
}
