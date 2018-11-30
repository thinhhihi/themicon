package com.dev.hieu.da1app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.MainAdapter;
import com.dev.hieu.da1app.adapter.MainAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.Main;
import com.dev.hieu.da1app.model.Main;
import com.dev.hieu.da1app.sqlitedao.MainDAO;

import java.util.ArrayList;
import java.util.List;

public class Main1Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Main> mainList;
    private MainDAO maindao;
    private LinearLayoutManager linearLayoutManager;
    private MainAdapter mainAdapter;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        AnhXa();
        for (int i = 0; i < 5; i++) {
            Main main = new Main(
                    "Main"+i,
                    "Mainboard GIGABYTE Z3"+i+"0 AORUS Gaming "+i,
                    "8th Generation Intel® Core™ Processors, and Intel® Pentium® and Celeron® Processors",
                    6+i,
                    200+i*10,
                    R.drawable.cpu2);
            maindao.insertMain(main);

        }
        AddRecyclerview();
    }
    void AnhXa() {

        recyclerView = findViewById(R.id.RecylerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mainList = new ArrayList<>();
        mainList.clear();
        databaseHelper = new DatabaseHelper(this);
        maindao  = new MainDAO(databaseHelper);
    }

    void AddRecyclerview() {


        mainList = maindao.getAllMain();
        mainAdapter = new MainAdapter(this, mainList, maindao);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mainAdapter);
    }
}
