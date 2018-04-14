package com.bignerdranch.abdulrahman.criminalintent.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bignerdranch.abdulrahman.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {
    private static final String ARGUMENT_TIME = "time";
    public static final String EXTRA_TIME = "TIME";


    private TimePicker mTimePicker ;
private String currentTime ;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View timeDialog = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time_picker,null);
        mTimePicker = timeDialog.findViewById(R.id.dialog_time_picker);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.time_dialog_title)
                .setView(timeDialog)
                .setPositiveButton(" ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int hour = mTimePicker.getCurrentHour();
                        int minute = mTimePicker.getCurrentMinute();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1){
                            currentTime = toTime(mTimePicker.getHour(),mTimePicker.getMinute());
                        }else{
                            currentTime = toTime(hour,minute);
                        }
//                        currentTime = toTime(hour,minute);
                        sendResult(Activity.RESULT_OK,currentTime);
                    }
                })
                .create();
    }
    // this func call from CrimeFragment when user click time button
    public static TimePickerFragment newInstance(Date paramDate){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_TIME,paramDate);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static TimePickerFragment newInstanceTest(String paramS){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_TIME,paramS);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    // this func for send result code , date ..
    private void sendResult(int resultCode , String string){
        if (getTargetFragment() == null )return;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME,string);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

    private String toTime(int paramHour,int paramMinute){
            return String.format("%02d:%02d %s",paramHour,paramMinute,paramHour>12?"PM":"AM");

    }
}
