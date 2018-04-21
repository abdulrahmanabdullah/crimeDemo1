package com.bignerdranch.abdulrahman.criminalintent.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bignerdranch.abdulrahman.criminalintent.dataBase.CrimeDbScheme.CrimeTable;
import com.bignerdranch.abdulrahman.criminalintent.model.Crime;

public class CrimeBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2 ;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context paramContext){
        super(paramContext,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+ CrimeTable.NAME + "("
            + "_id integer primary key autoincrement , "+
                CrimeTable.Cols.UUID +" , "
                + CrimeTable.Cols.TITLE + " , "
                + CrimeTable.Cols.DATE + " , "
                + CrimeTable.Cols.SOLVED + " , "
                +CrimeTable.Cols.SUSPECT + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists "+CrimeTable.NAME);
//            db.execSQL("alter table "+CrimeTable.NAME+" add column "+CrimeTable.Cols.SUSPECT+" string ;");
//            onCreate(db);

    }
}
