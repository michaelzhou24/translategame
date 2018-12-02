package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class WordBank {

    private final int WORD_COUNT = 101;

    private String[] wordList;

    public WordBank() {
        wordList = new String[WORD_COUNT];
        File file = new File("/Users/michaelzhou/IdeaProjects/translategame/src/main/resources/wordlist.txt");
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int count = 0;
        while (input.hasNext()) {
            String word  = input.next();
            wordList[count] = word;
            count++;
        }
    }

    public String getRandomWord() {
        Random ran = new Random();
        int index = ran.nextInt(WORD_COUNT);
        return wordList[index];
    }

    /*private void printWords() {
        for (String s : wordList) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        WordBank wordBank = new WordBank();
        wordBank.printWords();
    }
*/
}
