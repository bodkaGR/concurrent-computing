package net.bodkasoft.textanalyser.executor.impl;

import net.bodkasoft.textanalyser.analyser.FrequenciesAnalyser;
import net.bodkasoft.textanalyser.executor.Executor;
import net.bodkasoft.textanalyser.reader.TextReader;

import java.util.HashMap;
import java.util.Map;

public class WordsAnalyserExecutor implements Executor {

    private final FrequenciesAnalyser analyser;
    private final TextReader textReader;
    private final Map<Integer, Integer> wordFrequencies = new HashMap<>();

    public WordsAnalyserExecutor(TextReader textReader, FrequenciesAnalyser analyser) {
        this.analyser = analyser;
        this.textReader = textReader;
    }

    @Override
    public void execute() {
        textReader.readWordsLengthsInChunks(1000, wordLengths -> {
            Map<Integer, Integer> analysedChunk = analyser.frequencies(wordLengths);
            analysedChunk.forEach((word, frequency) -> {
                wordFrequencies.merge(word, frequency, Integer::sum);
            });
        });
    }

    public Map<Integer, Integer> getWordFrequencies() {
        return wordFrequencies;
    }
}
