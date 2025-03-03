package net.bodkasoft.matrix.executor;

import net.bodkasoft.matrix.utils.Result;
import net.bodkasoft.matrix.matrixmultiplier.StripMatrixMultiplier;
import net.bodkasoft.matrix.thread.StripMatrixTask;

public class StripMatrixMultiplicationExecutor extends Executor {

    public StripMatrixMultiplicationExecutor(Result result, int threadsAmount) {
        super(result, threadsAmount);
    }

    @Override
    public void execute(int[][] matrixA, int[][] matrixB) {
        Thread[] threads = new Thread[threadsAmount];
        int rowsPerThread = matrixA.length / threadsAmount;
        int extraRows = matrixA.length % threadsAmount;
        int startRow = 0;

        for (int i = 0; i < threadsAmount; i++) {
            int endRow = startRow + rowsPerThread + (i < extraRows ? 1 : 0);
            int[][] subMatrixA = new int[endRow - startRow][];
            System.arraycopy(matrixA, startRow, subMatrixA, 0, endRow - startRow);

            threads[i] = new Thread(new StripMatrixTask(subMatrixA, matrixB, new StripMatrixMultiplier(result, startRow)));
            threads[i].start();
            startRow = endRow;
        }

        super.waitForThreads(threads);
    }
}
