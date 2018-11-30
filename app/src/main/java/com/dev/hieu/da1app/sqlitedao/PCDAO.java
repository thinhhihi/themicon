package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.PC;


import java.util.ArrayList;

public class PCDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public PCDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertPC(PC pc) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDPC,pc.id);
        contentValues.put(COLUMN_PRICEPC, pc.price);
        contentValues.put(COLUMN_RATINGPC,pc.rating);
        contentValues.put(COLUMN_SHORTDESPC, pc.shortdesc);
        contentValues.put(COLUMN_TITLEPC, pc.title);
        contentValues.put(COLUMN_IMGPC, pc.image);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_PC, null, contentValues);

        Log.e("insertPC", "insert : " + id);

        sqLiteDatabase.close();

    }

    public PC getPCbyName(String pc) {

        PC pc1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_PC,
                new String[]{COLUMN_IDPC, COLUMN_TITLEPC, COLUMN_SHORTDESPC, COLUMN_PRICEPC,COLUMN_RATINGPC,COLUMN_IMGPC},
                COLUMN_TITLEPC + "=?",
                new String[]{pc}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idPC = cursor.getString(cursor.getColumnIndex(COLUMN_IDPC));

            String titlePC = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEPC));
            String shortdescPC = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESPC));
            String imgPC = cursor.getString(cursor.getColumnIndex(COLUMN_IMGPC));
            double pricePC = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEPC));
            double ratingPC = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGPC));

            pc1 = new PC();
            pc1.setId(idPC);
            pc1.setPrice(pricePC);
            pc1.setRating(ratingPC);
            pc1.setShortdesc(shortdescPC);
            pc1.setTitle(titlePC);
            pc1.setImage(imgPC);
        }

        return pc1;
    }

    public ArrayList<PC> getAllPC() {


        ArrayList<PC> pcArrayList = new ArrayList<>();

        String SELECT_ALL_PC = "SELECT * FROM " + TABLE_PC;

        Log.e("getAllPC", SELECT_ALL_PC);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_PC, null);


        if (cursor != null && cursor.moveToFirst()) {
            do {
                String idPC = cursor.getString(cursor.getColumnIndex(COLUMN_IDPC));

                String titlePC = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEPC));
                String shortdescPC = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESPC));
                String imgPC = cursor.getString(cursor.getColumnIndex(COLUMN_IMGPC));
                double pricePC = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEPC));
                double ratingPC = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGPC));

                PC  pc1 = new PC();
                pc1.setId(idPC);
                pc1.setPrice(pricePC);
                pc1.setRating(ratingPC);
                pc1.setShortdesc(shortdescPC);
                pc1.setTitle(titlePC);
                pc1.setImage(imgPC);
                // them user vao ArrayList< User >
                pcArrayList.add(pc1);


            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return pcArrayList;
    }



}
