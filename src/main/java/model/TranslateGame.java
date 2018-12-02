package model;

import util.Translator;
import util.WordBank;

import java.util.ArrayList;
import java.util.List;

public class TranslateGame {
    Player p1;
    Player p2;
    String lang;
    WordBank wordBank;
    Translator translator;
    List<String> words;

    public TranslateGame(String lang, Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.lang = lang;
        translator = new Translator();
        wordBank = new WordBank();
        words = new ArrayList<>();
    }

    public int getP1Score() {
        return p1.getScore();
    }

    public int getP2Score() {
        return p1.getScore();
    }

    public void handleWin() {
        String word = words.get(words.size()-1);
        if (word.equals(p1.getLastAnswer()) && word.equals(p2.getLastAnswer())){

        }

    }

    public String getNewWord() {
        String englishWord = wordBank.getRandomWord();
        words.add(englishWord);
        return translator.translateTo(englishWord, "eng", lang);
    }


}
