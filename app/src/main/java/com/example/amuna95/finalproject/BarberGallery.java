package com.example.amuna95.finalproject;

import  android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by amuna95 on 12/12/2017.
 */

public class BarberGallery extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.barber_gallery, container, false);
        return rootView;
    }
}