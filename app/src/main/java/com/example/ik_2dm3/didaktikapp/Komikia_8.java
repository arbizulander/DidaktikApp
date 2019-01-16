package com.example.ik_2dm3.didaktikapp;

import android.Manifest;
import android.content.Intent;
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
    static final int REQ_BTN = 0;
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

        btnPreviousGame.setEnabled(true);
        btnPreviousGame.setVisibility(View.VISIBLE);
        btnPreviousGame.setOnClickListener(v -> {
            //mp.stop();
            Intent i = new Intent(Komikia_8.this,Argazkiaaztertu_7.class);
            i.putExtra("pag_anterior",1);
            startActivityForResult(i, REQ_BTN);
            finish();
        });

        btnNextGame.setEnabled(true);
        btnNextGame.setVisibility(View.VISIBLE);
        btnNextGame.setOnClickListener(v -> {
            //mp.stop();
            Intent i = new Intent(Komikia_8.this,Debaterakogalderak_9.class);
            i.putExtra("pag_anterior",1);
            startActivityForResult(i, REQ_BTN);
            finish();
        });

    }

   
}
