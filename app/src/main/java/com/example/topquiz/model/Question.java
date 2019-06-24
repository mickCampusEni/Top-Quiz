package com.example.topquiz.model;

import java.util.List;

/**
 * Created by Mickaël L on 24/06/2019.
 */
public class Question {

    private String mQuestion;
    private List<String> mChoiseList;
    private int mAnswerIndex;

    public Question() {
    }

    public Question(String pQuestion, List<String> pChoiseList, int pAnswerIndex) {
        this.mQuestion = pQuestion;
        this.mChoiseList = pChoiseList;
        this.mAnswerIndex = pAnswerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String pQuestion) {
        mQuestion = pQuestion;
    }

    public List<String> getChoiseList() {
        return mChoiseList;
    }

    public void setChoiseList(List<String> pChoiseList) {

        if(pChoiseList == null){
            throw new IllegalArgumentException("Array cannot be null");
        }
        mChoiseList = pChoiseList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int pAnswerIndex) {

        if(pAnswerIndex < 0 || pAnswerIndex >= mChoiseList.size()){
            throw new IllegalArgumentException("Answer index is out of bound");
        }
        mAnswerIndex = pAnswerIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiseList=" + mChoiseList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }
}
