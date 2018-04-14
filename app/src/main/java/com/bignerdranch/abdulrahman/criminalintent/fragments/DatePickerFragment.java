package com.bignerdranch.abdulrahman.criminalintent.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.bignerdranch.abdulrahman.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    private static final String ARGUMENT_DATE="date";
    public static final String EXTRA_DATE = "DATE";

    private DatePicker mDatePicker ;
    private Calendar mCalendar ;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // initialize DatePicker
        Date date = (Date)getArguments().getSerializable(ARGUMENT_DATE);
        initialCalendar(date);
        // create date dialog... view
        View dateDialog = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date_picker,null);
        mDatePicker = dateDialog.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(getYear(),getMonth(),getDay(),null);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)//set Title
                .setView(dateDialog) // set dialog view ..
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Date dateDialog = getDateFromDialog();
                        sendResult(Activity.RESULT_OK, dateDialog);
                    }
                }).create();
    }

    private void initialCalendar(Date paramDate){
        mCalendar = Calendar.getInstance();
        mCalendar.setTime(paramDate);
    }

    private int getYear(){
        return mCalendar.get(Calendar.YEAR);
    }
    private int getMonth(){
        return mCalendar.get(Calendar.MONTH);
    }
    private int getDay(){
        return mCalendar.get(Calendar.DAY_OF_MONTH);
    }
    // save Date when user selected
    private Date getDateFromDialog(){
       int year = mDatePicker.getYear();
       int month = mDatePicker.getMonth();
       int day = mDatePicker.getDayOfMonth();
       return new GregorianCalendar(year,month,day).getTime();
    }
    // to save date when user select the date ..
    public static DatePickerFragment newInstance(Date paramDate){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_DATE,paramDate);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void sendResult(int resultCode , Date date){
        if (getTargetFragment() == null ) return;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
