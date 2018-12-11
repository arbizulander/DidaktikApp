package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStorageDirectory;

public class Argazkiaaztertu_3 extends AppCompatActivity {

    private MediaPlayer mp;
    private ImageButton btnNext, btnPreviousGame;
    private Context cont = this;
    private MyOpenHelper db;
    private int pag_anterior;
    static final int REQ_BTN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkiaaztertu_3);
        setTitle("Argazkia aztertu");
        btnNext = findViewById(R.id.btnNext);
        btnPreviousGame = findViewById(R.id.btnPreviousGame);

        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);

        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);
        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);


        switch (pag_anterior){
                case 0:

                    break;
                case 1:
                    //btnNext.set
                    btnNext.setEnabled(true);
                    btnNext.setVisibility(View.VISIBLE);
                    btnNext.setOnClickListener(v -> {
                        mp.stop();
                        finish();
                    });

                    btnPreviousGame.setEnabled(true);
                    btnPreviousGame.setVisibility(View.VISIBLE);
                    btnPreviousGame.setOnClickListener(v -> {
                        mp.stop();
                        Intent i = new Intent(Argazkiaaztertu_3.this,Elementuakbilatuargazkian_2.class);
                        i.putExtra("pag_anterior",1);
                        startActivityForResult(i, REQ_BTN);
                        finish();
                    });

                    break;
            }

        mp = MediaPlayer.create(this, R.raw.a1_4);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                switch (pag_anterior){
                    case 0:
                        Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation);
                        btnNext.startAnimation(animacion);
                        animacion.setAnimationListener(new Animation.AnimationListener(){
                            @Override
                            public void onAnimationStart(Animation arg0) {

                            }
                            @Override
                            public void onAnimationRepeat(Animation arg0) {
                            }
                            @Override
                            public void onAnimationEnd(Animation arg0) {
                                btnNext.setVisibility(View.VISIBLE);
                                btnNext.setEnabled(true);
                                btnNext.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int i = 3;
                                        db=new MyOpenHelper(cont);
                                        db.ActualizarJuego_Id(i);
                                        db.close();

                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("result",1);
                                        setResult(Activity.RESULT_OK,returnIntent);
                                        finish();
                                    }
                                });
                            }
                        });
                        break;
                    case 1:
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });
                        break;
                }


            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();
            mp.stop();
        }
        return super.onKeyDown(keyCode, event);
    }
}
