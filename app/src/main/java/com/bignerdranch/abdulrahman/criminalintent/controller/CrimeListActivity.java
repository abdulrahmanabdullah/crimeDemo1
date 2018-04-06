package com.bignerdranch.abdulrahman.criminalintent.controller;

import android.support.v4.app.Fragment;
import com.bignerdranch.abdulrahman.criminalintent.SingleFragmentActivity;
import com.bignerdranch.abdulrahman.criminalintent.fragments.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
