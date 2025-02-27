package net.bodkasoft.matrix.utils;

import java.util.Arrays;

public class Result {

    private final int[][] resultMatrix;

    public Result(int rows, int cols) {
        resultMatrix = new int[rows][cols];
    }

    public void addItem(int row, int col, int item) {
        resultMatrix[row][col] += item;
    }

    public void printResult() {
        System.out.println("\n<---Result matrix--->");
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < resultMatrix[i].length; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getResultMatrix() {
        return Arrays.copyOf(resultMatrix, resultMatrix.length);
    }
}
