package com.example.amuna95.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.amuna95.finalproject.R.id.parent;
import static com.example.amuna95.finalproject.R.styleable.Toolbar;
import static com.example.amuna95.finalproject.R.styleable.View;

/*
Created by:
    Ahmed Naeem (100602243)
    Paljor ()
    Ethan ()
    Pranav ()
 */

public class MainActivity extends AppCompatActivity {
    ArrayList<Barber> barberList = new ArrayList<Barber>();
    BarberArrayAdapter adapter;
    ListView listView;
    // paljor
    private MenuInflater inflater;
    private int dynMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //**********Paljor***********
        //Your code here

        Toolbar myActionbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(myActionbar);
        inflater = getMenuInflater();
        dynMenu = R.menu.menu;

        /*
        * if user is already logged in
        * dynMenu = R.menu.logout_menu
        * */

        //**********Ahmed***********
        listView = (ListView)findViewById(R.id.ListView);
        adapter = new BarberArrayAdapter(this, barberList);
        listView.setAdapter(adapter);

        sampleBarber();
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, BarberProfile.class);
                startActivity(intent);
            }
        });
        //**********Ahmed***********


        //**********Ethan***********
        //Your code here
        //**********Ethan***********


/*
        //**********Pranav***********
        //when profile button is pressed activity changes to userprofile
        final MediaPlayer pushSoundMP  = MediaPlayer.create(this, R.raw.tiny_button_push);
        Button bt;
        bt = (Button)findViewById(R.id.profilebtn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushSoundMP.start();
                Intent i = new Intent(MainActivity.this, UserProfile.class);
                startActivity(i);
            }
        });

        //**********Pranav***********
        */
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater.inflate(R.menu.login_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void sampleBarber() {
        Barber barber1 = new Barber("Ahmed Naeem", 5.0f, "29 Portelli Cres", "Ajax", "Shop n' Cuts");
        Barber barber2 = new Barber("Jazz Cartier", 3.5f, "13 Habbourd Ave", "Oshawa", "Jazz Store");
        Barber barber3 = new Barber("Lupe Fiasco", 2.2f, "695 Honololo Cres", "Ajax", "Jhoor Shop");
        barberList.add(barber1);
        barberList.add(barber2);
        barberList.add(barber3);
    }



    //**********Ahmed***********

    //**********Ahmed***********



    //**********Paljor***********
        //Your code here

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logIn:
                Intent login = new Intent(this, SigninActivity.class);
                startActivity(login);
                Toast.makeText(getApplicationContext(),"Log in", Toast.LENGTH_SHORT).show();

                break;
            case R.id.SignUp:

                break;
            case R.id.logOut:

                break;
            case R.id.profile:
                Intent i = new Intent(MainActivity.this, UserProfile.class);
                startActivity(i);
                break;
            default:

        }
        return super.onOptionsItemSelected(item);
    }

    //**********Paljor***********



    //**********Ethan***********
        //Your code here
    //**********Ethan***********



    //**********Pranav***********
    //button click sound

    //**********Pranav***********

}
