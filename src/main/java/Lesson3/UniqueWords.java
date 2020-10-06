package Lesson3;

import java.util.*;

// Create an array with a set of words (10-20 words, there must be duplicates).
// Find and display a list of unique words that make up the array (duplicates are not counted).
// Count how many times each word occurs.

public class UniqueWords {
    public static void main(String[] args) {

        String[] arrayWords = new String[20];

        Random random = new Random();
        for (int i = 0; i < arrayWords.length; i++) {
            arrayWords[i] = String.valueOf(random.nextInt(arrayWords.length-1));
        }

        HashMap<String, Integer> uniqueWords = new LinkedHashMap<>();
        for (String arrayWord : arrayWords) {
            Integer count = uniqueWords.get(arrayWord);
            uniqueWords.put(arrayWord, count == null ? 1 : count + 1);
        }

        Set<String> uniqueWordsKeys = uniqueWords.keySet();
        Iterator<String> keysIterator = uniqueWordsKeys.iterator();
        Collection<Integer> uniqueWordsCounts = uniqueWords.values();
        Iterator<Integer> countsIterator = uniqueWordsCounts.iterator();

        for (int i = 0; i < uniqueWords.size(); i++) {
            System.out.println("Word " + keysIterator.next() + " was inserted " + countsIterator.next() + " times.");
        }
    }
}
