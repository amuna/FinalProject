package com.example.amuna95.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private Button registerBtn;
    private EditText email;
    private EditText passwordA;
    private EditText passwordB;
    private TextView signin;
    private ProgressDialog progressDialog;
    private RadioGroup radioGroup;
    private RadioButton userType;
    private  BarberDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar myActionbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(myActionbar);
        registerBtn = (Button)findViewById(R.id.signupBtn);
        email = (EditText)findViewById(R.id.email);
        passwordA = (EditText)findViewById(R.id.pass1);
        passwordB = (EditText)findViewById(R.id.pass2);
        progressDialog = new ProgressDialog(this);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        helper = new BarberDBHelper(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logIn:
                //  Action
                Intent intent = new Intent(this, SigninActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Log in", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                Toast.makeText(getApplicationContext(),"Home", Toast.LENGTH_SHORT).show();
                break;
            default:
                // Unknown Error
        }
        return super.onOptionsItemSelected(item);
    }


    public void register(View view){
        String userEmail = email.getText().toString().trim();
        String pass1 = passwordA.getText().toString().trim();
        String pass2 = passwordB.getText().toString().trim();
        int utype = radioGroup.getCheckedRadioButtonId();
        userType = (RadioButton)findViewById(utype);
        String usertype = userType.getText().toString().trim();

        if(TextUtils.isEmpty(usertype)){
            //  radio button is empty

            Toast.makeText(this, "User type unknown", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(userEmail)){
            //  email is empty

            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass1)){
            //  first password is empty
            Toast.makeText(this, "Please enter password correctly", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass2)){
            //  Second password is empty
            Toast.makeText(this, "Please enter password correctly", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(pass1.equals(pass2))){
            //  Passwords did not matched
            Toast.makeText(this, "Passwords did not matched", Toast.LENGTH_SHORT).show();
            return;
        }

        //  if everything checks out fine
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        /*
        * Write to database
        * helper.register(email, password, type)
        * */
        Intent signUpIntent;
        String[] reg = {userEmail, pass1, usertype};

        switch (usertype){
            case "Barber":
                progressDialog.dismiss();
                Barber b = helper.getBarber(userEmail);
                User u = helper.getUser(userEmail);
                /*
                ArrayList<String> barbers = helper.getBarbers();
                for(int i = 0; i<barbers.size();i++){
                    if(userEmail.equals(barbers.get(i))){
                        Toast.makeText(this,"Barber already Registered", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                */
                if(b == null && u == null){
                    signUpIntent = new Intent(this,BarberProfile.class);
                    signUpIntent.putExtra("newUser", reg);
                    Toast.makeText(this,"Barber", Toast.LENGTH_SHORT).show();
                    startActivity(signUpIntent);
                }
                Toast.makeText(this,"Barber already Registered", Toast.LENGTH_SHORT).show();
                break;
            case "Customer":
                progressDialog.dismiss();
                ArrayList<String> customers = helper.getBarbers();
                for(int i = 0; i<customers.size();i++){
                    if(userEmail.equals(customers.get(i))){
                        Toast.makeText(this,"User already Registered", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                signUpIntent = new Intent(this,UserProfile.class);
                signUpIntent.putExtra("newUser", reg);
                Toast.makeText(this,"Customer", Toast.LENGTH_SHORT).show();
                startActivity(signUpIntent);
                break;
            default:
                Toast.makeText(this,"Never Caught", Toast.LENGTH_SHORT).show();
                break;
        }
        return;
    }
}
