package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.QuestionBank;
import com.example.topquiz.model.Question;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    private TextView mQuestion;
    private Button mAnswer1;
    private Button mAnswer2;
    private Button mAnswer3;
    private Button mAnswer4;
    private QuestionBank mQuestionBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestion = findViewById(R.id.activity_game_question_text);
        mAnswer1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswer2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswer3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswer4 = findViewById(R.id.activity_game_answer4_btn);

        initializeQuestionBank();
    }

    private QuestionBank initializeQuestionBank(){
        QuestionBank questionBank = null;

        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"),
                0);

        Question question2 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958",
                        "1962",
                        "1967",
                        "1969"),
                3);

        Question question3 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42",
                        "101",
                        "666",
                        "742"),
                3);

        questionBank = new QuestionBank(Arrays.asList(question1,
                                                        question2,
                                                        question3));

        return questionBank;
    }


}
