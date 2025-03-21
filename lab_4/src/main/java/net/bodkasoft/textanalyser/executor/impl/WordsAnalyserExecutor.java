package net.bodkasoft.textanalyser.executor.impl;

import net.bodkasoft.textanalyser.analyser.StatisticAnalyser;
import net.bodkasoft.textanalyser.executor.Executor;
import net.bodkasoft.textanalyser.reader.TextReader;
import net.bodkasoft.textanalyser.statistic.Statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsAnalyserExecutor implements Executor {

    private final StatisticAnalyser analyser;
    private final TextReader textReader;
    private Statistic statistic = new Statistic();
    private final String algorithmName;
//    private long completionTime = 0;

    public WordsAnalyserExecutor(TextReader textReader, StatisticAnalyser analyser, String algorithmName) {
        this.analyser = analyser;
        this.textReader = textReader;
        this.algorithmName = algorithmName;
    }

    @Override
    public Statistic execute() {
        // Chunk reader
//        textReader.readWordsLengthsInChunks(10000, wordLengths -> {
//            long start = System.currentTimeMillis();
//            Statistic analysedChunk = analyser.statistic(wordLengths);
//            long end = System.currentTimeMillis();
//            completionTime += end - start;
//            this.statistic = Statistic.unite(new Statistic(this.statistic), analysedChunk);
//        });
//
//        System.out.println("Time computing: " + completionTime);

        // Whole text reader
        List<Integer> wordLengths = textReader.readWordsLengths();

        long startTime = System.currentTimeMillis();
        statistic = analyser.statistic(wordLengths);
        long endTime = System.currentTimeMillis();

        System.out.println("Words analysis by " + algorithmName + " took " + (endTime - startTime) + "ms");

        return statistic;
    }
}
