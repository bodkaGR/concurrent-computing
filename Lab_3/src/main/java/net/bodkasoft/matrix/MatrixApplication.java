package net.bodkasoft.matrix;

import net.bodkasoft.matrix.matrixmultiplier.ConsistentMatrixMultiplier;
import net.bodkasoft.matrix.matrixmultiplier.MatrixMultiplier;

import java.util.Random;

public class MatrixApplication {

    public static void main(String[] args) {
        int[][] matrixA = generateMatrix(3, 3);
        int[][] matrixB = generateMatrix(3, 3);

        System.out.println("<---Input matrices--->");
        print(matrixA);
        System.out.println();
        print(matrixB);
        System.out.println();

        Result result = new Result(matrixA.length, matrixB[0].length);

        MatrixMultiplier matrixMultiplier = new ConsistentMatrixMultiplier(result);
        matrixMultiplier.multiply(matrixA, matrixB);

        result.printResult();
    }

    private static int[][] generateMatrix(int rows, int cols) {
        Random rand = new Random();
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(20);
            }
        }
        return matrix;
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}