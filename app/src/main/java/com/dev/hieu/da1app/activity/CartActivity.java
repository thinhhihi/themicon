package com.dev.hieu.da1app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.CartAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.Cart;
import com.dev.hieu.da1app.sqlitedao.CartDAO;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Cart> carts;
    private CartDAO cartdao;
    private LinearLayoutManager linearLayoutManager;
    private CartAdapter cartAdapter;
    private DatabaseHelper databaseHelper;
    private Button btnHuy;
    private Button btnThanhToan;



private TextView tvmoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        AnhXa();

        AddRecyclerview();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this,NavigationActivity.class));
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sendemail();
            }
        });
        double count = 0;
        for (int i = 0;i< carts.size();i++){
            count = count + carts.get(i).getPrice();
        }
        tvmoney.setText(count+"");
    }
    void AnhXa() {

        recyclerView = findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        carts = new ArrayList<>();
        carts.clear();
        databaseHelper = new DatabaseHelper(this);
        cartdao  = new CartDAO(databaseHelper);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        tvmoney = findViewById(R.id.tvMoney);
    }

    void AddRecyclerview() {


        carts = cartdao.getAllCart();
        cartAdapter = new CartAdapter(this, carts, cartdao);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cartAdapter);
    }



    void sendemail(){

    }
    @Override
    protected void onResume() {
        double count = 0;
        for (int i = 0;i< carts.size();i++){
            count = count + carts.get(i).getPrice();
        }
        tvmoney.setText(count+"");
        super.onResume();


    }
}
