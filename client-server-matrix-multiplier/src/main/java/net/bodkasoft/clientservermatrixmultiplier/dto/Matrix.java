package net.bodkasoft.clientservermatrixmultiplier.dto;

import org.springframework.stereotype.Component;

@Component
public class Matrix {
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        double[][] newMatrix = new double[matrix.length][matrix[0].length];
        System.arraycopy(matrix, 0, newMatrix, 0, matrix.length);
        this.matrix = newMatrix;
    }

    public Matrix(Matrix matrix) {
        this.matrix = matrix.getMatrix();
    }

    public Matrix(int rows, int cols) {
        matrix = new double[rows][cols];
    }

    public Matrix() {}

    public double[][] getMatrix() {
        double[][] copyOfMatrix = new double[matrix.length][matrix[0].length];
        System.arraycopy(matrix, 0, copyOfMatrix, 0, matrix.length);
        return copyOfMatrix;
    }

    public double[][] getMatrixPointer() {
        return matrix;
    }

    public void setValue(int row, int col, double value) {
        matrix[row][col] = value;
    }

    public void addItem(int row, int col, double value) {
        matrix[row][col] += value;
    }

    public double getValue(int row, int col) {
        return matrix[row][col];
    }

    public int getRowCount() {
        return matrix.length;
    }

    public int getColumnCount() {
        return matrix[0].length;
    }
}
