package com.example.ik_2dm3.didaktikapp;

import android.Manifest;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;



public class Komikia_8 extends AppCompatActivity {


    private ViewPager viewPager;
    private ImageButton btnNextGame, btnPreviousGame;
    private int pag_anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komikia_8);

        btnNextGame = findViewById(R.id.btnNextGame);
        btnPreviousGame = findViewById(R.id.btnPreviousGame);

        /*btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);

        btnNextGame.setEnabled(false);
        btnNextGame.setVisibility(View.INVISIBLE);*/

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);


    }

   
}
