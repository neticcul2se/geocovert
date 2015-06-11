package com.van.pae.geocovert;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    double LATITUDE = 13.8473;
    double LONGITUDE =100.856;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView myLatitude = (TextView)findViewById(R.id.mylatitude);
        TextView myLongitude = (TextView)findViewById(R.id.mylongitude);
        TextView myAddress = (TextView)findViewById(R.id.myaddress);

        myLatitude.setText("Latitude: " + String.valueOf(LATITUDE));
        myLongitude.setText("Longitude: " + String.valueOf(LONGITUDE));

        Locale mLocale = new Locale("th");
        Geocoder geocoder = new Geocoder(this, mLocale);

        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);

            if(addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                String ss=strReturnedAddress.toString();
                String s2[]=ss.split("\n");
                String s3[]=s2[1].split(" ");
                myAddress.setText(s3[3]);
            }
            else{
                myAddress.setText("No Address returned!");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            myAddress.setText("Canont get Address!");
        }

    }
}

/*
Locale mLocale = new Locale("th");
Geocoder geocoder = new Geocoder(this, mLocale);

String ss=strReturnedAddress.toString();
String s2[]=ss.split("\n");
String s3[]=s2[1].split(" ");
myAddress.setText(s3[3]);*/