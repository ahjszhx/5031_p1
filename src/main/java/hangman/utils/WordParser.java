package hangman.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class WordParser {

    public static List<String> loadWords() {
        List<String> words = new ArrayList<>();
        try (InputStream is = WordParser.class.getClassLoader().getResourceAsStream("wordlist.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return words;
    }
}
