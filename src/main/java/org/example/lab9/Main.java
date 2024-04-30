package org.example.lab9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.example.lab9.task2.WordCounter;

public class Main {
    public static void main(String[] args) throws IOException {
        WordCounter wordCounter = new WordCounter();
        String filePath = "src/test/java/utils/Bee_Movie_Script.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder textBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                textBuilder.append(line).append(" ");
            }
            String fileContent = textBuilder.toString();
            System.out.println(fileContent);
            wordCounter.parse(fileContent);
        }
        wordCounter.reset();
    }
}
