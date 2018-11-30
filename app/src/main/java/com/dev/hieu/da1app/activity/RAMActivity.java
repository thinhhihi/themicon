package com.dev.hieu.da1app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.RAMAdapter;
import com.dev.hieu.da1app.adapter.RAMAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.RAM;
import com.dev.hieu.da1app.model.RAM;
import com.dev.hieu.da1app.sqlitedao.RAMDAO;

import java.util.ArrayList;
import java.util.List;

public class RAMActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RAM> ramList;
    private RAMDAO ramdao;
    private LinearLayoutManager linearLayoutManager;
    private RAMAdapter ramAdapter;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ram);

        AnhXa();
        for (int i = 0; i < 5; i++) {
            RAM ram = new RAM(
                    "RAM"+i,
                    "Kit DDRam 4 AVEXIR "+8*i+"GB/2666 2COW - Core (Tản nhiệt -Led trắng)",
                    "AVEXIR CORE SERIES – DÒNG RAM DÀNH CHO GAME THỦ VÀ CÁC MODDER CHUYÊN NGHIỆP. \n",
                    5+i,
                    60*i,
                    R.drawable.cpu3);
            ramdao.insertRAM(ram);

        }
        AddRecyclerview();
    }
    void AnhXa() {

        recyclerView = findViewById(R.id.RecylerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ramList = new ArrayList<>();
        ramList.clear();
        databaseHelper = new DatabaseHelper(this);
        ramdao  = new RAMDAO(databaseHelper);
    }

    void AddRecyclerview() {


        ramList = ramdao.getAllRAM();
        ramAdapter = new RAMAdapter(this, ramList, ramdao);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(ramAdapter);
    }
}
