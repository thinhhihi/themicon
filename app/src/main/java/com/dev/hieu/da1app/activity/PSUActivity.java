package com.dev.hieu.da1app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.PSUAdapter;
import com.dev.hieu.da1app.adapter.PSUAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.PSU;
import com.dev.hieu.da1app.model.PSU;
import com.dev.hieu.da1app.sqlitedao.PSUDAO;

import java.util.ArrayList;
import java.util.List;

public class PSUActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<PSU> psuList;
    private PSUDAO psudao;
    private LinearLayoutManager linearLayoutManager;
    private PSUAdapter psuAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguon);

        AnhXa();
        for (int i = 0; i < 5; i++) {
            PSU psu = new PSU(
                    "PSU"+i,
                    "FSP Power Supply DAGGER Series SDA"+600*i+" - Active PFC - 80 Plus Gold - Full Modular - Micro ATX",
                    "Dòng sản phẩm DAGGER Series là lựa chọn tốt cho người dùng máy tính cá nhân cung cấp năng lượng hiệu hóa tới hơn 90%",
                    5+i,
                    30*i,
                    R.drawable.cpu2);
            psudao.insertPSU(psu);

        }
        AddRecyclerview();
    }
    void AnhXa() {

        recyclerView = findViewById(R.id.RecylerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        psuList = new ArrayList<>();
        psuList.clear();
        databaseHelper = new DatabaseHelper(this);
        psudao  = new PSUDAO(databaseHelper);
    }

    void AddRecyclerview() {


        psuList = psudao.getAllPSU();
        psuAdapter = new PSUAdapter(this, psuList, psudao);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(psuAdapter);
    }
}
