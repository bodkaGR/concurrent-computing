package net.bodkasoft.textanalyser;

import net.bodkasoft.textanalyser.analyser.impl.ConcurrentStatisticAnalyser;
import net.bodkasoft.textanalyser.analyser.impl.ConsistentStatisticAnalyser;
import net.bodkasoft.textanalyser.executor.Executor;
import net.bodkasoft.textanalyser.executor.impl.WordsAnalyserExecutor;
import net.bodkasoft.textanalyser.reader.TextReader;
import net.bodkasoft.textanalyser.statistic.Statistic;

import javax.swing.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        TextReader textReader = new TextReader("200mb.txt");

        Statistic consistentStatistic = run(new WordsAnalyserExecutor(textReader, new ConsistentStatisticAnalyser(), "consistent"));
        Statistic concurrentStatistic = run(new WordsAnalyserExecutor(textReader, new ConcurrentStatisticAnalyser(), "concurrent"));

        System.out.println();

        System.out.println("Consistent " + consistentStatistic);
        System.out.println("Concurrent " + concurrentStatistic);

        Map<Integer, Integer> frequencies = consistentStatistic.getFrequencies();
//        for (Map.Entry<Integer, Integer> entry: frequencies.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

        SwingUtilities.invokeLater(() -> {
            HistogramChart chart = new HistogramChart(frequencies);
            chart.setVisible(true);
        });
    }

    private static Statistic run(Executor executor) {
        return executor.execute();
    }
}