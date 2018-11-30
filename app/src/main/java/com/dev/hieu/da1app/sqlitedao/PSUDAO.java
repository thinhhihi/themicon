package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.PSU;

import java.util.ArrayList;
import java.util.List;

public class PSUDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public PSUDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertPSU(PSU psu) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDPSU,psu.id);
        contentValues.put(COLUMN_PRICEPSU, psu.price);
        contentValues.put(COLUMN_RATINGPSU,psu.rating);
        contentValues.put(COLUMN_SHORTDESPSU, psu.shortdesc);
        contentValues.put(COLUMN_TITLEPSU, psu.title);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_PSU, null, contentValues);

        Log.e("insertPSU", "insert : " + id);

        sqLiteDatabase.close();

    }

    public PSU getPSUbyName(String psu) {

        PSU psu1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_PSU,
                new String[]{COLUMN_IDPSU, COLUMN_TITLEPSU, COLUMN_SHORTDESPSU, COLUMN_PRICEPSU,COLUMN_RATINGPSU},
                COLUMN_TITLEPSU + "=?",
                new String[]{psu}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idPSU = cursor.getString(cursor.getColumnIndex(COLUMN_IDPSU));

            String titlePSU = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEPSU));
            String shortdescPSU = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESPSU));
            double pricePSU = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEPSU));
            double ratingPSU = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGPSU));

            psu1 = new PSU();
            psu1.setId(idPSU);
            psu1.setPrice(pricePSU);
            psu1.setRating(ratingPSU);
            psu1.setShortdesc(shortdescPSU);
            psu1.setTitle(titlePSU);
        }

        return psu1;
    }

    public List<PSU> getAllPSU() {


        List<PSU> psuList = new ArrayList<>();

        String SELECT_ALL_PSU = "SELECT * FROM " + TABLE_PSU;

        Log.e("getAllPSU", SELECT_ALL_PSU);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_PSU, null);

      if (cursor != null &&  cursor.moveToFirst()) {

          do {

              String idPSU = cursor.getString(cursor.getColumnIndex(COLUMN_IDPSU));

              String titlePSU = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEPSU));
              String shortdescPSU = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESPSU));
              double pricePSU = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEPSU));
              double ratingPSU = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGPSU));

              PSU psu1 = new PSU();
              psu1.setId(idPSU);
              psu1.setPrice(pricePSU);
              psu1.setRating(ratingPSU);
              psu1.setShortdesc(shortdescPSU);
              psu1.setTitle(titlePSU);
              // them user vao List< User >
              psuList.add(psu1);


          } while (cursor.moveToNext());
      }
        cursor.close();
        sqLiteDatabase.close();

        return psuList;
    }

}
