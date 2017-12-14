package com.example.amuna95.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.amuna95.finalproject.R.id.parent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



        //**********Paljor***********
        //Your code here
        //**********Paljor***********



        //**********Ethan***********
        //Your code here
        //**********Ethan***********



        //**********Pranav***********

        //**********Pranav***********
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
    //**********Paljor***********



    //**********Ethan***********
        //Your code here
    //**********Ethan***********



    //**********Pranav***********
    public void toProfile(View view){
        Intent i = new Intent(this, UserProfile.class);
        startActivity(i);
    }
    //**********Pranav***********

}
