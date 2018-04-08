package com.bignerdranch.abdulrahman.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// singleton pattern
public class CrimeLab {
    private static CrimeLab INSTANCE ;
    public List<Crime> mCrimeList ;
    public static CrimeLab get(Context paramContext){
        if (INSTANCE == null){
           INSTANCE = new CrimeLab(paramContext);
        }

        return INSTANCE ;
    }

    // private Constructor ..
    private CrimeLab(Context paramContext){
        mCrimeList = new ArrayList<>();
        for (int i = 0 ; i < 100 ; i++){
            Crime crime = new Crime();
            crime.setTitle(" Crime # "+ i );
            crime.setSolve(i % 2 == 0); // every other one .
            mCrimeList.add(crime);
        }
    }

    public List<Crime> getCrimeList(){ return  mCrimeList;}
    public Crime getCrime(UUID id ){
        for ( Crime crime : mCrimeList){
            if (crime.getID().equals(id)){
                return crime ;
            }
        }
        return null ;
    }
}
