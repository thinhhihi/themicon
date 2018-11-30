package com.dev.hieu.da1app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.CPUAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.CPU;
import com.dev.hieu.da1app.sqlitedao.CPUDAO;

import java.util.ArrayList;
import java.util.List;

public class CPUActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<CPU> cpus;
    private CPUDAO cpudao;
    private LinearLayoutManager linearLayoutManager;
    private CPUAdapter cpuAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcpu);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("CPU");
        }
        AnhXa();

        for (int i = 0; i < 5 ; i++) {
            CPU cpu = new CPU( "CPU"+i,
                    "I7-770"+i+"K",
                    "Bộ xử lý Intel® Core™ i7 thế hệ thứ 7",
                    9,
                    350,
                    R.drawable.cpu1);
            cpudao.insertCPU(cpu);
        }


        AddRecyclerview();
    }
    void AnhXa() {

        recyclerView = findViewById(R.id.RecylerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        cpus = new ArrayList<>();
        cpus.clear();
        databaseHelper = new DatabaseHelper(this);
        cpudao  = new CPUDAO(databaseHelper);
    }

    void AddRecyclerview() {


        cpus = cpudao.getAllCPU();
        cpuAdapter = new CPUAdapter(this, cpus, cpudao);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cpuAdapter);
    }

}
