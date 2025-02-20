package net.bodkasoft.bank.producerconsumer.consumer;

import net.bodkasoft.bank.producerconsumer.Drop;

import java.util.Arrays;
import java.util.Random;

public class Consumer implements Runnable {

    private final Drop drop;
    private final Random random = new Random();
    private final int[] consumedInfo;

    public Consumer(Drop drop, int size) {
        this.drop = drop;
        this.consumedInfo = new int[size];
    }

    @Override
    public void run() {
        int message;
        for (int i = 0; i < consumedInfo.length; i++) {
            if ((message = drop.take()) == -1) return;
            consumedInfo[i] = message;
            System.out.println("MESSAGE RECEIVED: " + message);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getConsumedInfo() {
        return Arrays.copyOf(consumedInfo, consumedInfo.length);
    }
}
