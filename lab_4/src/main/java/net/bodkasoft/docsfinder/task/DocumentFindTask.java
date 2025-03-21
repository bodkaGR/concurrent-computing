package net.bodkasoft.docsfinder.task;

import net.bodkasoft.docsfinder.enums.ITKeywords;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentFindTask extends RecursiveTask<List<Path>> {

    private static final int THRESHOLD = 2;
    private final List<Path> paths;

    public DocumentFindTask(List<Path> paths) {
        this.paths = paths;
    }

    @Override
    protected List<Path> compute() {
        if (paths.size() < THRESHOLD) {
            return filter(paths);
        }else {
            int mid = paths.size() / 2;

            DocumentFindTask left = new DocumentFindTask(paths.subList(0, mid));
            DocumentFindTask right = new DocumentFindTask(paths.subList(mid, paths.size()));

            left.fork();
            List<Path> rightPaths = right.compute();
            List<Path> leftPaths = left.join();

            leftPaths.addAll(rightPaths);
            return leftPaths;
        }
    }

    private List<Path> filter(List<Path> paths) {
        List<Path> resultPaths = new ArrayList<>();
        for (Path path : paths) {
            try {
                boolean containsKeyWord = Files.lines(path)
                        .flatMap(line -> Arrays.stream(line.split("\\W+")))
                        .map(String::toLowerCase)
                        .anyMatch(ITKeywords::contains);
                if (containsKeyWord) {
                    resultPaths.add(path);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultPaths;
    }
}
