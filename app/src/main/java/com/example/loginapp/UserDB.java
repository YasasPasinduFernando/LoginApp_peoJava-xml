package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDB {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public UserDB(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Add new user (register)
    public long addUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_USERNAME, username);
        values.put(DBHelper.COLUMN_PASSWORD, password);
        return db.insert(DBHelper.TABLE_USERS, null, values);
    }

    // Check login credentials (login)
    public boolean checkLogin(String username, String password) {
        Cursor cursor = db.query(DBHelper.TABLE_USERS,
                new String[]{DBHelper.COLUMN_USERNAME, DBHelper.COLUMN_PASSWORD},
                DBHelper.COLUMN_USERNAME + "=? AND " + DBHelper.COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null);

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }
}