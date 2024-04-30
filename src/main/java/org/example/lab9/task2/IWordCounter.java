package org.example.lab9.task2;

import java.util.SortedSet;

public interface IWordCounter {
    /**
     * Parses a text, creating tokens for each word.
     * Each word is then added and its internal counter gets incremented
     * @param text - text to be parsed
     */
    void parse(String text);

    /**
     * Gets the current count for a given word
     * @param word - word to be counted
     * @return - number of apparitions
     */
    int getCount(String word);

    /**
     * Gets a collection containing all the unique words
     */
    SortedSet<String> getUniqueWords();

    /**
     * Prints all of the words and their actual count in descendant
     * order of their individual counts (aka most typed word is first)
     */
    void printWordCounts();

    /**
     * Resets the word counter to 0 and deletes all the stored words.
     */
    void reset();
}
