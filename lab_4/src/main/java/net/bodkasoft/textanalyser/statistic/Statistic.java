package net.bodkasoft.textanalyser.statistic;

import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private final Map<Integer, Integer> frequencies;
    private final double mean;
    private final double variance;
    private final double standardDeviation;
    private final int min, max;
    private final double count;

    public Statistic(double mean, double variance, double standardDeviation, Map<Integer, Integer> frequencies, int min, int max, double count) {
        this.mean = mean;
        this.variance = variance;
        this.standardDeviation = standardDeviation;
        this.min = min;
        this.max = max;
        this.count = count;
        this.frequencies = frequencies;
    }

    public Statistic() {
        this(0, 0, 0, new HashMap<>(), Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    }

    public Statistic(Statistic statistic) {
        this.mean = statistic.mean;
        this.variance = statistic.variance;
        this.standardDeviation = statistic.standardDeviation;
        this.min = statistic.min;
        this.max = statistic.max;
        this.count = statistic.count;
        this.frequencies = statistic.frequencies;
    }

    public double getMean() {
        return mean;
    }

    public double getVariance() {
        return variance;
    }

    public Map<Integer, Integer> getFrequencies() {
        return frequencies;
    }

    public static Statistic unite(Statistic left, Statistic right) {
        double n1 = left.count;
        double n2 = right.count;

        double totalSum = left.mean * n1 + right.mean * n2;
        double totalCount = n1 + n2;
        double mean = totalSum / totalCount;

        double variance = ((n1 - 1) * left.variance + (n2 - 1) * right.variance + (n1 * n2 / totalCount) * Math.pow(left.mean - right.mean, 2)) / (totalCount - 1);

        double standardDeviation = Math.sqrt(variance);

        int min = Math.min(left.min, right.min);
        int max = Math.max(left.max, right.max);

        Map<Integer, Integer> frequencies = mergeFrequencies(left.frequencies, right.frequencies);
        return new Statistic(mean, variance, standardDeviation, frequencies, min, max, totalCount);
    }

    private static Map<Integer, Integer> mergeFrequencies(Map<Integer, Integer> left, Map<Integer, Integer> right) {
        for(Map.Entry<Integer, Integer> entry : right.entrySet()) {
            left.put(entry.getKey(), left.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        return left;
    }

    @Override
    public String toString() {
        return "Statistic [mean= " + mean + ", variance= " + variance + ", standardDeviation= " + standardDeviation + ", min= " + min + ", max= " + max + "]";
    }
}
