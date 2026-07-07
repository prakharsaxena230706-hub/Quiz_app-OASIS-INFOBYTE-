package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textFinalScore = findViewById(R.id.textFinalScore);
        TextView textCorrectCount = findViewById(R.id.textCorrectCount);
        TextView textWrongCount = findViewById(R.id.textWrongCount);
        Button btnRestart = findViewById(R.id.btnRestart);

        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        int correct = getIntent().getIntExtra("CORRECT", 0);
        int wrong = getIntent().getIntExtra("WRONG", 0);

        textFinalScore.setText(String.format("Score: %d / %d", score, total));
        textCorrectCount.setText(String.format("Correct: %d", correct));
        textWrongCount.setText(String.format("Incorrect: %d", wrong));

        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}