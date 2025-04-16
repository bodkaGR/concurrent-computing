package net.bodkasoft.clientservermatrixmultiplier.multiplier;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import org.springframework.stereotype.Component;

@Component
public interface MatrixMultiplier {
    Matrix multiply(Matrix matrixA, Matrix matrixB);
}
