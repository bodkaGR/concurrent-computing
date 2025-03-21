package net.bodkasoft.textanalyser.analyser.impl;

import net.bodkasoft.textanalyser.analyser.StatisticAnalyser;
import net.bodkasoft.textanalyser.statistic.Statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsistentStatisticAnalyser implements StatisticAnalyser {

    @Override
    public Statistic statistic(List<Integer> wordLengths) {
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
