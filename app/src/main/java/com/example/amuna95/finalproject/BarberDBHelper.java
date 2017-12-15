package com.example.amuna95.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BarberDBHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 3;
    static final String TABLE = "Person";
    static final String CREATE_STATEMENT = "CREATE TABLE Person(\n" +
            " email varchar(100) primary key,\n" +
            " password varchar(100) not null,\n" +
            //barber or user
            " personType char not null,\n" +
            " name varchar(100) not null,\n" +
            " postalCode varchar(10) not null,\n" +
            " city varchar(20),\n" +
            " address varchar(100),\n" +
            " rating float,\n" +
            " storeName varchar(100),\n" +
            " description varchar(300),\n" +
            " phone varchar(11),\n" +
            " price decimal(5,2));\n";

    public BarberDBHelper(Context context) {
        super(context, "products", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Not required
    }

    public void sampleBarbers() {
        createBarber("Ahmed Naeem", "anaeem@gmail.com", "blabla", "Oshawa", "160 Ice",
                "Ahmed's Store", "The best place to get your cut", "L1L0H1", "647-555-2222");
        createBarber("Emily Rosee", "emiy@emili.com", "blabla", "Ajax", "99 Home Ave",
                "Top Cuts", "Specializing in women hair", "L2F8G3", "773-333-2222");
    }

    // CREATE
    public Barber createBarber(String name,
                                 String email,
                                 String password,
                                 String city,
                                 String address,
                                 String storeName,
                                 String description,
                               String postalCode,
                               String phone) {
        // create a new entity object (Product)
        Barber barber = new Barber(name, email, address, city, storeName, description, postalCode, phone);
        //Log.i("PRICE1", String.valueOf(price));

        // put that data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("email", email);
        newValues.put("password", password);
        newValues.put("name", name);
        newValues.put("city", city);
        newValues.put("address", address);
        newValues.put("description", description);
        newValues.put("personType", "B");
        newValues.put("rating", 0);
        newValues.put("phone", phone);
        newValues.put("storeName", storeName);
        newValues.put("postalCode", postalCode);
        //newValues.put("price", String.valueOf(price));

        long id = db.insert(TABLE, null, newValues);

        // update the contact's id
        //barber.setId(id);

        return barber;
    }

    // CREATE
    public User createUser(String name,
                               String email,
                               String password,
                               String postalCode) {
        // create a new entity object (Product)
        User user = new User(name, email, postalCode);
        //Log.i("PRICE1", String.valueOf(price));

        // put that data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("email", email);
        newValues.put("password", password);
        newValues.put("name", name);
        newValues.put("personType", "U");

        long id = db.insert(TABLE, null, newValues);

        // update the contact's id
        //user.setId(id);

        return user;
    }

    // READ
    public Barber getBarber(String email) {
        Barber barber = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"name", "rating", "address", "city", "description", "postalCode", "storeName", "phone"};
        String where = "email = ?";
        String[] whereArgs = new String[] { "" + email };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");

        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();

            String name = cursor.getString(0);
            float rating = cursor.getFloat(1);
            String address = cursor.getString(2);
            String city = cursor.getString(3);
            String description = cursor.getString(4);
            String postalCode = cursor.getString(5);
            String storeName = cursor.getString(6);
            String phone = cursor.getString(7);

            barber = new Barber(name, email, address, city, storeName, description, postalCode, phone);
            barber.setRating(rating);
            //contact.setId(id);
        }

        //Log.i("SQLite", "createContact(): " + contact);

        return barber;
    }

    // READ
    public User getUser(String email) {
        User user = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"name", "postalCode"};
        String where = "email = ?";
        String[] whereArgs = new String[] { "" + email };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");

        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();

            String name = cursor.getString(1);
            String postalCode = cursor.getString(2);

            user = new User(name, email, postalCode);

            //contact.setId(id);
        }

        //Log.i("SQLite", "createContact(): " + contact);

        return user;
    }


    public ArrayList<Barber> getAllBarbers() {
        ArrayList<Barber> barbers = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{"email", "name", "rating", "address", "city", "description",
                "postalCode", "storeName", "phone", "price"};
        String where = "personType = ?";
        String[] whereArgs = new String[]{"" + "B"};
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "rating");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                String email = cursor.getString(0);
                Log.i("SQL: EMAIL", email);
                String name = cursor.getString(1);
                float rating = cursor.getFloat(2);
                String address = cursor.getString(3);
                String city = cursor.getString(4);
                String description = cursor.getString(5);
                String postalCode = cursor.getString(6);
                String storeName = cursor.getString(7);
                String phone = cursor.getString(8);

                Barber barber = new Barber(name, email, address, city, storeName, description, postalCode, phone);
                barber.setRating(rating);

                barbers.add(barber);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return barbers;
    }

    // DELETE
    /*
    public boolean deleteContact(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "productId = ?", new String[] { "" + id });

        return (numRows == 1);
    }
    */
}
