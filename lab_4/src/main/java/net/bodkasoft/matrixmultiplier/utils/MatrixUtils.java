package net.bodkasoft.matrixmultiplier.utils;

import net.bodkasoft.matrixmultiplier.matrix.Matrix;

import java.util.Random;

public class MatrixUtils {
    public static int[][] generateMatrix(int rows, int cols) {
        Random rand = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
//                matrix[i][j] = rand.nextInt(100);
                matrix[i][j] = 13;
            }
        }
        return matrix;
    }

    public static void printMatrix(String title, Matrix matrix) {
        System.out.println(title);
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                System.out.print(matrix.getItem(i, j) + " ");
            }
            System.out.println();
        }
    }

    public static void printMatrix(String title, int[][] matrix) {
        System.out.println(title);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
