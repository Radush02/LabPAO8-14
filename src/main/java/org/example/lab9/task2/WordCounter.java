package org.example.lab9.task2;

import java.util.*;
import org.example.lab9.task1.SortedListSet;

public class WordCounter implements IWordCounter {
    HashMap<String, Integer> words = new HashMap<>();

    @Override
    public void parse(String text) {
        String[] wordsList = text.split(" ");
        for (String w : wordsList) {
            String word = w.trim();
            if (words.containsKey(word)) {
                words.put(word, words.get(word) + 1);
            } else {
                words.put(word, 1);
            }
        }
    }

    @Override
    public int getCount(String word) {
        Integer count = words.get(word);
        return (count == null) ? 0 : count;
    }

    @Override
    public SortedSet<String> getUniqueWords() {
        SortedSet<String> unique = new SortedListSet<String>();
        unique.addAll(words.keySet());
        return unique;
    }

    @Override
    public void printWordCounts() {
        List<String> sortedWords = new ArrayList<String>(words.keySet());
        sortedWords.sort((a, b) -> words.get(b) - words.get(a));
        for (String word : sortedWords) {
            System.out.println(word + " " + words.get(word));
        }
    }

    @Override
    public void reset() {
        words.clear();
    }
}
