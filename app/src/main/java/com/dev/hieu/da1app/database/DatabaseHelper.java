package com.dev.hieu.da1app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dev.hieu.da1app.Constants;


public class DatabaseHelper extends SQLiteOpenHelper implements Constants {
    public DatabaseHelper(Context context) {
        super(context, "Shopping", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CPU);
        sqLiteDatabase.execSQL(CREATE_TABLE_GPU);
        sqLiteDatabase.execSQL(CREATE_TABLE_MAIN);
        sqLiteDatabase.execSQL(CREATE_TABLE_CART);
        sqLiteDatabase.execSQL(CREATE_TABLE_HDD);
        sqLiteDatabase.execSQL(CREATE_TABLE_SSD);
        sqLiteDatabase.execSQL(CREATE_TABLE_RAM);
        sqLiteDatabase.execSQL(CREATE_TABLE_PSU);
        sqLiteDatabase.execSQL(CREATE_TABLE_PC);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PC);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CPU);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GPU);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MAIN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PSU);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HDD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SSD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RAM);

        onCreate(sqLiteDatabase);

    }
}
