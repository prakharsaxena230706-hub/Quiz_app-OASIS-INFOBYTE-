package com.example.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;

    public Question(String questionText, String correctAnswer, String... wrongAnswers) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        List<String> allOptions = new ArrayList<>();
        allOptions.add(correctAnswer);
        Collections.addAll(allOptions, wrongAnswers);
        Collections.shuffle(allOptions);
        this.options = allOptions;
    }

    public String getQuestionText() { return questionText; }
    public List<String> getOptions() { return options; }
    public String getCorrectAnswer() { return correctAnswer; }
    public boolean isCorrect(String selectedAnswer) { return correctAnswer.equals(selectedAnswer); }
}