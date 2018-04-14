package com.bignerdranch.abdulrahman.criminalintent.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.abdulrahman.criminalintent.CrimeActivity;
import com.bignerdranch.abdulrahman.criminalintent.R;
import com.bignerdranch.abdulrahman.criminalintent.model.Crime;
import com.bignerdranch.abdulrahman.criminalintent.model.CrimeLab;
import com.bignerdranch.abdulrahman.criminalintent.viewPager.CrimeViewPagerActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mRecyclerView ;

    private CrimeAdapter mAdapter ;
    public int currentItem ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // to tell the fragmentManager we receive menu bar ..
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
//        updateUI();
        return view ;
    }

    // Adapter class
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimeList ;

        public CrimeAdapter(List<Crime> paramList){
            mCrimeList = paramList;
        }
        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return  new CrimeHolder(inflater,parent);
        }
        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimeList.get(position);
            holder.bind(crime,position);
//            this.notifyItemChanged(position);
        }



        @Override
        public int getItemCount() {
            return mCrimeList.size();
        }
    }
    // ViewHolder class
    private static final int REQUEST_CODE =1 ;
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Crime mCrime ;
        TextView txvCrimeTitle ;
        TextView txvCrimeDate ;
        ImageView imgCrimeSolve ;
        public CrimeHolder(LayoutInflater inflater , ViewGroup paramViewGroup){
            super(inflater.inflate(R.layout.list_item_crime,paramViewGroup,false));
            txvCrimeTitle = itemView.findViewById(R.id.txv_crime_title);
            txvCrimeDate = itemView.findViewById(R.id.txv_crime_date);
            imgCrimeSolve = itemView.findViewById(R.id.img_crime_solve);
            itemView.setOnClickListener(this);
        }

        public void bind(Crime paramCrime,int parmCurrentItem){
            mCrime = paramCrime ;
            txvCrimeTitle.setText(mCrime.getTitle());
            txvCrimeDate.setText(getHumanDate(mCrime.getDate()));
            imgCrimeSolve.setVisibility(paramCrime.isSolve() ? View.VISIBLE : View.GONE);
             currentItem = parmCurrentItem ;
//             Toast.makeText(getActivity()," onBind func id = "+currentItem,Toast.LENGTH_SHORT).show();

        }

        private String getHumanDate(Date paramDate){
            String pattern = "EEE, MMM d , yyy ";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(paramDate) ;
        }
        @Override
        public void onClick(View v) {
//            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getID());
//            startActivityForResult(intent, REQUEST_CODE);
            // open viewPager activity ..
            Intent intent = CrimeViewPagerActivity.newIntent(getActivity(),mCrime.getID());
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE ){

        }
    }

    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimeList = crimeLab.getCrimeList();
        if (mAdapter == null ){
            mAdapter = new CrimeAdapter(crimeList);
            mRecyclerView.setAdapter(mAdapter);
        }else{
//            Toast.makeText(getActivity()," Current position = "+currentItem,Toast.LENGTH_SHORT).show();
            mAdapter.notifyItemChanged(currentItem);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    // callback menu bar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent intent = CrimeViewPagerActivity.newIntent(getActivity(),crime.getID());
                startActivity(intent);
                return  true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
