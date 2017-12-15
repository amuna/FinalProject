package com.example.amuna95.finalproject;

import android.content.Intent;
import  android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by amuna95 on 12/12/2017.
 */

public class BarberReview extends Fragment{

    private RatingBar rb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.barber_review, container, false);
        rb = (RatingBar) rootView.findViewById(R.id.ratingBar2);
        rb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                return true;
            }
        });

        setRating();
        return rootView;
    }

    public void setRating() {
        String email = getArguments().getString("EMAIL");
        Float rating = getArguments().getFloat("RATING");
        Float numRating = getArguments().getFloat("RATINGNUM");
        String reviews = getArguments().getString("REVIEWS");
        rb.setRating(rating);


    }
}
