package net.bodkasoft.wordoccurrencescounter.counter;

import net.bodkasoft.wordoccurrencescounter.document.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    public static Long occurrencesCount(Document document, List<String> searchedWord) {
        long count = 0;
        for(String word: document.getWords()) {
            if(searchedWord.contains(word)) {
                count++;
            }
        }
        return count;
    }
}
