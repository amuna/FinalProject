package com.example.amuna95.finalproject;

import  android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by amuna95 on 12/12/2017.
 */

public class BarberAbout extends Fragment {
    private TextView lblName;
    private TextView lblDescription;
    private TextView lblStoreName;
    private TextView lblAddress;
    private TextView lblCity;
    private TextView lblPostalCode;
    private TextView lblPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.barber_about, container, false);
        setText(rootView);
        return rootView;
    }

    public void setText(View view) {
        lblName = view.findViewById(R.id.tabName);
        lblDescription = view.findViewById(R.id.tabDescription);
        lblStoreName = view.findViewById(R.id.lblStore);
        lblAddress = view.findViewById(R.id.lblAddress);
        lblCity = view.findViewById(R.id.lblCity);
        lblPostalCode = view.findViewById(R.id.lblPostalCode);
        lblPhone = view.findViewById(R.id.lblPhone);

        String[] data = getArguments().getStringArray("DATA");
        lblName.setText(data[1]);
        lblAddress.setText(data[2]);
        lblCity.setText(data[3]);
        lblStoreName.setText(data[4]);
        lblDescription.setText(data[5]);
        lblPostalCode.setText(data[6]);
        lblPhone.setText(data[7]);
    }




}
