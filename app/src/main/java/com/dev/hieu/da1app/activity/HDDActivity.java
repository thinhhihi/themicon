package com.dev.hieu.da1app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.HDDAdapter;
import com.dev.hieu.da1app.adapter.HDDAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.HDD;
import com.dev.hieu.da1app.model.HDD;
import com.dev.hieu.da1app.sqlitedao.HDDDAO;

import java.util.ArrayList;
import java.util.List;

public class HDDActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<HDD> hddList;
    private HDDDAO hdddao;
    private LinearLayoutManager linearLayoutManager;
    private HDDAdapter hddAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdd);
        AnhXa();
        for (int i = 0; i < 5; i++) {
            HDD hdd = new HDD(
                    "HDD"+i,
                    "HDD Seagate SkyHawk "+i+", Sata3, 256MB Cache",
                    "Tối ưu cho DVR và NVR, ổ đĩa lưu video giám sát SkyHawk ™ được thiết kế hoạt động cho 24 × 7 lượng lưu trữ lên tới "+i+" TB",
                    8.4,
                    60*i,
                    R.drawable.cpu2);
            hdddao.insertHDD(hdd);

        }
        AddRecyclerview();
    }
    void AnhXa() {

        recyclerView = findViewById(R.id.RecylerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        hddList = new ArrayList<>();
        hddList.clear();
        databaseHelper = new DatabaseHelper(this);
        hdddao  = new HDDDAO(databaseHelper);
    }

    void AddRecyclerview() {


        hddList = hdddao.getAllHDD();
        hddAdapter = new HDDAdapter(this, hddList, hdddao);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(hddAdapter);
    }
}
