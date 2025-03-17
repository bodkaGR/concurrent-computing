package net.bodkasoft.textanalyser.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class WordFrequencyTask extends RecursiveTask<Map<Integer, Integer>> {

    private final List<Integer> words;
    private final int start, end;
    private final int THRESHOLD = 500;

    public WordFrequencyTask(final List<Integer> words, int start, int end) {
        this.words = words;
        this.start = start;
        this.end = end;
    }

    @Override
    public Map<Integer, Integer> compute() {
        if (end - start < THRESHOLD) {
            return countWordsSequentially();
        }else {
            int mid = (start + end) / 2;
            WordFrequencyTask left = new WordFrequencyTask(words, start, mid);
            WordFrequencyTask right = new WordFrequencyTask(words, mid, end);

            left.fork();
            Map<Integer, Integer> rightResult = right.compute();
            Map<Integer, Integer> leftResult = left.join();

            return mergeResults(leftResult, rightResult);
        }
    }

    private Map<Integer, Integer> countWordsSequentially() {
        Map<Integer, Integer> wordFrequencies = new HashMap<>();
        for (int i = start; i < end; i++) {
            wordFrequencies.put(words.get(i), wordFrequencies.getOrDefault(words.get(i), 0) + 1);
        }
        return wordFrequencies;
    }

    private Map<Integer, Integer> mergeResults(Map<Integer, Integer> left, Map<Integer, Integer> right) {
        for(Map.Entry<Integer, Integer> entry : right.entrySet()) {
            left.put(entry.getKey(), left.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        return left;
    }
}
