package net.bodkasoft.queueingtheory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Equations {

    private static final Random random = ThreadLocalRandom.current();

    public static double interProducing(double producerArrivalRate) {
        return -Math.log(1 - random.nextDouble()) / producerArrivalRate;
    }

    public static double consumptionTime(double consumerServiceRate) {
        return -Math.log(1 - random.nextDouble()) / consumerServiceRate;
    }
}
