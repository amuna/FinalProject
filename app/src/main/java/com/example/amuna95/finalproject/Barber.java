package com.example.amuna95.finalproject;

/**
 * Created by amuna95 on 12/12/2017.
 */

public class Barber {
    private String name;
    private float rating;
    private String address;
    private String city;
    private String storeName;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Barber(String _name, float _rating, String _address, String _city, String _storeName) {
        this.name = _name;
        this.rating = _rating;
        this.address = _address;
        this.city = _city;
        this.storeName = _storeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
