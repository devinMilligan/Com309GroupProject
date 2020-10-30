package com.example.project309.app;

public class PointLocation {

    double longi;
    double latit;


    public PointLocation(double longitude, double latitude) {

        longi = longitude;
        latit = latitude;

    }

    public double getLatitude() {
        return latit;
    }

    public double getLongitude() {
        return longi;
    }
}
