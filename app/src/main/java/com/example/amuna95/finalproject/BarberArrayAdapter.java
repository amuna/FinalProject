package com.example.amuna95.finalproject;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class BarberArrayAdapter extends ArrayAdapter<Barber> {

    private List<Barber> data;
    private Context context;

    public BarberArrayAdapter(Context context, List<Barber> data) {
        super(context, R.layout.barber_list, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View reusableView, ViewGroup parent) {
        Barber barber = data.get(position);

        if (reusableView == null) {
            // create a new view (this is the first item)
            LayoutInflater inflater = LayoutInflater.from(context);
            reusableView = inflater.inflate(R.layout.barber_list, parent, false);
        }

        ImageView imgBarber = (ImageView) reusableView.findViewById(R.id.imgBarber);
        //imgBarber.setImageResource();

        RatingBar ratingBar = (RatingBar) reusableView.findViewById(R.id.ratingBar);
        ratingBar.setRating(barber.getRating());

        TextView lblName = (TextView)reusableView.findViewById(R.id.lblName);
        lblName.setText(barber.getName());

        TextView lblAddress = (TextView)reusableView.findViewById(R.id.lblAddress);
        lblAddress.setText(barber.getAddress());

        TextView lblStoreName = (TextView)reusableView.findViewById(R.id.lblStoreName);
        lblStoreName.setText(barber.getStoreName());

        TextView lblCity = (TextView)reusableView.findViewById(R.id.lblCity);
        lblCity.setText(barber.getCity());

        return reusableView;
    }
}