package net.bodkasoft.matrix.matrixmultiplier;

import net.bodkasoft.matrix.utils.Result;

public class StripMatrixMultiplier implements MatrixMultiplier {

    private final Result result;
    private int rowIndex;

    public StripMatrixMultiplier(Result result, int rowIndex) {
        this.result = result;
        this.rowIndex = rowIndex;
    }

    @Override
    public void multiply(int[][] matrixA, int[][] matrixB) {
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for (int k = 0; k < matrixA[0].length; k++) {
                    result.addItem(rowIndex, j, matrixA[i][k] * matrixB[k][j]);
                }
            }
            rowIndex++;
        }
    }
}
