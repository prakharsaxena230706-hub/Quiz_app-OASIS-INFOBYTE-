package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textQuestion, textQuestionCounter, textScorePreview;
    private RadioGroup radioGroupOptions;
    private RadioButton option1, option2, option3, option4;
    private Button btnNext;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int correctCount = 0;
    private int wrongCount = 0;

    private boolean answerLocked = false; // Prevents changing answer after selection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textQuestion = findViewById(R.id.textQuestion);
        textQuestionCounter = findViewById(R.id.textQuestionCounter);
        textScorePreview = findViewById(R.id.textScorePreview);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        btnNext = findViewById(R.id.btnNext);

        questionList = QuestionBank.getQuestions();

        loadQuestion();

        radioGroupOptions.setOnCheckedChangeListener((group, checkedId) -> {
            if (!answerLocked && checkedId != -1) {
                handleAnswerSelected(checkedId);
            }
        });

        btnNext.setOnClickListener(v -> goToNextQuestion());
    }

    private void loadQuestion() {
        // Reset state for the new question
        answerLocked = false;
        btnNext.setEnabled(false);
        radioGroupOptions.clearCheck();
        resetOptionBackgrounds();

        Question currentQuestion = questionList.get(currentQuestionIndex);
        List<String> options = currentQuestion.getOptions();

        textQuestion.setText(currentQuestion.getQuestionText());
        textQuestionCounter.setText(String.format("Question %d of %d",
                currentQuestionIndex + 1, questionList.size()));
        textScorePreview.setText(String.format("Score: %d", score));

        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        option4.setText(options.get(3));

        // Update button text for last question
        if (currentQuestionIndex == questionList.size() - 1) {
            btnNext.setText(R.string.btn_finish);
        } else {
            btnNext.setText(R.string.btn_next);
        }
    }

    private void handleAnswerSelected(int checkedId) {
        answerLocked = true; // Lock further changes once an answer is chosen

        Question currentQuestion = questionList.get(currentQuestionIndex);
        RadioButton selectedButton = findViewById(checkedId);
        String selectedAnswer = selectedButton.getText().toString();

        boolean isCorrect = currentQuestion.isCorrect(selectedAnswer);

        if (isCorrect) {
            score++;
            correctCount++;
            selectedButton.setBackgroundResource(R.drawable.option_background_correct);
            selectedButton.setTextColor(getResources().getColor(R.color.colorTextLight));
        } else {
            wrongCount++;
            selectedButton.setBackgroundResource(R.drawable.option_background_wrong);
            selectedButton.setTextColor(getResources().getColor(R.color.colorTextLight));

            // Also highlight the correct answer in green so the user learns it
            highlightCorrectAnswer(currentQuestion.getCorrectAnswer());
        }

        // Disable all radio buttons so user can't change the answer after feedback
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);

        textScorePreview.setText(String.format("Score: %d", score));
        btnNext.setEnabled(true);
    }

    private void highlightCorrectAnswer(String correctAnswerText) {
        RadioButton[] allOptions = {option1, option2, option3, option4};
        for (RadioButton rb : allOptions) {
            if (rb.getText().toString().equals(correctAnswerText)) {
                rb.setBackgroundResource(R.drawable.option_background_correct);
                rb.setTextColor(getResources().getColor(R.color.colorTextLight));
            }
        }
    }

    private void resetOptionBackgrounds() {
        RadioButton[] allOptions = {option1, option2, option3, option4};
        for (RadioButton rb : allOptions) {
            rb.setBackgroundResource(R.drawable.option_background_default);
            rb.setTextColor(getResources().getColor(R.color.colorTextDark));
            rb.setEnabled(true);
        }
    }

    private void goToNextQuestion() {
        if (currentQuestionIndex < questionList.size() - 1) {
            currentQuestionIndex++;
            loadQuestion();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("SCORE", score);
        intent.putExtra("TOTAL", questionList.size());
        intent.putExtra("CORRECT", correctCount);
        intent.putExtra("WRONG", wrongCount);
        startActivity(intent);
        finish(); // Prevent going back into a finished quiz via back button
    }
}
