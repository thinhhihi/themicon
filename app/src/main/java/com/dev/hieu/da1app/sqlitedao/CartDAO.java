package com.dev.hieu.da1app.sqlitedao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev.hieu.da1app.Constants;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartDAO implements Constants {
    private DatabaseHelper databaseHelper;

    public CartDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }


    public void insertCart(Cart cart) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IDCART,cart.id);
        contentValues.put(COLUMN_PRICECART, cart.price);
        contentValues.put(COLUMN_RATINGCART,cart.rating);
        contentValues.put(COLUMN_SHORTDESCCART, cart.shortdesc);
        contentValues.put(COLUMN_TITLECART, cart.title);

        // tao cau lenh insert

        long id = sqLiteDatabase.insert(TABLE_CART, null, contentValues);

        Log.e("insertCart", "insert : " + id);

        sqLiteDatabase.close();

    }

    public Cart getCartbyName(String cart) {

        Cart cart1 = null;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_CART,
                new String[]{COLUMN_IDCART, COLUMN_TITLECART, COLUMN_SHORTDESCCART, COLUMN_PRICECART,COLUMN_RATINGCART},
                COLUMN_TITLECART + "=?",
                new String[]{cart}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            String idCart = cursor.getString(cursor.getColumnIndex(COLUMN_IDCART));

            String titleCart = cursor.getString(cursor.getColumnIndex(COLUMN_TITLECART));
            String shortdescCart = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESCCART));
            double priceCart = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICECART));
            double ratingCart = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGCART));

            cart1 = new Cart();
            cart1.setId(idCart);
            cart1.setPrice(priceCart);
            cart1.setRating(ratingCart);
            cart1.setShortdesc(shortdescCart);
            cart1.setTitle(titleCart);
        }

        return cart1;
    }

    public List<Cart> getAllCart() {


        List<Cart> cartList = new ArrayList<>();

        String SELECT_ALL_Cart = "SELECT * FROM " + TABLE_CART;

        Log.e("getAllCart", SELECT_ALL_Cart);

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_Cart, null);
        if (cursor != null && cursor.moveToFirst()){

            do {

                String idCart = cursor.getString(cursor.getColumnIndex(COLUMN_IDCART));
                String titleCart = cursor.getString(cursor.getColumnIndex(COLUMN_TITLECART));
                String shortdescCart = cursor.getString(cursor.getColumnIndex(COLUMN_SHORTDESCCART));
                double priceCart = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICECART));
                double ratingCart = cursor.getDouble(cursor.getColumnIndex(COLUMN_RATINGCART));

                Cart cart1 = new Cart();
                cart1.setId(idCart);
                cart1.setPrice(priceCart);
                cart1.setRating(ratingCart);
                cart1.setShortdesc(shortdescCart);
                cart1.setTitle(titleCart);
                // them user vao List< User >
                cartList.add(cart1);


            } while (cursor.moveToNext());

            cursor.close();
            sqLiteDatabase.close();
        }


        return cartList;
    }

    public int deleteCart(String cart) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_CART,
                COLUMN_IDCART + "=?", new String[]{cart});

    }


}
