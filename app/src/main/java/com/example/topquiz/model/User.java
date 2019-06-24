package com.example.topquiz.model;

/**
 * Created by Mickaël L on 24/06/2019.
 */
public class User {

    private String mFirstName;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String pFirstName) {
        mFirstName = pFirstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName='" + mFirstName + '\'' +
                '}';
    }
}
