package net.bodkasoft.clientservermatrixmultiplier.service;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import net.bodkasoft.clientservermatrixmultiplier.multiplier.MatrixMultiplier;
import net.bodkasoft.clientservermatrixmultiplier.utils.MatrixUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StoredMatrixService {

    @Value("${matrix.size}")
    private int matrixSize;

    private final MatrixMultiplier matrixMultiplier;

    public StoredMatrixService(final MatrixMultiplier matrixMultiplier) {
        this.matrixMultiplier = matrixMultiplier;
    }

    public Matrix multiplyStoredMatrices() {
        Matrix storedA = MatrixUtils.fillMatrix(matrixSize, matrixSize, 10);
        Matrix storedB = MatrixUtils.fillMatrix(matrixSize, matrixSize, 10);
        return matrixMultiplier.multiply(storedA, storedB);
    }
}
