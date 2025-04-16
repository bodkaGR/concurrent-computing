package net.bodkasoft.clientservermatrixmultiplier.service;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import net.bodkasoft.clientservermatrixmultiplier.multiplier.MatrixMultiplier;
import net.bodkasoft.clientservermatrixmultiplier.utils.MatrixUtils;
import org.springframework.stereotype.Service;

@Service
public class StoredMatrixService {

    private final Matrix storedA = MatrixUtils.fillMatrix(10, 10, 10);
    private final Matrix storedB = MatrixUtils.fillMatrix(10, 10, 10);
    private final MatrixMultiplier matrixMultiplier;

    public StoredMatrixService(final MatrixMultiplier matrixMultiplier) {
        this.matrixMultiplier = matrixMultiplier;
    }

    public Matrix multiplyStoredMatrices() {
        return matrixMultiplier.multiply(storedA, storedB);
    }
}
