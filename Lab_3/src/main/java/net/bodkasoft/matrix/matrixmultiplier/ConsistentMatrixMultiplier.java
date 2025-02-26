package net.bodkasoft.matrix.matrixmultiplier;

import net.bodkasoft.matrix.Result;

public class ConsistentMatrixMultiplier implements MatrixMultiplier {

    private final Result result;

    public ConsistentMatrixMultiplier(Result result) {
        this.result = result;
    }

    @Override
    public void multiply(int[][] matrixA, int[][] matrixB) {
        if (matrixA.length != matrixB[0].length) {
            System.out.println("Multiplication Not Possible");
            return;
        }

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixB[0].length; j++) {
                for(int k = 0; k < matrixB[0].length; k++) {
                    result.addItem(i, j, matrixA[i][k] * matrixB[k][j]);
                }
            }
        }
    }

}
