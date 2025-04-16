package net.bodkasoft.clientservermatrixmultiplier.controller;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import net.bodkasoft.clientservermatrixmultiplier.service.StoredMatrixService;
import net.bodkasoft.clientservermatrixmultiplier.service.UploadedMatrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiplicationController {

    private final StoredMatrixService storedMatrixService;
    private final UploadedMatrixService uploadedMatrixService;

    public MultiplicationController(StoredMatrixService storedMatrixService, UploadedMatrixService uploadedMatrixService) {
        this.storedMatrixService = storedMatrixService;
        this.uploadedMatrixService = uploadedMatrixService;
    }

    @GetMapping("/multiply-server-stored")
    public double[][] multiplyServerStoredMatrix() {
        Matrix resultMatrix = storedMatrixService.multiplyStoredMatrices();
        return resultMatrix.getMatrix();
    }
}
