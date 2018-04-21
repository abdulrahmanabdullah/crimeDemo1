package com.bignerdranch.abdulrahman.criminalintent.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.abdulrahman.criminalintent.R;
import com.bignerdranch.abdulrahman.criminalintent.dataBase.CrimeDbScheme;
import com.bignerdranch.abdulrahman.criminalintent.model.Crime;
import com.bignerdranch.abdulrahman.criminalintent.model.CrimeLab;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private  final String DIALOG_DATE = "dialogDate";
    private  final String DIALOG_TIME = "dialogTime";
    private Crime mCrime ;
    private final int REQUEST_CODE = 0 ;
    private final int REQUEST_CODE_TIME=1;
    // editText
    EditText edTitleField ;
    // button
    Button btnDate;
    Button btnTime;
    Button btnDeleteCrime ;
    //checkBox
    CheckBox chBoxSolved ;
    List<Crime> mList ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();
        // pass the key to get the UUId .
//        UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID crimeId =(UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        mList = CrimeLab.get(getActivity()).getCrimeList();
    }

    public static CrimeFragment newInstance(UUID paramUUID){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRIME_ID,paramUUID);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void retuernResult(){
        getActivity().setResult(Activity.RESULT_OK,null);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime,container,false);
        edTitleField = view.findViewById(R.id.ed_crime_title);
        edTitleField.setText(mCrime.getTitle());
        edTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnDate = view.findViewById(R.id.btn_crime_date);
        btnDeleteCrime = view.findViewById(R.id.btn_crime_delete);
//        btnDate.setText(mCrime.getDate().toString());
        updateDate();
//        btnDate.setEnabled(false);
        //Two open dialog datePicker ..
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this,REQUEST_CODE);
                dialog.show(fm,DIALOG_DATE);
            }
        });
        chBoxSolved = view.findViewById(R.id.ch_crime_solved);
        chBoxSolved.setChecked(mCrime.isSolve());
        chBoxSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        btnTime = view.findViewById(R.id.btn_crime_time);
        btnTime.setText(R.string.crime_time);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity()," clicked",Toast.LENGTH_SHORT).show();
                FragmentManager fm = getFragmentManager();
                TimePickerFragment timeDialog = TimePickerFragment.newInstanceTest("TEST");
                timeDialog.setTargetFragment(CrimeFragment.this,REQUEST_CODE_TIME);
                timeDialog.show(fm,DIALOG_TIME);
            }
        });
        btnDeleteCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<Crime> crimeList = CrimeLab.get(getActivity()).getCrimeList();
//                crimeList.remove(mCrime);
//                mList.remove(mCrime);
                CrimeLab.get(getActivity()).deleteCrime(mCrime);
//                Toast.makeText(getActivity()," title of crime = "+mList.size(),Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        return  view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == REQUEST_CODE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
        else if (requestCode == REQUEST_CODE_TIME){
            String str =(String) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            btnTime.setText(str);
        }
    }


    private String getHumanDate(Date paramDate,String datePattern){
        if (paramDate == null || datePattern == null ) return "Null" ;
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        return format.format(paramDate) ;
    }
    private String datePattern = "EEE, MMM d , yyy ";
    private void updateDate(){
        String date = getHumanDate(mCrime.getDate(),datePattern);
        btnDate.setText(date);
    }
    private String timePattern = "hh:mm:ss a zzz";
    private void updateTime(){
       String time = getHumanDate(mCrime.getDate(),timePattern);
       btnTime.setText(time);
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.get(getActivity()).updateCrime(mCrime);
    }


    private String getCrimeRepot(){
        String solvedString = null ;
        if (mCrime.isSolve())solvedString = getString(R.string.crime_report_solved);
        else solvedString = getString(R.string.crime_report_unsolved);

        String dateFormat = "EE, MMM , dd";
        String dateString = DateFormat.format(dateFormat,mCrime.getDate()).toString();
        String suspect = mCrime.getSuspect();
        if (suspect == null ) suspect = getString(R.string.crime_report_no_suspect);
        else suspect = getString(R.string.crime_report_suspect,suspect);
        String report = getString(R.string.crime_report,mCrime.getTitle(),dateString,solvedString,suspect) ;
        return report;
    }

}
