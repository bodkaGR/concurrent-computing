package net.bodkasoft.matrixmultiplier.multiplier;

import net.bodkasoft.matrixmultiplier.matrix.Matrix;

public interface MatrixMultiplier {
    Matrix multiply(Matrix leftMatrix, Matrix rightMatrix);
}
