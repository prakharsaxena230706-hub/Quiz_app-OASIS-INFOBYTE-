package com.example.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
                "What is the capital of France?",
                "Paris", "London", "Berlin", "Madrid"));

        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                "Mars", "Venus", "Jupiter", "Saturn"));

        questions.add(new Question(
                "Who wrote 'Romeo and Juliet'?",
                "William Shakespeare", "Charles Dickens", "Mark Twain", "Leo Tolstoy"));

        questions.add(new Question(
                "What is the largest ocean on Earth?",
                "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"));

        questions.add(new Question(
                "What is the chemical symbol for Gold?",
                "Au", "Ag", "Fe", "Gd"));

        questions.add(new Question(
                "In which year did World War II end?",
                "1945", "1939", "1942", "1950"));

        questions.add(new Question(
                "What is the smallest prime number?",
                "2", "1", "3", "0"));

        questions.add(new Question(
                "Which country is home to the kangaroo?",
                "Australia", "South Africa", "Brazil", "India"));

        questions.add(new Question(
                "What is the powerhouse of the cell?",
                "Mitochondria", "Nucleus", "Ribosome", "Golgi Apparatus"));

        questions.add(new Question(
                "Who painted the Mona Lisa?",
                "Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Claude Monet"));

        // Shuffle the order of questions themselves too, each time the quiz starts
        Collections.shuffle(questions);

        return questions;
    }
}
