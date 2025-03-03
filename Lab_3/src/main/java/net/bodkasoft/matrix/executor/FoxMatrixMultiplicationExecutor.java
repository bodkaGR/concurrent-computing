package net.bodkasoft.matrix.executor;

import net.bodkasoft.matrix.thread.FoxMatrixTask;
import net.bodkasoft.matrix.utils.Result;

public class FoxMatrixMultiplicationExecutor extends Executor {

    private final int q = (int) Math.sqrt(threadsAmount);

    public FoxMatrixMultiplicationExecutor(Result result, int threadsAmount) {
        super(result, threadsAmount);
    }

    @Override
    public void execute(int[][] matrixA, int[][] matrixB) {
        if (matrixA.length % q != 0) {
            System.out.println("Impossible to multiply matrix. Length must divide by sqrt(threadsAmount)");
            return;
        }

        int blockSize = matrixA.length / q;
        int gridSize = matrixA.length / blockSize;

        Thread[][] threads = new Thread[gridSize][gridSize];

        for (int stage = 0; stage < gridSize; stage++) {
            for(int i = 0; i < gridSize; i++) {
                for(int j = 0; j < gridSize; j++) {
                    int row = i * blockSize;
                    int col = j * blockSize;
                    int shared = ((i + stage) % gridSize) * blockSize;

                    int[][] subA = extractBlock(matrixA, row, shared, blockSize);
                    int[][] subB = extractBlock(matrixB, shared, col, blockSize);

                    threads[i][j] = new Thread(new FoxMatrixTask(subA, subB, row, col, result));
                    threads[i][j].start();
                }
            }
        }

        super.waitForThreads(threads);
    }

    private int[][] extractBlock(int[][] matrix, int row, int col, int blockSize) {
        int[][] block = new int[blockSize][blockSize];
        for(int i = 0; i < blockSize; i++) {
            for(int j = 0; j < blockSize; j++) {
                block[i][j] = matrix[row + i][col + j];
            }
        }
        return block;
    }
}
