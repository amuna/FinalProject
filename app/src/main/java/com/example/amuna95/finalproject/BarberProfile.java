package com.example.amuna95.finalproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class BarberProfile extends AppCompatActivity {

    private String barberEmail;
    private BarberDBHelper helper = new BarberDBHelper(this);
    private Barber barber;

    private String getEmail() {
        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        return email;
    }

    private String[] getData(String email) {
        //Barber barber = helper.getBarber(email);
        String[] data = {barber.getEmail(), barber.getName(), barber.getAddress(),
                barber.getCity(), barber.getStoreName(), barber.getDescription(), barber.getPostalCode(),
                barber.getPhone()};
        //ArrayList<String> data = new ArrayList<String>(Arrays.asList();
        return data;
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        barber = helper.getBarber(getEmail());
        barberEmail = getEmail();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barber_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        private String[] tabTitles = new String[]{"About", "Review"};

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    BarberAbout tab1 = new BarberAbout();
                    Bundle args = new Bundle();
                    args.putStringArray("DATA", getData(getEmail()));
                    tab1.setArguments(args);
                    return tab1;
                case 1:
                    BarberReview tab2 = new BarberReview();
                    Bundle arg = new Bundle();
                    arg.putString("EMAIL", getEmail());
                    arg.putFloat("RATING", barber.getRating());
                    arg.putFloat("RATINGNUM", barber.getNumRating());
                    arg.putString("REVIEW", barber.getReviews());
                    tab2.setArguments(arg);
                    return tab2;
            }
            return null;
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position) {
           return tabTitles[position];
           /* switch (position) {
                case 0:
                    return "ABOUT";
                case 1:
                    return "GALLERY";
                case 2:
                    return "REVIEW";
            }*/
           // return null;
        }
    }
}

