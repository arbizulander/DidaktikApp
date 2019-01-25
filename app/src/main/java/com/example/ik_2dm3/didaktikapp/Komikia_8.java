package com.example.ik_2dm3.didaktikapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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
    private Context cont = this;
    private MyOpenHelper db;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komikia_8);

        setTitle("Komikia");
        btnNextGame = findViewById(R.id.btnNextGame);
        btnNextGame.setEnabled(false);
        btnNextGame.setVisibility(View.INVISIBLE);
        btnPreviousGame = findViewById(R.id.btnPreviousGame);
        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);
/*
        switch (pag_anterior){
            case 0:
                break;
            case 1:
                btnNextGame.setEnabled(true);
                btnNextGame.setVisibility(View.VISIBLE);
                btnNextGame.setOnClickListener(v -> {
                    finish();
                });

                btnPreviousGame.setEnabled(true);
                btnPreviousGame.setVisibility(View.VISIBLE);
                btnPreviousGame.setOnClickListener(v -> {
                    Intent i = new Intent(Komikia_8.this,Argazkiaaztertu_7.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }
*/
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, pag_anterior);

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
            /*
            //mp.stop();
            Intent i = new Intent(Komikia_8.this,Debaterakogalderak_9.class);
            i.putExtra("pag_anterior",1);
            startActivityForResult(i, REQ_BTN);
            finish();
            */
            switch (pag_anterior){
                case 0:
                    int i = 8;
                    db=new MyOpenHelper(cont);
                    db.ActualizarJuego_Id(i);
                    db.close();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",1);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                    break;

                case 1:
                    break;
            }

        });

    }

   
}
