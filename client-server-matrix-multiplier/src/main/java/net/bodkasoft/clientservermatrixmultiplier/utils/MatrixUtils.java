package net.bodkasoft.clientservermatrixmultiplier.utils;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import org.springframework.stereotype.Component;

@Component
public class MatrixUtils {
    public static Matrix fillMatrix(int rows, int cols, int value) {
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = value;
            }
        }
        return new Matrix(result);
    }
}
