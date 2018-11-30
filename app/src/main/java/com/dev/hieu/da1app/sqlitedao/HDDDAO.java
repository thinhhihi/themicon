package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.HDD;

import java.util.ArrayList;
import java.util.List;

public class HDDDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public HDDDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertHDD(HDD hdd) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDHDD,hdd.id);
        contentValues.put(COLUMN_PRICEHDD, hdd.price);
        contentValues.put(COLUMN_RATINGHDD,hdd.rating);
        contentValues.put(COLUMN_SHORTDESGHDD, hdd.shortdesc);
        contentValues.put(COLUMN_TITLEHDD, hdd.title);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_HDD, null, contentValues);

        Log.e("insertHDD", "insert : " + id);

        sqLiteDatabase.close();

    }

    public HDD getHDDbyName(String hdd) {

        HDD hdd1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_HDD,
                new String[]{COLUMN_IDHDD, COLUMN_TITLEHDD, COLUMN_SHORTDESGHDD, COLUMN_PRICEHDD,COLUMN_RATINGHDD},
                COLUMN_TITLEHDD + "=?",
                new String[]{hdd}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idHDD = cursor.getString(cursor.getColumnIndex(COLUMN_IDHDD));

            String titleHDD = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEHDD));
            String shortdescHDD = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESGHDD));
            double priceHDD = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEHDD));
            double ratingHDD = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGHDD));

            hdd1 = new HDD();
            hdd1.setId(idHDD);
            hdd1.setPrice(priceHDD);
            hdd1.setRating(ratingHDD);
            hdd1.setShortdesc(shortdescHDD);
            hdd1.setTitle(titleHDD);
        }

        return hdd1;
    }

    public List<HDD> getAllHDD() {


        List<HDD> hddList = new ArrayList<>();

        String SELECT_ALL_HDD = "SELECT * FROM " + TABLE_HDD;

        Log.e("getAllHDD", SELECT_ALL_HDD);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_HDD, null);

if (cursor != null &&  cursor.moveToFirst()) {
    do {

        String idHDD = cursor.getString(cursor.getColumnIndex(COLUMN_IDHDD));

        String titleHDD = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEHDD));
        String shortdescHDD = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESGHDD));
        double priceHDD = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEHDD));
        double ratingHDD = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGHDD));

        HDD hdd1 = new HDD();
        hdd1.setId(idHDD);
        hdd1.setPrice(priceHDD);
        hdd1.setRating(ratingHDD);
        hdd1.setShortdesc(shortdescHDD);
        hdd1.setTitle(titleHDD);
        // them user vao List< User >
        hddList.add(hdd1);


    } while (cursor.moveToNext());
}
        cursor.close();
        sqLiteDatabase.close();

        return hddList;
    }

}
