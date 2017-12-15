package com.example.amuna95.finalproject;

import android.content.Intent;
import  android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by amuna95 on 12/12/2017.
 */

public class BarberReview extends Fragment{
    private String email;
    private int RATING_BAR_UPDATE = 1;
    private BarberDBHelper helper;
    private boolean rateonce;
    private int i = 0;

    private RatingBar rb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rateonce = false;
        helper = new BarberDBHelper(getContext());
        email = getArguments().getString("EMAIL");
        Log.i("RATINGEMAIL", email);

        View rootView = inflater.inflate(R.layout.barber_review, container, false);
        rb = (RatingBar) rootView.findViewById(R.id.ratingBar2);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    i++;
                    Log.i("RATING6", String.valueOf(rating));
                    Barber b = helper.getBarber(email);
                    b.setRating(rating/i);
                    helper.updateBarber(b);
                    Toast.makeText(getContext(), "Rating updated.", Toast.LENGTH_SHORT).show();

                    //b = helper.getBarber(email);

                   // rb.setRating(b.getRating());

            }
        });

        return rootView;
    }
}
