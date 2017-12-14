package com.example.amuna95.finalproject;

/**
 * Created by 100557540
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setupGeolocation();
    };

    private void setupGeolocation() {
        // check to ensure I have permission
        verifyGeolocationPermission();
        // check to ensure that geolocation is enabled
        // request updates

        final MediaPlayer pushSoundMP  = MediaPlayer.create(this, R.raw.tiny_button_push);
        Button bt;
        bt = (Button)findViewById(R.id.homebtn);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushSoundMP.start();
                Intent i = new Intent(UserProfile.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    private static final int REQUEST_GEOLOCATION_PERMS = 1;

    private void verifyGeolocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {

                String[] perms = new String[] { Manifest.permission.ACCESS_FINE_LOCATION };
                requestPermissions(perms, REQUEST_GEOLOCATION_PERMS);
            } else {
                //geolocation permission granted, so request location updates
                verifyGeolocationEnabled();
                //Updates labels with very last location
                //location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //longitude = location.getLongitude();
                //latitude = location.getLatitude();
                //geocode(longitude, latitude);
            }
        }}

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] perms,
                                           int[] results) {
        if (requestCode == REQUEST_GEOLOCATION_PERMS) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                //geolocation permission granted, so request location updates
                verifyGeolocationEnabled();
            }
        }
    }

    private void verifyGeolocationEnabled() {
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);


        // check if geolocation is enabled in settings
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // TODO:  Request location updates
            requestLocationUpdates();
        } else {
            // show the settings app to let the user enable it
            String locationSettings = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGeoloc = new Intent(locationSettings);
            startActivity(enableGeoloc);
            // Note:  startActivityForResult() may be better here
        }
    }

    private void requestLocationUpdates() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);

        String recommendedProvider = locationManager.getBestProvider(criteria, true);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(recommendedProvider, 5000, 10, (LocationListener) this);
            Log.i("MapsDemo", "requestLocationUpdates()");
        }
    }


}
