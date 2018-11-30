package com.dev.hieu.da1app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.GPUAdapter;
import com.dev.hieu.da1app.adapter.GPUAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.GPU;
import com.dev.hieu.da1app.model.GPU;
import com.dev.hieu.da1app.sqlitedao.GPUDAO;
import com.dev.hieu.da1app.sqlitedao.GPUDAO;

import java.util.ArrayList;
import java.util.List;

public class GPUActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private List<GPU> gpuList;
    private GPUDAO gpudao;
    private LinearLayoutManager linearLayoutManager;
    private GPUAdapter gpuAdapter;
    private DatabaseHelper databaseHelper;
   

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpu);

        AnhXa();
        for (int i = 0; i < 5; i++) {
            GPU gpu = new GPU(
                    "GPU"+i,
                    "GTX208"+i+"TI",
                    "Cực Mạnh",
                    10,
                    1000,
                    R.drawable.cpu2);
            gpudao.insertGPU(gpu);

            }
        AddRecyclerview();
        }


    void AnhXa() {

        recyclerView = findViewById(R.id.RecylerView);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gpuList = new ArrayList<>();
        gpuList.clear();
        databaseHelper = new DatabaseHelper(this);
        gpudao  = new GPUDAO(databaseHelper);
    }

    void AddRecyclerview() {


        gpuList = gpudao.getAllGPU();
        gpuAdapter = new GPUAdapter(this, gpuList, gpudao);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(gpuAdapter);
    }
}
