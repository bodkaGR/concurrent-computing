package net.bodkasoft.wordoccurrencescounter.task;

import net.bodkasoft.wordoccurrencescounter.document.Document;
import net.bodkasoft.wordoccurrencescounter.folder.Folder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class CommonWordsTask extends RecursiveTask<Set<String>> {

    private final Folder folder;

    public CommonWordsTask(Folder folder) {
        this.folder = folder;
    }

    @Override
    protected Set<String> compute() {
        List<RecursiveTask<Set<String>>> tasks = new ArrayList<>();

        for (Folder subFolder : folder.getSubFolders()) {
            CommonWordsTask task = new CommonWordsTask(subFolder);
            tasks.add(task);
            task.fork();
        }

        Set<String> commonWords = findCommonWords(folder);

        for (RecursiveTask<Set<String>> task : tasks) {
            Set<String> subResult = task.join();
            if (commonWords == null) {
                commonWords = subResult;
            } else {
                commonWords.retainAll(subResult);
            }
        }

        return commonWords != null ? commonWords : Collections.emptySet();
    }

    private Set<String> findCommonWords(Folder folder) {
        Set<String> commonWords = null;
        for (Document document : folder.getDocuments()) {
            Set<String> words = new HashSet<>(document.getWords());
            if (commonWords == null) {
                commonWords = words;
            } else {
                commonWords.retainAll(words);
            }
        }
        return commonWords;
    }
}
