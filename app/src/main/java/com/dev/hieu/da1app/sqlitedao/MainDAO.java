package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.Main;

import java.util.ArrayList;
import java.util.List;

public class MainDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public MainDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertMain(Main main) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDMAIN,main.id);
        contentValues.put(COLUMN_PRICEMAIN, main.price);
        contentValues.put(COLUMN_RATINGMAIN,main.rating);
        contentValues.put(COLUMN_SHORTDESGMAIN, main.shortdesc);
        contentValues.put(COLUMN_TITLEMAIN, main.title);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_MAIN, null, contentValues);

        Log.e("insertMain", "insert : " + id);

        sqLiteDatabase.close();

    }

    public Main getMainbyName(String main) {

        Main main1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_MAIN,
                new String[]{COLUMN_IDMAIN, COLUMN_TITLEMAIN, COLUMN_SHORTDESGMAIN, COLUMN_PRICEMAIN,COLUMN_RATINGMAIN},
                COLUMN_TITLEMAIN + "=?",
                new String[]{main}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idMain = cursor.getString(cursor.getColumnIndex(COLUMN_IDMAIN));

            String titleMain = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEMAIN));
            String shortdescMain = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESGMAIN));
            double priceMain = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEMAIN));
            double ratingMain = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGMAIN));

            main1 = new Main();
            main1.setId(idMain);
            main1.setPrice(priceMain);
            main1.setRating(ratingMain);
            main1.setShortdesc(shortdescMain);
            main1.setTitle(titleMain);
        }

        return main1;
    }

    public List<Main> getAllMain() {


        List<Main> mainList = new ArrayList<>();

        String SELECT_ALL_MAIN = "SELECT * FROM " + TABLE_MAIN;

        Log.e("getAllMain", SELECT_ALL_MAIN);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_MAIN, null);

if (cursor != null &&  cursor.moveToFirst()) {

    do {

        String idMain = cursor.getString(cursor.getColumnIndex(COLUMN_IDMAIN));

        String titleMain = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEMAIN));
        String shortdescMain = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESGMAIN));
        double priceMain = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEMAIN));
        double ratingMain = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGMAIN));

        Main main1 = new Main();
        main1.setId(idMain);
        main1.setPrice(priceMain);
        main1.setRating(ratingMain);
        main1.setShortdesc(shortdescMain);
        main1.setTitle(titleMain);
        // them user vao List< User >
        mainList.add(main1);


    } while (cursor.moveToNext());
}
        cursor.close();
        sqLiteDatabase.close();

        return mainList;
    }

}
