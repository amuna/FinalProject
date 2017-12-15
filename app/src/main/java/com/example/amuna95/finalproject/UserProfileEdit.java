package com.example.amuna95.finalproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by 100557540
 */

public class UserProfileEdit extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_edit);

        final MediaPlayer pushSoundMP = MediaPlayer.create(this, R.raw.tiny_button_push);

        Button bt;
        bt = (Button) findViewById(R.id.savebtn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushSoundMP.start();
                Intent i = new Intent(UserProfileEdit.this, UserProfile.class);
                startActivity(i);
            }
        });

    }
}