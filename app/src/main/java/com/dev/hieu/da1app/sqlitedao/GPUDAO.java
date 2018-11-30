package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.GPU;

import java.util.ArrayList;
import java.util.List;

public class GPUDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public GPUDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertGPU(GPU gpu) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDGPU,gpu.id);
        contentValues.put(COLUMN_PRICEGPU, gpu.price);
        contentValues.put(COLUMN_RATINGGPU,gpu.rating);
        contentValues.put(COLUMN_SHORTDESGPU, gpu.shortdesc);
        contentValues.put(COLUMN_TITLEGPU, gpu.title);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_GPU, null, contentValues);

        Log.e("insertGPU", "insert : " + id);

        sqLiteDatabase.close();

    }

    public GPU getGPUbyName(String gpu) {

        GPU gpu1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_GPU,
                new String[]{COLUMN_IDGPU, COLUMN_TITLEGPU, COLUMN_SHORTDESGPU, COLUMN_PRICEGPU,COLUMN_RATINGGPU},
                COLUMN_TITLEGPU + "=?",
                new String[]{gpu}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idGPU = cursor.getString(cursor.getColumnIndex(COLUMN_IDGPU));

            String titleGPU = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEGPU));
            String shortdescGPU = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESGPU));
            double priceGPU = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEGPU));
            double ratingGPU = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGGPU));

            gpu1 = new GPU();
            gpu1.setId(idGPU);
            gpu1.setPrice(priceGPU);
            gpu1.setRating(ratingGPU);
            gpu1.setShortdesc(shortdescGPU);
            gpu1.setTitle(titleGPU);
        }

        return gpu1;
    }

    public List<GPU> getAllGPU() {


        List<GPU> gpuList = new ArrayList<>();

        String SELECT_ALL_GPU = "SELECT * FROM " + TABLE_GPU;

        Log.e("getAllGPU", SELECT_ALL_GPU);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_GPU, null);


if (cursor != null &&  cursor.moveToFirst()) {


    do {

        String idGPU = cursor.getString(cursor.getColumnIndex(COLUMN_IDGPU));

        String titleGPU = cursor.getString(cursor.getColumnIndex(COLUMN_TITLEGPU));
        String shortdescGPU = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESGPU));
        double priceGPU = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICEGPU));
        double ratingGPU = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGGPU));

        GPU gpu1 = new GPU();
        gpu1.setId(idGPU);
        gpu1.setPrice(priceGPU);
        gpu1.setRating(ratingGPU);
        gpu1.setShortdesc(shortdescGPU);
        gpu1.setTitle(titleGPU);
        // them user vao List< User >
        gpuList.add(gpu1);


    } while (cursor.moveToNext());
}
        cursor.close();
        sqLiteDatabase.close();

        return gpuList;
    }

}
