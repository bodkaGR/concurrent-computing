package net.bodkasoft.matrixmultiplier.matrix;

import java.util.List;

public class Matrix {

    private final int[][] matrix;
    private final int rows;
    private final int cols;

    public Matrix(int[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, cols);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int[] getRow(int index) {
        return matrix[index];
    }

    public int getItem(int row, int col) {
        return matrix[row][col];
    }

    public Matrix copyOfRange(int startRow, int endRow, int startCol, int endCol) {
        if (startRow < 0 || endRow > rows || startCol < 0 || endCol > cols || startRow >= endRow || startCol >= endCol) {
            throw new IllegalArgumentException("Invalid range for submatrix");
        }

        int newRows = endRow - startRow;
        int newCols = endCol - startCol;
        int[][] subMatrix = new int[newRows][newCols];

        for (int i = 0; i < newRows; i++) {
            System.arraycopy(matrix[startRow + i], startCol, subMatrix[i], 0, newCols);
        }

        return new Matrix(subMatrix);
    }

    public static Matrix unitResults(List<Matrix> results) {
        if (results == null || results.isEmpty()) {
            throw new IllegalArgumentException("Results list cannot be empty");
        }

        int totalRows = results.get(0).getRows() + results.get(2).getRows();
        int totalCols = results.get(0).getCols() + results.get(1).getCols();
        int[][] combinedData = new int[totalRows][totalCols];

        for (int i = 0; i < results.get(0).getRows(); i++) {
            System.arraycopy(results.get(0).getRow(i), 0, combinedData[i], 0, results.get(0).getCols());
        }
        for (int i = 0; i < results.get(1).getRows(); i++) {
            System.arraycopy(results.get(1).getRow(i), 0, combinedData[i], results.get(0).getCols(), results.get(1).getCols());
        }
        for (int i = 0; i < results.get(2).getRows(); i++) {
            System.arraycopy(results.get(2).getRow(i), 0, combinedData[results.get(0).getRows() + i], 0, results.get(2).getCols());
        }
        for (int i = 0; i < results.get(3).getRows(); i++) {
            System.arraycopy(results.get(3).getRow(i), 0, combinedData[results.get(0).getRows() + i], results.get(0).getCols(), results.get(3).getCols());
        }

        return new Matrix(combinedData);
    }
}
