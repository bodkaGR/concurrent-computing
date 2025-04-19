package net.bodkasoft.clientservermatrixmultiplier.controller;

import lombok.extern.slf4j.Slf4j;
import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import net.bodkasoft.clientservermatrixmultiplier.service.StoredMatrixService;
import net.bodkasoft.clientservermatrixmultiplier.service.UploadedMatrixService;
import net.bodkasoft.clientservermatrixmultiplier.utils.MatrixUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class MultiplicationController {

    private final StoredMatrixService storedMatrixService;
    private final UploadedMatrixService uploadedMatrixService;
    private static final Logger logger = LoggerFactory.getLogger(MultiplicationController.class);

    public MultiplicationController(StoredMatrixService storedMatrixService, UploadedMatrixService uploadedMatrixService) {
        this.storedMatrixService = storedMatrixService;
        this.uploadedMatrixService = uploadedMatrixService;
    }

    @GetMapping("/multiply-server-stored")
    public ResponseEntity<Matrix> multiplyServerStoredMatrix(@RequestParam int size) {
        logger.info("Multiplication with server stored data initiated");
        Matrix resultMatrix = storedMatrixService.multiplyStoredMatrices(size);
        logger.info("Multiplication with server stored data completed");
        return ResponseEntity.ok(resultMatrix);
    }

    @PostMapping("/multiply-client-stored")
    public ResponseEntity<Matrix> multiplyClientStoredMatrix(
            @RequestParam("matrixA") MultipartFile fileA,
            @RequestParam("matrixB") MultipartFile fileB
    ) {
        logger.info("Multiplication with client stored data initiated");
        try {
            Matrix matrixA = MatrixUtils.parseCsvToMatrix(fileA);
            Matrix matrixB = MatrixUtils.parseCsvToMatrix(fileB);

            Matrix resultMatrix = uploadedMatrixService.multiplyUploadedMatrices(matrixA, matrixB);
            logger.info("Multiplication with client stored data completed");
            return ResponseEntity.ok(resultMatrix);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/generate-matrix")
    public ResponseEntity<Void> generateMatrix(
            @RequestParam int rows,
            @RequestParam int cols,
            @RequestParam double value,
            @RequestParam String fileName
    ) {
        Matrix matrix = MatrixUtils.fillMatrix(rows, cols, value);
        try {
            MatrixUtils.writeMatrixToCsv(matrix, fileName);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
