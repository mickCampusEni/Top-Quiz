package com.example.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    public static final String PREFERENCE_KEY_FIRST_NAME  = "firstName";
    public static final String PREFERENCE_KEY_SCORE = "score";
    public static final int GAME_ACTIVIY_REQUEST_CODE = 1;
    private SharedPreferences mPreferences;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(GAME_ACTIVIY_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_CORE, 0);
            mPreferences.edit()
                        .putInt(PREFERENCE_KEY_SCORE, score)
                        .apply();
            userExist();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity :: onCreate()");

        mUser = new User();

        mPreferences = getPreferences(MODE_PRIVATE);

        mGreetingText = findViewById(R.id.activity_main_greeting_txt);
        mNameInput = findViewById(R.id.activity_main_name_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);

        mPlayButton.setEnabled(false);

        userExist();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {
                String saisie = c.toString().trim();
                mPlayButton.setEnabled(!saisie.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstName = mNameInput.getText().toString();
                mUser.setFirstName(firstName);

                mPreferences.edit()
                            .putString(PREFERENCE_KEY_FIRST_NAME, mUser.getFirstName())
                            .apply();

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVIY_REQUEST_CODE);
            }
        });
    }

    private void userExist() {
        String firstName = getPreferences(MODE_PRIVATE).getString(PREFERENCE_KEY_FIRST_NAME, null);
        int score = getPreferences(MODE_PRIVATE).getInt(PREFERENCE_KEY_SCORE, -1);

        if(firstName != null && score != -1){
            StringBuilder greetingText = new StringBuilder("Welcome back, ").append(firstName).append( " ! \n")
                                            .append("Your last score was ").append(score).append(", will you do better this time ?");
            mGreetingText.setText(greetingText);
            mNameInput.setText(firstName);
            mNameInput.setSelection(firstName.length());
            mPlayButton.setEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("MainActivity :: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("MainActivity :: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("MainActivity :: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("MainActivity :: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("MainActivity :: onDestroy()");
    }
}
