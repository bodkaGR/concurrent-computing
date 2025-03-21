package net.bodkasoft.wordoccurrencescounter.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {

    private static final Pattern pattern = Pattern.compile("\\b[a-zA-Z]{2,}\\b");
    private final List<String> words;

    public Document(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    public static Document fromFile(File file) throws IOException {
        List<String> words = new LinkedList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    words.add(matcher.group().toLowerCase());
                }
            }
        }
        return new Document(words);
    }
}
