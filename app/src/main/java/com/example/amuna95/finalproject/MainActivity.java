package com.example.amuna95.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

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
    //Ahmed
    private ArrayList<Barber> barberList = new ArrayList<Barber>();
    private BarberArrayAdapter adapter;
    private ListView listView;
    private BarberDBHelper helper;
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
        helper = new BarberDBHelper(this);
        //helper.sampleBarbers();
        barberList = helper.getAllBarbers();

        listView = (ListView)findViewById(R.id.ListView);
        adapter = new BarberArrayAdapter(this, barberList);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, BarberProfile.class);
                Barber barber = (Barber)adapter.getItem(i);
                String email = barber.getEmail();
                //String email = ((TextView)findViewById(R.id.lblStoreName)).getText().toString();
                Log.i("EMAILEMAIL:", email);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
            }
        });
        //**********Ahmed***********


        //**********Ethan***********
        //Your code here
        //**********Ethan***********



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
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater.inflate(R.menu.login_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    //**********Ahmed***********
    public void updateContactList() {
        /*
        barberList = BarberDBHelper.getAllBarbers();
        adapter = new BarberArrayAdapter(this, barberList);
        listView.setAdapter(adapter);
        */
    }
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
