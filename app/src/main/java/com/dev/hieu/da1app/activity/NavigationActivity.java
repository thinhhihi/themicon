package com.dev.hieu.da1app.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.adapter.PCAdapter;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.PC;
import com.dev.hieu.da1app.sqlitedao.PCDAO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NavigationActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewFlipper viewfliper;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private TextView textViewnameemail;
    private ListView lv;
    private EditText tvtfind;
    private RecyclerView recyclerView;
    private ArrayList<PC> pcList;
    private PCDAO pcdao;
    private GridLayoutManager gridLayoutManager;
    private PCAdapter pcAdapter;
    ArrayList<HashMap<String, String>> productList;
    private DatabaseHelper databaseHelper;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = findViewById(R.id.imageView);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Màn hình chính");
        }
        anhxa();
        actionViewFliper();
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        FirebaseUser emailfirebase = auth.getCurrentUser();
        String email1 = emailfirebase.getEmail();
        Log.e("Email: ",email1);
        textViewnameemail = headerview.findViewById(R.id.tvNamedEmail);
        textViewnameemail.setText(email1);
        AnhXa();
//        PC pc = new PC(
//                "PC",
//                "PC số 1",
//                "Z370"+" \nI7700"+"K\nRam 16GB\nPSU 1100W\nGTX 1080"+"\nSSD Samsung Evo 960 "+120,
//                16,
//                30,
//                R.drawable.logo);
//        pcdao.insertPC(pc);
//        Log.e("Anh", String.valueOf(pc.getImage()));
//        PC pc1 = new PC(
//                "PC1",
//                "PC số 1",
//                "Z370"+" \nI7700"+"K\nRam 16GB\nPSU 1100W\nGTX 1080"+"\nSSD Samsung Evo 960 "+120,
//                16,
//                30,
//                R.drawable.i7);
//        pcdao.insertPC(pc1);
//        Log.e("Anh", String.valueOf(pc1.getImage()));

//        for (int i = 0; i < 16; i++) {
//            PC pc = new PC(
//                    "PC"+i,
//                    "PC Build sẵn cực mạnh số "+i,
//                    "Z370"+i+" \nI770"+i+"K\nRam 16GB\nPSU 1100W\nRTX 208"+i+"\nSSD Samsung Evo 960 "+120*(i+1),
//                    16+i,
//                    30*i,
//                    R.drawable.cpu2);
//            pcdao.insertPC(pc);
//
//        }
                    PC pc = new PC(
                    "PC",
                    "PC số 1",
                    "Z370"+" \nI7700"+"K\nRam 16GB\nPSU 1100W\nGTX 1080"+"\nSSD Samsung Evo 960 "+120,
                    16,
                    30,
                         "" + R.drawable.cpu3);
        PC pc1 = new PC(
                "PC1",
                "PC số 2",
                "Z370"+" \nI58200"+"K\nRam 16GB\nPSU 1100W\nRTX 2080"+"\nSSD Samsung Evo 970 "+120,
                16,
                50,
                ""+R.drawable.cpu2);
        PC pc2 = new PC(
                "PC2",
                "PC số 3",
                "Z370"+" \nI3 3520"+"K\nRam 16GB\nPSU 1100W\nGTX 1080"+"\nSSD Green WD"+120,
                16,
                30,
                ""+R.drawable.cpu4);
        PC pc3 = new PC(
                "PC3",
                "PC số 4",
                "Z370"+" \nI58200"+"K\nRam 16GB\nPSU 1100W\nRTX 2080"+"\nSSD Samsung Evo 970 "+120,
                16,
                50,
                ""+R.drawable.cpu3);
        PC pc4 = new PC(
                "PC4",
                "PC số 5",
                "Z370"+" \nI7700"+"K\nRam 16GB\nPSU 1100W\nGTX 1080"+"\nSSD Samsung Evo 960 "+120,
                16,
                30,
                ""+R.drawable.cpu2);
        PC pc5 = new PC(
                "PC5",
                "PC số 6",
                "Z370"+" \nI58200"+"K\nRam 16GB\nPSU 1100W\nRTX 2080"+"\nSSD Samsung Evo 970 "+120,
                16,
                50,
                ""+R.drawable.cpu3);
        PC pc6 = new PC(
                "PC6",
                "PC số 7",
                "Z370"+" \nI58200"+"K\nRam 16GB\nPSU 1100W\nRTX 2080"+"\nSSD Samsung Evo 970 "+120,
                16,
                50,
                ""+R.drawable.cpu3);

            pcdao.insertPC(pc);
        pcdao.insertPC(pc1);
        pcdao.insertPC(pc2);
        pcdao.insertPC(pc3);
        pcdao.insertPC(pc4);
        pcdao.insertPC(pc5);
        pcdao.insertPC(pc6);

        AddRecyclerview();


    }

    private void actionViewFliper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://image1.infogame.vn/2016/08/25/68_w1llrpcimv_14039909_1764930577110240_3186796555180321575_n.jpg");
        mangquangcao.add("http://i.imgur.com/6kFwlGQ.jpg");
        mangquangcao.add("http://i.imgur.com/7HHe8xr.jpg");
        mangquangcao.add("http://i.imgur.com/xrgAsXA.jpg");
        for (int i =0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewfliper.addView(imageView);

        }
        viewfliper.setFlipInterval(5000);
        viewfliper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewfliper.setInAnimation(animation_slide_in);
        viewfliper.setOutAnimation(animation_slide_out);
    }

    private void anhxa() {
        viewfliper =  findViewById(R.id.viewfliper);
        lv = findViewById(R.id.list_view);
        tvtfind = findViewById(R.id.tvtfind);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.navigation, menu);
//        MenuItem search = menu.findItem(R.id.action_find);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
//        searchView.setOnQueryTextListener(NavigationActivity.this);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_find) {
//            return true;
//        }
//        if (id == R.id.cart){
//                Intent intent = new Intent(NavigationActivity.this,CartActivity.class);
//                startActivity(intent);
//
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
        Intent intent = new Intent(NavigationActivity.this,Main1Activity.class);
        startActivity(intent);

        } else if (id == R.id.nav_cpu) {
            Intent intent = new Intent(NavigationActivity.this,CPUActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gpu) {
            Intent intent = new Intent(NavigationActivity.this,GPUActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_ram) {
            Intent intent = new Intent(NavigationActivity.this,RAMActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_psu) {
            Intent intent = new Intent(NavigationActivity.this,PSUActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_ssd) {
            Intent intent = new Intent(NavigationActivity.this,SSDActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_hdd) {
            Intent intent = new Intent(NavigationActivity.this,HDDActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_case) {
            Intent intent = new Intent(NavigationActivity.this,MapsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_information) {
            Intent intent = new Intent(NavigationActivity.this,InformationActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_logout) {
            auth.signOut();
            Intent intent = new Intent(NavigationActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

        }
        else if (id == R.id.nav_exit) {
            Exit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void Exit() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thoát");
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.show();



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_find:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.activity_name_pc1);
                dialog.show();
                break;
            case R.id.cart:
                Intent intent = new Intent(NavigationActivity.this,CartActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    void AnhXa() {

        recyclerView = findViewById(R.id.RecylerView);
        gridLayoutManager = new GridLayoutManager(this,2);
        pcList = new ArrayList<>();
        pcList.clear();
        databaseHelper = new DatabaseHelper(this);
        pcdao  = new PCDAO(databaseHelper);
    }

    void AddRecyclerview() {

        pcList = pcdao.getAllPC();
        pcAdapter = new PCAdapter(this, pcList, pcdao);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(pcAdapter);
    }


}
