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
    private static boolean loginStatus = false;
    private static int currentUser;
    private static final int BARBER = 0;
    private static final int USER = 1;
    private static String EMAIL=null;
    private static final int noUSER = 13;
    static final int DATABASE_VERSION = 33;
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
        //db.execSQL(USER_TABLE);
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
        createBarber("Tsering Paljor", "admin", "admin", "Toronto", "69 Home Ave",
                "Pro Quts", "All hair needs a good cut. Good cut needs a good barber", "L2F8G3", "773-999-0000");
    }
    public void sampleUsers(){
        createUser("Tsering Paljor", "abc@gmail.com", "abc", "L2F8G3");
        createUser("Pranav", "acc@gmail.com", "abc", "L2F8G3");
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
        newValues.put("postalCode", postalCode);
        newValues.put("personType", "U");

        long id = db.insert(TABLE, null, newValues);

        // update the contact's id
        //user.setId(id);

        return user;
    }

    public boolean updateBarber(Barber barber) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("name", barber.getName());
        newValues.put("city", barber.getCity());
        newValues.put("address", barber.getAddress());
        newValues.put("description", barber.getDescription());
        newValues.put("rating", barber.getRating());
        newValues.put("phone", barber.getPhone());
        newValues.put("storeName", barber.getStoreName());
        newValues.put("postalCode", barber.getPostalCode());
        int numRows = db.update(TABLE, newValues, "email = ?", new String[] { "" + barber.getEmail() });
        return (numRows == 1);
    }

    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("name", user.getName());
        newValues.put("postalCode", user.getPostalCode());
        int numRows = db.update(TABLE, newValues, "email = ?", new String[] { "" + user.getEmail() });
        return (numRows == 1);
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
            //barber.setRating(rating);
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
    public ArrayList<String> getBarbers(){
        ArrayList<String> barberEmail = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT email FROM "+TABLE +" WHERE personType = B";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                barberEmail.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return barberEmail;
    }

    public boolean login(String userEmail, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT email, password, personType from "+TABLE;
        Cursor cursor =db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                System.out.println(cursor.getString(0)+":"+cursor.getString(1)+":"+cursor.getString(2));
                if(userEmail.equals(cursor.getString(0)) && password.equals(cursor.getString(1))){
                    setLoginStatus(true);
                    if(cursor.getString(2).equals("B")){
                        currentUser = BARBER;
                    }
                    else if(cursor.getString(2).equals("U")){
                        currentUser = USER;
                    }
                    EMAIL = userEmail;
                    return true;
                }
            }while (cursor.moveToNext());
        }
        return false;
    }

    public String getEMAIL(){
        return EMAIL;
    }

    public ArrayList<String> getCustomers(){
        ArrayList<String> custEmail = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT email FROM "+TABLE +" WHERE personType = U";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                custEmail.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        return custEmail;
    }
    private void setLoginStatus(boolean status){
        loginStatus = status;
    }
    public boolean getLoginStatus(){
        return loginStatus;
    }

        //  Current logged in user
        //  0 = barber
        //  1 = user

    public int getCurrentUser(){
        if(getLoginStatus()){
            return currentUser;
        }
        return noUSER;
    }
    public boolean logout(){
        if(getLoginStatus()){
            setLoginStatus(false);
            EMAIL = null;
            return true;
        }
        return false;
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
