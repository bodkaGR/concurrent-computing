package net.bodkasoft.textanalyser.analyser.impl;

import net.bodkasoft.textanalyser.analyser.StatisticAnalyser;
import net.bodkasoft.textanalyser.statistic.Statistic;
import net.bodkasoft.textanalyser.task.WordStatisticTask;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class ConcurrentStatisticAnalyser implements StatisticAnalyser {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    @Override
    public Statistic statistic(List<Integer> wordLengths) {
        WordStatisticTask task = new WordStatisticTask(wordLengths, 0, wordLengths.size());
        return forkJoinPool.invoke(task);
    }
}
