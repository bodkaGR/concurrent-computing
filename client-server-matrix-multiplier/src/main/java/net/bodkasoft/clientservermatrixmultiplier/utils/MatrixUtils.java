package net.bodkasoft.clientservermatrixmultiplier.utils;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MatrixUtils {
    public static Matrix fillMatrix(int rows, int cols, double value) {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = value;
            }
        }
        return new Matrix(result);
    }

    public static Matrix parseCsvToMatrix(MultipartFile file) throws IOException {
        List<double[]> rows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(",");
            double[] row = Arrays.stream(tokens)
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            rows.add(row);
        }

        double[][] matrixArray = rows.toArray(new double[0][]);
        return new Matrix(matrixArray);
    }

    public static void writeMatrixToCsv(Matrix matrix, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            double[][] data = matrix.getMatrixPointer(); // якщо хочеш без копій

            for (double[] row : data) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < row.length; i++) {
                    line.append(row[i]);
                    if (i < row.length - 1) {
                        line.append(",");
                    }
                }
                writer.write(line.toString());
                writer.newLine();
            }
        }
    }
}
