package net.bodkasoft.clientservermatrixmultiplier.dto;

import lombok.Data;

@Data
public class MatrixRequest {
    private double[][] matrixA;
    private double[][] matrixB;
}
