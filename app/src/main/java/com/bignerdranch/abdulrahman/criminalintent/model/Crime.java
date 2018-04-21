package com.bignerdranch.abdulrahman.criminalintent.model;

import java.util.Date;
import java.util.UUID;

public class Crime {

    private UUID mID ;
    private String mTitle ;
    private Date mDate ;
    private boolean mSolve ;
    private String suspect ;


    public Crime(){
        this(UUID.randomUUID());
    }

    public Crime(UUID paramUUID){
        mID = paramUUID;
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

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String paramSuspect) {
        suspect = paramSuspect;
    }
}
