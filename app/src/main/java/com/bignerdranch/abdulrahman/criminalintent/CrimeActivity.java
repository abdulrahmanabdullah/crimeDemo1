package com.bignerdranch.abdulrahman.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bignerdranch.abdulrahman.criminalintent.fragments.CrimeFragment;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    private static final String EXTRA_CRIME_ID = "crime_id";

    public static Intent newIntent(Context paramContext, UUID paramUUID){
        Intent intent = new Intent(paramContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,paramUUID);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
//        return new CrimeFragment();
        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return  CrimeFragment.newInstance(crimeId);
    }

//    FragmentManager mFragmentManager ;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//        mFragmentManager = getSupportFragmentManager();
//        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
//        if (fragment == null ){
//            fragment = new CrimeFragment();
//            mFragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
//        }
//    }
}
