package net.bodkasoft.textanalyser.analyser.impl;

import net.bodkasoft.textanalyser.analyser.FrequenciesAnalyser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsistentFrequenciesAnalyser implements FrequenciesAnalyser {

    @Override
    public Map<Integer, Integer> frequencies(List<Integer> wordLengths) {
        return wordLengths.stream()
                .collect(Collectors.groupingBy(wordLength -> wordLength, Collectors.summingInt(wordLength -> 1)));
    }
}
