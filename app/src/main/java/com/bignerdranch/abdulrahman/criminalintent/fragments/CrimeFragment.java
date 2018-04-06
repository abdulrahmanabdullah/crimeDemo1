package com.bignerdranch.abdulrahman.criminalintent.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.bignerdranch.abdulrahman.criminalintent.CrimeActivity;
import com.bignerdranch.abdulrahman.criminalintent.R;
import com.bignerdranch.abdulrahman.criminalintent.model.Crime;
import com.bignerdranch.abdulrahman.criminalintent.model.CrimeLab;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private Crime mCrime ;
    // editText
    EditText edTitleField ;
    // button
    Button btnDate;
    //checkBox
    CheckBox chBoxSolved ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();
        // pass the key to get the UUId .
//        UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID crimeId =(UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
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
        btnDate.setText(mCrime.getDate().toString());
        btnDate.setEnabled(false);
        chBoxSolved = view.findViewById(R.id.ch_crime_solved);
        chBoxSolved.setChecked(mCrime.isSolve());
        chBoxSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        return  view;
    }
}
