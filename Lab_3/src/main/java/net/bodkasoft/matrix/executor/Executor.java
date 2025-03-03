package net.bodkasoft.matrix.executor;

import net.bodkasoft.matrix.utils.Result;

public abstract class Executor {

    protected Result result;
    protected int threadsAmount;

    protected Executor(Result result, int threadsAmount) {
        this.result = result;
        this.threadsAmount = threadsAmount;
    }

    protected abstract void execute(int[][] matrixA, int[][] matrixB);

    protected void waitForThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    protected void waitForThreads(Thread[][] threads) {
        for (Thread[] thread : threads) {
            for (Thread value : thread) {
                try {
                    value.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
