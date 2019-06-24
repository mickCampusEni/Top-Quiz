package com.example.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.model.QuestionBank;
import com.example.topquiz.model.Question;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTextView;
    private Button mAnswerButton1;
    private Button mAnswerButton2;
    private Button mAnswerButton3;
    private Button mAnswerButton4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = initializeQuestionBank();
        mScore = 0;
        mNumberOfQuestions = 4;

        mQuestionTextView = findViewById(R.id.activity_game_question_text);
        mAnswerButton1 = findViewById(R.id.activity_game_answer1_btn);
        mAnswerButton2 = findViewById(R.id.activity_game_answer2_btn);
        mAnswerButton3 = findViewById(R.id.activity_game_answer3_btn);
        mAnswerButton4 = findViewById(R.id.activity_game_answer4_btn);

        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getquestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    public void onClick(View pView) {

        int responseIndex = (int) pView.getTag();

        if(responseIndex == mCurrentQuestion.getAnswerIndex()){
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else{
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }

        if(--mNumberOfQuestions == 0){
            endGame();
        }
        else{
            mCurrentQuestion = mQuestionBank.getquestion();
            displayQuestion(mCurrentQuestion);
        }

    }


    private void endGame(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done !")
                .setMessage("Your score is : " + mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int witch){
                        finish();
                    }
                })
                .create()
                .show();

    }

    private void displayQuestion(final Question pQuestion){

        List<String> choiceList = pQuestion.getChoiseList();

        mQuestionTextView.setText(pQuestion.getQuestion());
        mAnswerButton1.setText(choiceList.get(0));
        mAnswerButton2.setText(choiceList.get(1));
        mAnswerButton3.setText(choiceList.get(2));
        mAnswerButton4.setText(choiceList.get(3));
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
