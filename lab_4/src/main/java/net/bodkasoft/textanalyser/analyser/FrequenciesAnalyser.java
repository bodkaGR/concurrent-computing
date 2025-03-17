package net.bodkasoft.textanalyser.analyser;

import java.util.List;
import java.util.Map;

public interface FrequenciesAnalyser {
    Map<Integer, Integer> frequencies(List<Integer> wordLengths);
}
