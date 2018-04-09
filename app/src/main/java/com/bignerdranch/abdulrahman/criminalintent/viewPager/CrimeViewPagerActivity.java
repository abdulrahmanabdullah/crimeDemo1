package com.bignerdranch.abdulrahman.criminalintent.viewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import com.bignerdranch.abdulrahman.criminalintent.R;
import com.bignerdranch.abdulrahman.criminalintent.fragments.CrimeFragment;
import com.bignerdranch.abdulrahman.criminalintent.model.Crime;
import com.bignerdranch.abdulrahman.criminalintent.model.CrimeLab;

import java.util.List;
import java.util.UUID;

/**
 * Created by abdulrahman on 4/6/18.
 */

public class CrimeViewPagerActivity extends AppCompatActivity {
   public static final String EXTRA_CRIME_ID = "crime_id";
    ViewPager mViewPager ;
    List<Crime>  mCrimeList ;
    private int currentCrime =0  ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        // initial crime list
        mCrimeList = CrimeLab.get(this).getCrimeList();
        mViewPager = findViewById(R.id.crime_view_pager);
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimeList.get(position);
                currentCrime = position;
                return CrimeFragment.newInstance(crime.getID());
            }

            @Override
            public int getCount() {
                return mCrimeList.size();
            }
        });
        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);
            for(int i =0 ; i < mCrimeList.size() ; i++ ){
                if (mCrimeList.get(i).getID().equals(crimeId)){
                    mViewPager.setCurrentItem(i);
                    break;
                }
            }
    }

    public void backToFirstCrime(View paramView){
        if (currentCrime  == 1 )Toast.makeText(this," You are in first Crime",Toast.LENGTH_SHORT).show();
        else mViewPager.setCurrentItem(0);
    }
    public void jumpToLastCrime(View paramView){
        int lastCrime = mCrimeList.size() -1 ;
        if (currentCrime == lastCrime-1)Toast.makeText(this," You are in Last Crime  ",Toast.LENGTH_SHORT).show();
        else mViewPager.setCurrentItem(lastCrime);

    }
    public static Intent newIntent(Context context , UUID uuid){
       Intent intent = new Intent(context,CrimeViewPagerActivity.class) ;
        intent.putExtra(EXTRA_CRIME_ID,uuid);
       return intent ;
    }
}
