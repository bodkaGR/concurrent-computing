package net.bodkasoft.matrix.thread;

import net.bodkasoft.matrix.utils.Result;

public class FoxMatrixTask implements Runnable {

    private final int[][] blockA, blockB;
    private final Result result;
    private final int rowOffSet, colOffSet;

    public FoxMatrixTask(int[][] blockA, int[][] blockB, int rowOffSet, int colOffSet, Result result) {
        this.blockA = blockA;
        this.blockB = blockB;
        this.result = result;
        this.rowOffSet = rowOffSet;
        this.colOffSet = colOffSet;
    }

    @Override
    public void run() {
        for (int i = 0; i < blockA.length; i++) {
            for (int j = 0; j < blockA.length; j++) {
                for (int k = 0; k < blockA.length; k++) {
                    result.addItem(rowOffSet + i, colOffSet + j, blockA[i][k] * blockB[k][j]);
                }
            }
        }
    }
}
