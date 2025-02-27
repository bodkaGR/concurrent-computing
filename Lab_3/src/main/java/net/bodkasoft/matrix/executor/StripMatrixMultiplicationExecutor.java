package net.bodkasoft.matrix.executor;

import net.bodkasoft.matrix.utils.Result;
import net.bodkasoft.matrix.matrixmultiplier.StripMatrixMultiplier;
import net.bodkasoft.matrix.thread.MatrixTask;

public class StripMatrixMultiplicationExecutor {

    public void execute(int[][] matrixA, int[][] matrixB, Result result, int threadsAmount) {
        Thread[] threads = new Thread[threadsAmount];
        int rowsPerThread = matrixA.length / threadsAmount;
        int extraRows = matrixA.length % threadsAmount;
        int startRow = 0;

        for (int i = 0; i < threadsAmount; i++) {
            int endRow = startRow + rowsPerThread + (i < extraRows ? 1 : 0);
            int[][] subMatrixA = new int[endRow - startRow][];
            System.arraycopy(matrixA, startRow, subMatrixA, 0, endRow - startRow);

            threads[i] = new Thread(new MatrixTask(subMatrixA, matrixB, new StripMatrixMultiplier(result, startRow)));
            threads[i].start();
            startRow = endRow;
        }

        waitForThreads(threads);
    }

    private void waitForThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
