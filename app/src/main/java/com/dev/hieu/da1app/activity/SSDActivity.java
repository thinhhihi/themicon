package com.dev.hieu.da1app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.SSDAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.SSD;
import com.dev.hieu.da1app.sqlitedao.SSDDAO;

import java.util.ArrayList;
import java.util.List;

public class SSDActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<SSD> sddList;
    private SSDDAO sdddao;
    private LinearLayoutManager linearLayoutManager;
    private SSDAdapter sddAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssd);
        AnhXa();
        for (int i = 0; i < 5; i++) {
            SSD sdd = new SSD(
                    "SSD"+i,
                    "Kit DDRam 4 AVEXIR "+8*i+"GB/2666 2COW - Core (Tản nhiệt -Led trắng)",
                    "AVEXIR CORE SERIES – DÒNG SSD DÀNH CHO GAME THỦ VÀ CÁC MODDER CHUYÊN NGHIỆP. \n",
                    5+i,
                    60*i,
                    R.drawable.cpu3);
            sdddao.insertSSD(sdd);

        }
        AddRecyclerview();
    }
    void AnhXa() {

        recyclerView = findViewById(R.id.RecylerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sddList = new ArrayList<>();
        sddList.clear();
        databaseHelper = new DatabaseHelper(this);
        sdddao  = new SSDDAO(databaseHelper);
    }

    void AddRecyclerview() {


        sddList = sdddao.getAllSSD();
        sddAdapter = new SSDAdapter(this, sddList, sdddao);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(sddAdapter);
    }
}
