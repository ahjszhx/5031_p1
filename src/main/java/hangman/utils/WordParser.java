package hangman.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The WordParser class is designed to load a list of words from an external resource file.
 * This utility class provides a method to read words from a file and compile them into a list,
 * which can be used for various purposes such as word games or vocabulary applications.
 */
public class WordParser {

    /**
     * Loads words from a text file located in the resources directory of the project.
     * This static method reads each line from the file, assuming each line contains a single word,
     * and adds it to a List after trimming any leading or trailing whitespace.
     *
     * @return A List<String> containing all the words read from the file. If an error occurs during reading,
     * the method prints the stack trace and exits the program to prevent further execution with incomplete data.
     */
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
