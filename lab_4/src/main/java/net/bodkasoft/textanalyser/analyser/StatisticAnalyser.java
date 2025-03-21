package net.bodkasoft.textanalyser.analyser;

import net.bodkasoft.textanalyser.statistic.Statistic;

import java.util.List;
import java.util.Map;

public interface StatisticAnalyser {
    Statistic statistic(List<Integer> wordLengths);
}
