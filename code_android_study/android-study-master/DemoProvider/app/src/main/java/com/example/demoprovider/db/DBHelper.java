package com.example.demoprovider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    private static final String name = "demo.db";
    private static final int version = 1;

    public DBHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_person = "CREATE TABLE person (" +
                "_id INTEGER PRIMARY KEY , " +
                "name VARCHAR , " +
                "phone VARCHAR, " +
                "sex VARCHAR, " +
                "age INTEGER );";
        db.execSQL(tb_person);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
