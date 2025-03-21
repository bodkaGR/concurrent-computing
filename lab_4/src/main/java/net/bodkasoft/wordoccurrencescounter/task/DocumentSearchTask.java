package net.bodkasoft.wordoccurrencescounter.task;

import net.bodkasoft.wordoccurrencescounter.counter.WordCounter;
import net.bodkasoft.wordoccurrencescounter.document.Document;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<Long> {

    private final Document document;
    private final List<String> searchedWord;

    public DocumentSearchTask(Document document, List<String> searchedWord) {
        super();
        this.document = document;
        this.searchedWord = searchedWord;
    }

    @Override
    protected Long compute() {
        return WordCounter.occurrencesCount(document, searchedWord);
    }
}
