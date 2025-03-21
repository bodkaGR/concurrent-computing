package net.bodkasoft.textanalyser.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextReader {

    private final Pattern pattern = Pattern.compile("\\b[a-zA-Z]{2,}\\b");;
    private final String filename;

    public TextReader(String filename) {
        this.filename = filename;
    }

    public void readWordsLengthsInChunks(int chunkSize, Consumer<List<Integer>> wordProcessor) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            List<Integer> wordLengths = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    wordLengths.add(matcher.group().length());

                    if (wordLengths.size() == chunkSize) {
                        wordProcessor.accept(new ArrayList<>(wordLengths));
                        wordLengths.clear();
                    }
                }
            }

            if (!wordLengths.isEmpty()) {
                wordProcessor.accept(wordLengths);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> readWordsLengths() {
        List<Integer> wordLengths = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    wordLengths.add(matcher.group().length());
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return wordLengths;
    }
}
