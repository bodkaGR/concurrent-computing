package net.bodkasoft.docsfinder;

import net.bodkasoft.docsfinder.task.DocumentFindTask;

import java.nio.file.Path;
import java.util.List;

public class DocsFinder {

    public static void main(final String[] args) {
        List<Path> paths = List.of(
                Path.of("src/main/resources/it-documents/100mb.txt"),
                Path.of("src/main/resources/it-documents/200mb.txt"),
                Path.of("src/main/resources/it-documents/1-2mb.txt"),
                Path.of("src/main/resources/it-documents/74kb.txt")
        );

        List<Path> resultPaths = getPaths(paths);

        System.out.println("<---Paths with IT key words found--->");
        resultPaths.forEach(System.out::println);
    }

    private static List<Path> getPaths(List<Path> paths) {
        DocumentFindTask task = new DocumentFindTask(paths);
        return task.fork().join();
    }
}
