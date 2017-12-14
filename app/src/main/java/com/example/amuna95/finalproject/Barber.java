package com.example.amuna95.finalproject;

/**
 * Created by amuna95 on 12/12/2017.
 */

public class Barber {
    private String name;
    //Set to zero when first created
    private float rating;
    private String address;
    private String city;
    private String storeName;
    private String description;
    private long id;
    private String email;
    private String postalCode;
    private String phone;

    public Barber(String _name, String _email, String _address, String _city, String _storeName,
                  String _description, String _postalCode, String _phone) {
        this.name = _name;
        this.email = _email;
        this.address = _address;
        this.city = _city;
        this.storeName = _storeName;
        this.description = _description;
        this.postalCode = _postalCode;
        this.rating = 0;
        this.phone = _phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
