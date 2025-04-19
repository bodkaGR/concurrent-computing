package net.bodkasoft.clientservermatrixmultiplier.multiplier;

import net.bodkasoft.clientservermatrixmultiplier.dto.Matrix;
import net.bodkasoft.clientservermatrixmultiplier.multiplier.thread.StripMatrixTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripMatrixMultiplier implements MatrixMultiplier {

    @Value("${matrix.threads}")
    private int threadsAmount;

    @Override
    public Matrix multiply(Matrix matrixA, Matrix matrixB) {
        Matrix resultMatrix = new Matrix(matrixA.getRowCount(), matrixB.getColumnCount());

        Thread[] threads = new Thread[threadsAmount];
        int rowsPerThread = matrixA.getRowCount() / threadsAmount;
        int extraRows = matrixA.getRowCount() % threadsAmount;
        int startRow = 0;

        for (int i = 0; i < threadsAmount; i++) {
            int endRow = startRow + rowsPerThread + (i < extraRows ? 1 : 0);

            double[][] fullMatrix = matrixA.getMatrixPointer();
            double[][] partial = new double[endRow - startRow][matrixA.getColumnCount()];
            System.arraycopy(fullMatrix, startRow, partial, 0, endRow - startRow);
            Matrix subMatrixA = new Matrix(partial);

            threads[i] = new Thread(new StripMatrixTask(subMatrixA, matrixB, resultMatrix, startRow));
            threads[i].start();
            startRow = endRow;
        }

        waitForThreads(threads);

        return resultMatrix;
    }

    private void waitForThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
