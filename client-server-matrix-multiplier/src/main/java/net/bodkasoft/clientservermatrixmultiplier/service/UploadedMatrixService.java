package net.bodkasoft.clientservermatrixmultiplier.service;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import net.bodkasoft.clientservermatrixmultiplier.multiplier.MatrixMultiplier;
import org.springframework.stereotype.Service;

@Service
public class UploadedMatrixService {

    private final MatrixMultiplier matrixMultiplier;

    public UploadedMatrixService(final MatrixMultiplier matrixMultiplier) {
        this.matrixMultiplier = matrixMultiplier;
    }

    public Matrix multiplyUploadedMatrices(Matrix matrixA, Matrix matrixB) {
        return matrixMultiplier.multiply(matrixA, matrixB);
    }
}
