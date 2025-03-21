package net.bodkasoft.textanalyser.task;

import net.bodkasoft.textanalyser.statistic.Statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class WordStatisticTask extends RecursiveTask<Statistic> {

    private final List<Integer> wordLengths;
    private final int start, end;
    private final int THRESHOLD = 10000;

    public WordStatisticTask(final List<Integer> wordLengths, int start, int end) {
        this.wordLengths = wordLengths;
        this.start = start;
        this.end = end;
    }

    @Override
    public Statistic compute() {
        if (end - start <= THRESHOLD) {
            return computeStatisticSequentially(wordLengths.subList(start, end));
        }else {
            int mid = (start + end) / 2;
            WordStatisticTask left = new WordStatisticTask(wordLengths, start, mid);
            WordStatisticTask right = new WordStatisticTask(wordLengths, mid, end);

            left.fork();
            Statistic rightResult = right.compute();
            Statistic leftResult = left.join();

            return Statistic.unite(leftResult, rightResult);
        }
    }

    private Statistic computeStatisticSequentially(List<Integer> wordLengths) {
        int sum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        double variance = 0;
        int count = 0;

        for (int wordLength : wordLengths) {
            sum += wordLength;
            count++;
            min = Math.min(min, wordLength);
            max = Math.max(max, wordLength);
        }
        double mean = sum / (double) wordLengths.size();
        for (int wordLength : wordLengths) {
            variance += Math.pow(wordLength - mean, 2);
        }
        variance /= wordLengths.size();

        Map<Integer, Integer> frequencies = frequencies(wordLengths);
        return new Statistic(mean, variance, Math.sqrt(variance), frequencies, min, max, count);
    }

    private Map<Integer, Integer> frequencies(List<Integer> wordLengths) {
        Map<Integer, Integer> wordFrequencies = new HashMap<>();
        for (Integer wordLength : wordLengths) {
            wordFrequencies.put(wordLength, wordFrequencies.getOrDefault(wordLength, 0) + 1);
        }
        return wordFrequencies;
    }
}
