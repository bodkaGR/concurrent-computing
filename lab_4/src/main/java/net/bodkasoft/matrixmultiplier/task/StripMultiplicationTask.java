package net.bodkasoft.matrixmultiplier.task;

import net.bodkasoft.matrixmultiplier.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class StripMultiplicationTask extends RecursiveTask<Matrix> {

    private final Matrix leftMatrix;
    private final Matrix rightMatrix;
    private final int THRESHOLD = 100;

    public StripMultiplicationTask(Matrix leftMatrix, Matrix rightMatrix) {
        this.leftMatrix = leftMatrix;
        this.rightMatrix = rightMatrix;
    }

    @Override
    protected Matrix compute() {
        if (isSmallEnough()) {
            return consistentMultiplication(leftMatrix, rightMatrix);
        }

        Matrix[] subMatrices = splitMatrices();

        List<RecursiveTask<Matrix>> tasks = new ArrayList<>();
        tasks.add(new StripMultiplicationTask(subMatrices[0], subMatrices[2]));
        tasks.add(new StripMultiplicationTask(subMatrices[0], subMatrices[3]));
        tasks.add(new StripMultiplicationTask(subMatrices[1], subMatrices[2]));
        tasks.add(new StripMultiplicationTask(subMatrices[1], subMatrices[3]));

        for (RecursiveTask<Matrix> task : tasks) {
            task.fork();
        }

        List<Matrix> results = new ArrayList<>();
        for (RecursiveTask<Matrix> task : tasks) {
            results.add(task.join());
        }

        return Matrix.unitResults(results);
    }

    private Matrix consistentMultiplication(Matrix leftMatrix, Matrix rightMatrix) {
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

    private Matrix[] splitMatrices() {
        int midRows = leftMatrix.getRows() / 2;
        int midCols = rightMatrix.getCols() / 2;
        return new Matrix[]{
            leftMatrix.copyOfRange(0, midRows, 0, leftMatrix.getCols()),
            leftMatrix.copyOfRange(midRows, leftMatrix.getRows(), 0, leftMatrix.getCols()),
            rightMatrix.copyOfRange(0, leftMatrix.getCols(), 0, midCols),
            rightMatrix.copyOfRange(0, leftMatrix.getCols(), midCols, rightMatrix.getCols())
        };
    }

    private boolean isSmallEnough() {
        return leftMatrix.getRows() <= THRESHOLD ||
               leftMatrix.getCols() <= THRESHOLD ||
               rightMatrix.getCols() <= THRESHOLD;
    }
}
