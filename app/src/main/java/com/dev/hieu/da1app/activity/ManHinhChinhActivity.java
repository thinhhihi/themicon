package com.dev.hieu.da1app.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.dev.hieu.da1app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ManHinhChinhActivity extends AppCompatActivity {
    private ViewFlipper viewfliper;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Màn hình chính");
        }
        anhxa();
        actionViewFliper();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        View headerview = navigationView.getHeaderView(0);
//        textViewnameemail = headerview.findViewById(R.id.email);
    }

    private void anhxa() {
        viewfliper =  findViewById(R.id.viewfliper);
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
    public void cardview2(View view) {
    }

    public void btnmuahang(View view) {
    }

    public void cardview1(View view) {
    }

    public void btnmuahang2(View view) {
    }

    public void cardview3(View view) {
    }

    public void btnmuahang3(View view) {
    }

    public void cardview4(View view) {
    }

    public void btnmuahang4(View view) {
    }
}
