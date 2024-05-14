import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.example.lab9.task2.WordCounter;
import org.junit.jupiter.api.Test;

public class WordCounterTest {

    @Test
    void testParse() {
        WordCounter wordCounter = new WordCounter();
        String testText =
                "Lorem ipsum dolor sit amet consectetur adipiscing elit. Duis dapibus nisl sit"
                        + " amet lacus gravida, nec ultricies elit venenatis.";

        wordCounter.parse(testText);

        assertEquals(1, wordCounter.getCount("Lorem"));
        assertEquals(2, wordCounter.getCount("amet"));
        assertEquals(0, wordCounter.getCount("hjoerjherojheropjop"));
        wordCounter.reset();
    }

    @Test
    void testFileParsing() throws IOException {
        WordCounter wordCounter = new WordCounter();

        // https://gist.github.com/MattIPv4/045239bc27b16b2bcf7a3a9a4648c08a
        // Am scos semnele de punctuatie din fisier pt acuratete
        String filePath = "src/test/java/utils/Bee_Movie_Script.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder textBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                textBuilder.append(line).append(" ");
            }
            String fileContent = textBuilder.toString();

            wordCounter.parse(fileContent);

            // mai sunt si cuvinte precum Bee, bee-man, etc.
            // dar e case sensitive
            assertEquals(75, wordCounter.getCount("bee"));
            assertEquals(50, wordCounter.getCount("Barry"));
            assertEquals(0, wordCounter.getCount("nuexist"));
        }
        wordCounter.reset();
    }
}
