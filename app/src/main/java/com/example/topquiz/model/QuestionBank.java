package com.example.topquiz.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mickaël L on 24/06/2019.
 */
public class QuestionBank {

    private List<Question> mQuestionList;
    private int mQuestionIndex;

    public QuestionBank(List<Question> pQuestionList) {
        this.mQuestionList = pQuestionList;

        initialize();
    }

    private void initialize() {
        Collections.shuffle(mQuestionList);
        mQuestionIndex = 0;
    }

    public Question getquestion(){

        if(mQuestionIndex == mQuestionList.size()){
            initialize();
        }

        return mQuestionList.get(mQuestionIndex++);
    }
}
