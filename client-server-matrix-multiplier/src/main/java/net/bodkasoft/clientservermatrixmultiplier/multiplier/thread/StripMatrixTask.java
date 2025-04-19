package net.bodkasoft.clientservermatrixmultiplier.multiplier.thread;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;

public class StripMatrixTask implements Runnable {

    private final Matrix matrixA;
    private final Matrix matrixB;
    private final Matrix resultMatrix;
    private int rowIndex;

    public StripMatrixTask(final Matrix matrixA, final Matrix matrixB, final Matrix resultMatrix, int rowIndex) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.resultMatrix = resultMatrix;
        this.rowIndex = rowIndex;
    }

    @Override
    public void run() {
        for (int i = 0; i < matrixA.getRowCount(); i++) {
            for (int j = 0; j < matrixB.getColumnCount(); j++) {
                for (int k = 0; k < matrixA.getColumnCount(); k++) {
                    resultMatrix.addItem(rowIndex, j, matrixA.getValue(i, k) * matrixB.getValue(k, j));
                }
            }
            rowIndex++;
        }
    }
}
