package net.bodkasoft.matrix.executor;

import net.bodkasoft.matrix.utils.Result;
import net.bodkasoft.matrix.matrixmultiplier.ConsistentMatrixMultiplier;
import net.bodkasoft.matrix.utils.MatrixUtils;

import java.util.Scanner;

public class MatrixMultiplicationExecutor {

    public static void run(int[][] matrixA, int[][] matrixB, int threadsAmount) {
        displayMatrices(matrixA, matrixB);
        int choice = getUserChoice();
        if (choice == 0) return;

        Result result = new Result(matrixA.length, matrixB[0].length);
        long start, end;

        switch (choice) {
            case 1 -> {
                start = System.currentTimeMillis();
                new ConsistentMatrixMultiplier(result).multiply(matrixA, matrixB);
                end = System.currentTimeMillis();
                System.out.println("Consistent matrix multiplication took: " + (end - start) + "ms");
                result.printResult();
            }
            case 2 -> {
                start = System.currentTimeMillis();
                new StripMatrixMultiplicationExecutor().execute(matrixA, matrixB, result, threadsAmount);
                end = System.currentTimeMillis();
                System.out.println("Strip matrix multiplication took: " + (end - start) + "ms");
                result.printResult();
            }
            case 3 -> System.out.println("Fox Matrix multiplication is not yet implemented");
            default -> System.out.println("Invalid choice");
        }
    }

    private static void displayMatrices(int[][] matrixA, int[][] matrixB) {
        System.out.println("<---Input matrices--->");
        MatrixUtils.printMatrix("<---Matrix A--->", matrixA);
        System.out.println();
        MatrixUtils.printMatrix("<---Matrix B--->", matrixB);
        System.out.println();
    }

    private static int getUserChoice() {
        System.out.println("""
                [1] - Consistent matrix multiplication
                [2] - Tape matrix multiplication
                [3] - Fox matrix multiplication
                [0] - Exit
                """);
        System.out.print("Enter your choice: ");
        return new Scanner(System.in).nextInt();
    }
}
