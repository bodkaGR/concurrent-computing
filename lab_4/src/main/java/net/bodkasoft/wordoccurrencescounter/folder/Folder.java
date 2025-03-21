package net.bodkasoft.wordoccurrencescounter.folder;

import net.bodkasoft.wordoccurrencescounter.document.Document;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Folder {

    private final List<Folder> subFolders;
    private final List<Document> documents;

    public Folder(List<Folder> subFolders, List<Document> documents) {
        this.subFolders = subFolders;
        this.documents = documents;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public static Folder fromDirectory(File directory) throws IOException {
        List<Document> documents = new LinkedList<>();
        List<Folder> subFolders = new LinkedList<>();
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                subFolders.add(Folder.fromDirectory(file));
            }else {
                documents.add(Document.fromFile(file));
            }
        }
        return new Folder(subFolders, documents);
    }
}
