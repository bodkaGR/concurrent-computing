package net.bodkasoft.wordoccurrencescounter.task;

import net.bodkasoft.wordoccurrencescounter.document.Document;
import net.bodkasoft.wordoccurrencescounter.folder.Folder;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSearchTask extends RecursiveTask<Long> {

    private final Folder folder;
    private final List<String> searchedWords;

    public FolderSearchTask(Folder folder, List<String> searchedWords) {
        super();
        this.folder = folder;
        this.searchedWords = searchedWords;
    }


    @Override
    protected Long compute() {
        long count = 0L;

        List<RecursiveTask<Long>> tasks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder, searchedWords);
            tasks.add(task);
            task.fork();
        }

        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document, searchedWords);
            tasks.add(task);
            task.fork();
        }

        for (RecursiveTask<Long> task : tasks) {
            count += task.join();
        }

        return count;
    }
}
