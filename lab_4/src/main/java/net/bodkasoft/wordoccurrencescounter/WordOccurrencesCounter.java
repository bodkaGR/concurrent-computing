package net.bodkasoft.wordoccurrencescounter;

import net.bodkasoft.wordoccurrencescounter.folder.Folder;
import net.bodkasoft.wordoccurrencescounter.task.CommonWordsTask;
import net.bodkasoft.wordoccurrencescounter.task.FolderSearchTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class WordOccurrencesCounter {

    private static final ForkJoinPool pool = new ForkJoinPool();
    private static final List<String> commonWords = List.of( "with", "in" );

    public static void main(String[] args) throws IOException {
        Folder folder = Folder.fromDirectory(new File("D:\\Programing\\concurrent-computing\\billiards\\lab_4\\src\\main\\resources\\documents"));
//        System.out.println("Common words amount: " + countOccurrencesInParallel(folder, commonWords));

// #####################################################################
        Set<String> commonWords = getCommonWords(folder);

        System.out.println("Words found: " + commonWords.size());
        System.out.println("Common words: " + commonWords);
    }

    private static Long countOccurrencesInParallel(Folder folder, List<String> searchedWords) {
        return pool.invoke(new FolderSearchTask(folder, searchedWords));
    }

    private static Set<String> getCommonWords(Folder folder) {
        return pool.invoke(new CommonWordsTask(folder));
    }
}
