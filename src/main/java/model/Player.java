package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int score;
    private List<Double> timesTaken;
    private List<String> answers;
    private boolean answerConfirmed;

    public Player() {
        score = 0;
        timesTaken = new ArrayList<>();
        answers = new ArrayList<>();
        answerConfirmed = false;
    }

    public boolean isAnswerConfirmed() {
        return answerConfirmed;
    }

    public void setAnswerConfirmed() {
        answerConfirmed = true;
    }

    public void resetAnswerConfirmed() {
        answerConfirmed = false;
    }

    public void increaseScore() {
        score++;
    }

    public void addTimeTaken(Double t) {
        timesTaken.add(t);
    }

    public void addAnswer(String s) {
        answers.add(s);
    }

    public int getScore() {
        return score;
    }

    public String getLastAnswer() {
        return answers.get(answers.size()-1);
    }

    public Double getLastTime() {
        return timesTaken.get(timesTaken.size()-1);
    }


}
