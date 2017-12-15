package com.example.amuna95.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BarberProfileEdit extends AppCompatActivity {
    private BarberDBHelper helper;
    private boolean noInputError = true;
    private String email;
    private String[] info;

    private EditText fldName;
    private EditText fldDescription;
    private EditText fldStoreName;
    private EditText fldAddress;
    private EditText fldCity;
    private EditText fldPostalCode;
    private EditText fldPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_profile_edit);

        Intent i = getIntent();
        info = i.getStringArrayExtra("newUser");
        email = i.getStringExtra("EMAIL");

        helper = new BarberDBHelper(this);
        Barber barber = helper.getBarber(email);

        fldName = (EditText)findViewById(R.id.fldName);
        fldDescription = (EditText)findViewById(R.id.fldDescription);
        fldStoreName = (EditText)findViewById(R.id.fldStoreName);
        fldAddress = (EditText)findViewById(R.id.fldAddress);
        fldCity = (EditText)findViewById(R.id.fldCity);
        fldPostalCode = (EditText)findViewById(R.id.fldPostalCode);
        fldPhone = (EditText)findViewById(R.id.fldPhone);
        if(email != null) {
            fldName.setText(barber.getName());
            fldDescription.setText(barber.getDescription());
            fldStoreName.setText(barber.getStoreName());
            fldAddress.setText(barber.getAddress());
            fldCity.setText(barber.getCity());
            fldPostalCode.setText(barber.getPostalCode());
            fldPhone.setText(barber.getPhone());
        }
    }

    public void onClickSave(View view) {
        String name = fldName.getText().toString().trim();
        String description = fldDescription.getText().toString().trim();
        String storeName = fldStoreName.getText().toString().trim();
        String address = fldAddress.getText().toString().trim();
        String city = fldCity.getText().toString().trim();
        String postalCode = fldPostalCode.getText().toString().trim();
        String phone = fldPhone.getText().toString().trim();

        if (name == null || name.isEmpty()) {
            Toast.makeText(this, "Please enter a name.",Toast.LENGTH_SHORT).show();
            noInputError = false;
        }

        if (description == null || description.isEmpty()) {
            Toast.makeText(this, "Please enter a description",Toast.LENGTH_SHORT).show();
            noInputError = false;
        }

        if (storeName == null || storeName.isEmpty()) {
            Toast.makeText(this, "Please enter a store name",Toast.LENGTH_SHORT).show();
            noInputError = false;
        }

        if (address == null || address.isEmpty()) {
            Toast.makeText(this, "Please enter an address.",Toast.LENGTH_SHORT).show();
            noInputError = false;
        }

        if (city == null || city.isEmpty()) {
            Toast.makeText(this, "Please enter a city.",Toast.LENGTH_SHORT).show();
            noInputError = false;
        }

        if (postalCode == null || postalCode.isEmpty()) {
            Toast.makeText(this, "Please enter a postal code.",Toast.LENGTH_SHORT).show();
            noInputError = false;
        }

        if (phone == null || phone.isEmpty()) {
            Toast.makeText(this, "Please enter a phone number",Toast.LENGTH_SHORT).show();
            noInputError = false;
        }

        if (noInputError) {
            if(email == null) {
                helper.createBarber(name, info[0], info[1], city, address, storeName, description,
                        postalCode, phone);
                Toast.makeText(this, "Profile has been created.",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Barber barber = new Barber(name, email, address, city, storeName, description, postalCode,
                        phone);
                helper.updateBarber(barber);
                Toast.makeText(this, "Profile has been updated.",Toast.LENGTH_SHORT).show();
                finish();
            }
        }

        noInputError = true;

    }
}
