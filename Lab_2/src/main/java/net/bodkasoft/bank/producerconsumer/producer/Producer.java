package net.bodkasoft.bank.producerconsumer.producer;

import net.bodkasoft.bank.producerconsumer.Drop;

import java.util.Random;

public class Producer implements Runnable {

    private final Drop drop;
    private final Random random = new Random();
    private final int[] importantInfo;

    public Producer(Drop drop, int[] importantInfo) {
        this.drop = drop;
        this.importantInfo = importantInfo;
    }

    @Override
    public void run() {
        for (int info : importantInfo) {
            drop.put(info);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        drop.put(-1);
    }
}
