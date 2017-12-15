package com.example.amuna95.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class BarberProfileMenu extends AppCompatActivity {
    private TextView lblName;
    private TextView lblDescription;
    private TextView lblStoreName;
    private TextView lblAddress;
    private TextView lblCity;
    private TextView lblPostalCode;
    private TextView lblPhone;

    private BarberDBHelper helper;
    private String email;
    private MenuInflater inflater;
    private int dynMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_profile_menu);
        //*****Paljor----->
        Toolbar myActionbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(myActionbar);
        inflater = getMenuInflater();
        dynMenu = R.menu.barberprofilemenu;
        //  ********Paljor ----->/
    }

    @Override
    protected void onStart() {
        super.onStart();

        helper = new BarberDBHelper(this);
        email = helper.getEMAIL();
        Barber b = helper.getBarber(email);

        lblName = (TextView) findViewById(R.id.tabName);
        lblDescription = (TextView)findViewById(R.id.tabDescription);
        lblStoreName = (TextView)findViewById(R.id.lblStore);
        lblAddress = (TextView)findViewById(R.id.lblAddress);
        lblCity = (TextView)findViewById(R.id.lblCity);
        lblPostalCode = (TextView)findViewById(R.id.lblPostalCode);
        lblPhone = (TextView)findViewById(R.id.lblPhone);

        lblName.setText(b.getName());
        lblAddress.setText(b.getAddress());
        lblCity.setText(b.getCity());
        lblStoreName.setText(b.getStoreName());
        lblDescription.setText(b.getDescription());
        lblPostalCode.setText(b.getPostalCode());
        lblPhone.setText(b.getPhone());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater.inflate(dynMenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //**********Paljor***********
    //Your code here

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logOut:
                if(helper.logout()){
                    finish();
                    Intent toSignin = new Intent(this, SigninActivity.class);
                    startActivity(toSignin);
                }
                break;

            case R.id.home:
                finish();
                Intent toHome = new Intent(this, MainActivity.class);
                startActivity(toHome);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //**********Paljor***********


    public void onClickEditBarber(View view) {
        Intent i = new Intent(this, BarberProfileEdit.class);
        i.putExtra("EMAIL", email);
        startActivity(i);
    }

}
