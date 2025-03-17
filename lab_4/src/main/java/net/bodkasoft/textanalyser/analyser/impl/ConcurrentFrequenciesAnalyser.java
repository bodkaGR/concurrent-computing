package net.bodkasoft.textanalyser.analyser.impl;

import net.bodkasoft.textanalyser.analyser.FrequenciesAnalyser;
import net.bodkasoft.textanalyser.task.WordFrequencyTask;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentFrequenciesAnalyser implements FrequenciesAnalyser {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    @Override
    public Map<Integer, Integer> frequencies(List<Integer> wordLengths) {
        WordFrequencyTask task = new WordFrequencyTask(wordLengths, 0, wordLengths.size());
        return forkJoinPool.invoke(task);
    }
}
