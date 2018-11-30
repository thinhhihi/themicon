package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.SSD;

import java.util.ArrayList;
import java.util.List;

public class SSDDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public SSDDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertSSD(SSD ssd) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDSSD,ssd.id);
        contentValues.put(COLUMN_PRICESSD, ssd.price);
        contentValues.put(COLUMN_RATINGSSD,ssd.rating);
        contentValues.put(COLUMN_SHORTDESSSD, ssd.shortdesc);
        contentValues.put(COLUMN_TITLESSD, ssd.title);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_SSD, null, contentValues);

        Log.e("insertSSD", "insert : " + id);

        sqLiteDatabase.close();

    }

    public SSD getSSDbyName(String ssd) {

        SSD ssd1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_SSD,
                new String[]{COLUMN_IDSSD, COLUMN_TITLESSD, COLUMN_SHORTDESSSD, COLUMN_PRICESSD,COLUMN_RATINGSSD},
                COLUMN_TITLESSD + "=?",
                new String[]{ssd}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idSSD = cursor.getString(cursor.getColumnIndex(COLUMN_IDSSD));

            String titleSSD = cursor.getString(cursor.getColumnIndex(COLUMN_TITLESSD));
            String shortdescSSD = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESSSD));
            double priceSSD = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICESSD));
            double ratingSSD = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGSSD));

            ssd1 = new SSD();
            ssd1.setId(idSSD);
            ssd1.setPrice(priceSSD);
            ssd1.setRating(ratingSSD);
            ssd1.setShortdesc(shortdescSSD);
            ssd1.setTitle(titleSSD);
        }

        return ssd1;
    }

    public List<SSD> getAllSSD() {


        List<SSD> ssdList = new ArrayList<>();

        String SELECT_ALL_SSD = "SELECT * FROM " + TABLE_SSD;

        Log.e("getAllSSD", SELECT_ALL_SSD);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_SSD, null);

     if (cursor != null &&  cursor.moveToFirst()) {

         do {

             String idSSD = cursor.getString(cursor.getColumnIndex(COLUMN_IDSSD));

             String titleSSD = cursor.getString(cursor.getColumnIndex(COLUMN_TITLESSD));
             String shortdescSSD = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESSSD));
             double priceSSD = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICESSD));
             double ratingSSD = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGSSD));

             SSD ssd1 = new SSD();
             ssd1.setId(idSSD);
             ssd1.setPrice(priceSSD);
             ssd1.setRating(ratingSSD);
             ssd1.setShortdesc(shortdescSSD);
             ssd1.setTitle(titleSSD);
             // them user vao List< User >
             ssdList.add(ssd1);


         } while (cursor.moveToNext());
     }
        cursor.close();
        sqLiteDatabase.close();

        return ssdList;
    }
}
