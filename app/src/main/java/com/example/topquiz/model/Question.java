package com.example.topquiz.model;

import java.util.List;

/**
 * Created by Mickaël L on 24/06/2019.
 */
public class Question {

    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String pQuestion, List<String> pChoiceList, int pAnswerIndex) {
        this.mQuestion = pQuestion;
        this.mChoiceList = pChoiceList;
        this.mAnswerIndex = pAnswerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String pQuestion) {
        mQuestion = pQuestion;
    }

    public List<String> getChoiseList() {
        return mChoiceList;
    }

    public void setChoiseList(List<String> pChoiseList) {

        if(pChoiseList == null){
            throw new IllegalArgumentException("Array cannot be null");
        }
        mChoiceList = pChoiseList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int pAnswerIndex) {

        if(pAnswerIndex < 0 || pAnswerIndex >= mChoiceList.size()){
            throw new IllegalArgumentException("Answer index is out of bound");
        }
        mAnswerIndex = pAnswerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiseList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }
}
