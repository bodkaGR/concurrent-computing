package net.bodkasoft.bank.producerconsumer;

import net.bodkasoft.bank.producerconsumer.consumer.Consumer;
import net.bodkasoft.bank.producerconsumer.producer.Producer;

import java.util.Arrays;
import java.util.Random;

public class ProducerConsumerApplication {

    private static final int ARRAY_SIZE = 5;

    public static void main(String[] args) {
        Drop drop = new Drop();

        int[] info = initArray();

        Producer producer = new Producer(drop, info);
        Consumer consumer = new Consumer(drop, ARRAY_SIZE);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Process completed");
    }

    private static int[] initArray() {
        int[] array = new int[ARRAY_SIZE];
        for(int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }
}
