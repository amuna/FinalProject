package com.example.amuna95.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BarberProfileMenu extends AppCompatActivity {
    private TextView lblName;
    private TextView lblDescription;
    private TextView lblStoreName;
    private TextView lblAddress;
    private TextView lblCity;
    private TextView lblPostalCode;
    private TextView lblPhone;

    private BarberDBHelper helper;
    private String email = "anaeem@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_profile_menu);
    }

    @Override
    protected void onStart() {
        super.onStart();

        helper = new BarberDBHelper(this);
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

    public void onClickEditBarber(View view) {
        Intent i = new Intent(this, BarberProfileEdit.class);
        i.putExtra("EMAIL", email);
        startActivity(i);
    }
}
