package com.bignerdranch.abdulrahman.criminalintent.dataBase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.abdulrahman.criminalintent.model.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime(){
       String uuidString = getString(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.SUSPECT));
        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolve(isSolved != 0);
        crime.setSuspect(suspect);
        return crime ;
    }
}
