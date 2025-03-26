package net.bodkasoft.queueingtheory;

import java.util.concurrent.ThreadLocalRandom;

public class Equations {
    public static int evenDistribution(int tMin, int tMax) {
        return tMin + ThreadLocalRandom.current().nextInt(tMax - tMin + 1);
    }

    public static long normalDistribution(double mean, double stdDev) {
        return (long) (mean + stdDev * ThreadLocalRandom.current().nextGaussian());
    }
}
