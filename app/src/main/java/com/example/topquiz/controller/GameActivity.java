package com.example.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
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

    public static final String BUNDLE_EXTRA_CORE = "BUNDLE_EXTRA_CORE";

    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity :: onCreate()");

        mQuestionBank = initializeQuestionBank();
        mScore = 0;
        mNumberOfQuestions = 4;
        mEnableTouchEvents = true;

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

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                if(--mNumberOfQuestions == 0){
                    endGame();
                }
                else{
                    mCurrentQuestion = mQuestionBank.getquestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000); // Delay for wait the display of toast
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String title = (mScore >= 2 ? "Well done !" : "To improve !");
        StringBuilder message = new StringBuilder("Your score is : ")
                                    .append(mScore)
                                    .append(" / ")
                                    .append(4);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int witch){
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_CORE, mScore);
                        setResult(RESULT_OK, intent);
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

        Question question1 = new Question("What is the name of the current french president?",
                Arrays.asList("François Hollande", "Emmanuel Macron", "Jacques Chirac", "François Mitterand"),
                1);

        Question question2 = new Question("How many countries are there in the European Union?",
                Arrays.asList("15", "24", "28", "32"),
                2);

        Question question3 = new Question("Who is the creator of the Android operating system?",
                Arrays.asList("Andy Rubin", "Steve Wozniak", "Jake Wharton", "Paul Smith"),
                0);

        Question question4 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958", "1962", "1967", "1969"),
                3);

        Question question5 = new Question("What is the capital of Romania?",
                Arrays.asList("Bucarest", "Warsaw", "Budapest", "Berlin"),
                0);

        Question question6 = new Question("Who did the Mona Lisa paint?",
                Arrays.asList("Michelangelo", "Leonardo Da Vinci", "Raphael", "Carravagio"),
                1);

        Question question7 = new Question("In which city is the composer Frédéric Chopin buried?",
                Arrays.asList("Strasbourg", "Warsaw", "Paris", "Moscow"),
                2);

        Question question8 = new Question("What is the country top-level domain of Belgium?",
                Arrays.asList(".bg", ".bm", ".bl", ".be"),
                3);

        Question question9 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42", "101", "666", "742"),
                3);

        questionBank = new QuestionBank(Arrays.asList(question1,
                                                        question2,
                                                        question3,
                                                        question4,
                                                        question5,
                                                        question6,
                                                        question7,
                                                        question8,
                                                        question9));

        return questionBank;
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("GameActivity :: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("GameActivity :: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("GameActivity :: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("GameActivity :: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("GameActivity :: onDestroy()");
    }

}
