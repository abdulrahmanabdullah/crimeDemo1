package com.bignerdranch.abdulrahman.criminalintent.model;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mID ;
    private String mTitle ;
    private Date mDate ;
    private boolean mSolve ;

    public Crime(){
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    //setter ..
    public void setTitle(String paramTitle) {
        mTitle = paramTitle;
    }

    public void setDate(Date paramDate) {
        mDate = paramDate;
    }

    public void setSolve(boolean paramSolve) {
        mSolve = paramSolve;
    }

    // getter
    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isSolve() {
        return mSolve;
    }
}
