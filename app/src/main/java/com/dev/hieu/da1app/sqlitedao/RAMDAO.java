package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.RAM;

import java.util.ArrayList;
import java.util.List;

public class RAMDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public RAMDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertRAM(RAM ram) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDRAM,ram.id);
        contentValues.put(COLUMN_PRICERAM, ram.price);
        contentValues.put(COLUMN_RATINGRAM,ram.rating);
        contentValues.put(COLUMN_SHORTDESGRAM, ram.shortdesc);
        contentValues.put(COLUMN_TITLERAM, ram.title);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_RAM, null, contentValues);

        Log.e("insertRAM", "insert : " + id);

        sqLiteDatabase.close();

    }

    public RAM getRAMbyName(String ram) {

        RAM ram1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_RAM,
                new String[]{COLUMN_IDRAM, COLUMN_TITLERAM, COLUMN_SHORTDESGRAM, COLUMN_PRICERAM,COLUMN_RATINGRAM},
                COLUMN_TITLERAM + "=?",
                new String[]{ram}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idRAM = cursor.getString(cursor.getColumnIndex(COLUMN_IDRAM));

            String titleRAM = cursor.getString(cursor.getColumnIndex(COLUMN_TITLERAM));
            String shortdescRAM = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESGRAM));
            double priceRAM = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICERAM));
            double ratingRAM = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGRAM));

            ram1 = new RAM();
            ram1.setId(idRAM);
            ram1.setPrice(priceRAM);
            ram1.setRating(ratingRAM);
            ram1.setShortdesc(shortdescRAM);
            ram1.setTitle(titleRAM);
        }

        return ram1;
    }

    public List<RAM> getAllRAM() {


        List<RAM> ramList = new ArrayList<>();

        String SELECT_ALL_RAM = "SELECT * FROM " + TABLE_RAM;

        Log.e("getAllRAM", SELECT_ALL_RAM);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_RAM, null);

  if (cursor != null &&  cursor.moveToFirst()) {

      do {

          String idRAM = cursor.getString(cursor.getColumnIndex(COLUMN_IDRAM));

          String titleRAM = cursor.getString(cursor.getColumnIndex(COLUMN_TITLERAM));
          String shortdescRAM = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESGRAM));
          double priceRAM = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICERAM));
          double ratingRAM = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGRAM));

          RAM ram1 = new RAM();
          ram1.setId(idRAM);
          ram1.setPrice(priceRAM);
          ram1.setRating(ratingRAM);
          ram1.setShortdesc(shortdescRAM);
          ram1.setTitle(titleRAM);
          // them user vao List< User >
          ramList.add(ram1);


      } while (cursor.moveToNext());
  }
        cursor.close();
        sqLiteDatabase.close();

        return ramList;
    }

}
