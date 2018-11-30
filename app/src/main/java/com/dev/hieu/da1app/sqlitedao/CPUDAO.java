package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.CPU;

import java.util.ArrayList;
import java.util.List;

public class CPUDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public CPUDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertCPU(CPU cpu) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDCPU,cpu.id);
        contentValues.put(COLUMN_PRICECPU, cpu.price);
        contentValues.put(COLUMN_RATINGCPU,cpu.rating);
        contentValues.put(COLUMN_SHORTDESCCPU, cpu.shortdesc);
        contentValues.put(COLUMN_TITLECPU, cpu.title);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_CPU, null, contentValues);

        Log.e("insertCPU", "insert : " + id);

        sqLiteDatabase.close();

    }

    public CPU getCPUbyName(String cpu) {

        CPU cpu1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_CPU,
                new String[]{COLUMN_IDCPU, COLUMN_TITLECPU, COLUMN_SHORTDESCCPU, COLUMN_PRICECPU,COLUMN_RATINGCPU},
                COLUMN_TITLECPU + "=?",
                new String[]{cpu}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idCPU = cursor.getString(cursor.getColumnIndex(COLUMN_IDCPU));

            String titleCPU = cursor.getString(cursor.getColumnIndex(COLUMN_TITLECPU));
            String shortdescCPU = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESCCPU));
            double priceCPU = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICECPU));
            double ratingCPU = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGCPU));

            cpu1 = new CPU();
            cpu1.setId(idCPU);
            cpu1.setPrice(priceCPU);
            cpu1.setRating(ratingCPU);
            cpu1.setShortdesc(shortdescCPU);
            cpu1.setTitle(titleCPU);
        }

        return cpu1;
    }

    public List<CPU> getAllCPU() {


        List<CPU> cpuList = new ArrayList<>();

        String SELECT_ALL_CPU = "SELECT * FROM " + TABLE_CPU;

        Log.e("getAllCPU", SELECT_ALL_CPU);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_CPU, null);


if (cursor != null &&  cursor.moveToFirst()) {


            do {

                String idCPU = cursor.getString(cursor.getColumnIndex(COLUMN_IDCPU));

                String titleCPU = cursor.getString(cursor.getColumnIndex(COLUMN_TITLECPU));
                String shortdescCPU = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESCCPU));
                double priceCPU = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICECPU));
                double ratingCPU = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGCPU));

                CPU cpu1 = new CPU();
                cpu1.setId(idCPU);
                cpu1.setPrice(priceCPU);
                cpu1.setRating(ratingCPU);
                cpu1.setShortdesc(shortdescCPU);
                cpu1.setTitle(titleCPU);
                // them user vao List< User >
                cpuList.add(cpu1);


            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return cpuList;
    }



}
