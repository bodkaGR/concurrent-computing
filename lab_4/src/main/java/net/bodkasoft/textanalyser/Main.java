package net.bodkasoft.textanalyser;

import net.bodkasoft.textanalyser.analyser.impl.ConcurrentFrequenciesAnalyser;
import net.bodkasoft.textanalyser.analyser.impl.ConsistentFrequenciesAnalyser;
import net.bodkasoft.textanalyser.executor.impl.WordsAnalyserExecutor;
import net.bodkasoft.textanalyser.reader.TextReader;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TextReader textReader = new TextReader("war-and-peace.txt");

        long start = System.currentTimeMillis();
        WordsAnalyserExecutor executor = new WordsAnalyserExecutor(textReader, new ConcurrentFrequenciesAnalyser());
        executor.execute();
        long end = System.currentTimeMillis();

        Map<Integer, Integer> result = executor.getWordFrequencies();
        result.forEach((word, count) -> System.out.println(word + ": " + count));

        System.out.println("Total time: " + (end - start) + "ms");
    }
}