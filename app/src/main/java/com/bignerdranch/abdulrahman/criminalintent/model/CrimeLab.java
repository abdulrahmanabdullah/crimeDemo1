package com.bignerdranch.abdulrahman.criminalintent.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.bignerdranch.abdulrahman.criminalintent.dataBase.CrimeBaseHelper;
import com.bignerdranch.abdulrahman.criminalintent.dataBase.CrimeCursorWrapper;
import com.bignerdranch.abdulrahman.criminalintent.dataBase.CrimeDbScheme.CrimeTable;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// singleton pattern , and lab for insert and update in database ..
public class CrimeLab {
    private static CrimeLab INSTANCE ;
//    public List<Crime> mCrimeList ;
    private Context mContext ;
    private SQLiteDatabase mSQLiteDatabase ;
    public static CrimeLab get(Context paramContext){
        if (INSTANCE == null){
           INSTANCE = new CrimeLab(paramContext);
        }
        return INSTANCE ;
    }

    // private Constructor ..
    private CrimeLab(Context paramContext){
//        mCrimeList = new ArrayList<>();
        mContext = paramContext.getApplicationContext();
        mSQLiteDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
//        for (int i = 1 ; i < 100 ; i++){
//            Crime crime = new Crime();
//            crime.setTitle(" Crime # "+ i );
//            crime.setSolve(i % 2 == 0); // every other one .
//            mCrimeList.add(crime);
//        }
    }

    public List<Crime> getCrimeList(){
        List<Crime> crimeList = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null,null);
        try{
           cursor.moveToFirst();
           while (!cursor.isAfterLast()){
               crimeList.add(cursor.getCrime());
               cursor.moveToNext();
           }
        }finally {
            cursor.close();
        }
        return crimeList;
    }
    public Crime getCrime(UUID id ){
        CrimeCursorWrapper cursor = queryCrimes(CrimeTable.Cols.UUID +" = ? ",new String[]{id.toString()} );
        try{
            if (cursor.getCount() == 0 ) return null ;
            cursor.moveToNext();
            return cursor.getCrime();
        }finally {
            cursor.close();
        }
    }
        // add new crime ..
    public void addCrime(Crime paramCrime){
//        mCrimeList.add(paramCrime);
        ContentValues values = getContentValues(paramCrime);
        mSQLiteDatabase.insert(CrimeTable.NAME,null ,values);
    }
    // update crime
    public void updateCrime(Crime crime ){
        String crimeUUID = crime.getID().toString();
        ContentValues values = getContentValues(crime);
        mSQLiteDatabase.update(CrimeTable.NAME,values,CrimeTable.Cols.UUID+" = ? ",new String[]{crimeUUID});
    }
    // ContentValues func ..
    private static ContentValues getContentValues(Crime paramCrime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID,paramCrime.getID().toString());
        values.put(CrimeTable.Cols.TITLE,paramCrime.getTitle());
        values.put(CrimeTable.Cols.DATE,paramCrime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED,paramCrime.isSolve()?1:0);
        values.put(CrimeTable.Cols.SUSPECT,paramCrime.getSuspect());
        return values ;
    }
    // read data , cursor
    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArg){
        Cursor cursor = mSQLiteDatabase.query(CrimeTable.NAME,null,whereClause,whereArg,null,null,null);
        return new CrimeCursorWrapper(cursor);
    }

    // delete crime ....
    public void deleteCrime(Crime paramCrime){
        String whereClause = CrimeTable.Cols.UUID + " = ?";
       int deleteRow =  mSQLiteDatabase.delete(CrimeTable.NAME,whereClause,new String[]{paramCrime.getID().toString()});
//       if (deleteRow >0) Toast.makeText()
        Log.v("main","success deleted "+deleteRow);
    }

    // photo should live here ..
    public File getPhotoFile(Crime paramCrime){
        File fileDir = mContext.getFilesDir(); // this returns a private application files and directors
        return new File(fileDir,paramCrime.getPhotoFileName());
    }
}
