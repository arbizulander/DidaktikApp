package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Debaterakogalderak_9 extends AppCompatActivity {

    private MediaPlayer mp;
    private ImageButton btnNext, btnPreviousGame;
    private Context cont = this;
    private MyOpenHelper db;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    private ImageView fotodebate;
    private TextView galdera1, galdera2, galdera3, galdera4, komenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debaterakogalderak_9);
        setTitle("Eztabaidatu zure ikaskideekin");
        btnNext = findViewById(R.id.btnNext);
        btnPreviousGame = findViewById(R.id.btnPreviousGame);
        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);
        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        galdera1 = (TextView) findViewById(R.id.galdera1);
        galdera1.setVisibility(View.INVISIBLE);
        galdera2 = (TextView) findViewById(R.id.galdera2);
        galdera2.setVisibility(View.INVISIBLE);
        galdera3 = (TextView) findViewById(R.id.galdera3);
        galdera3.setVisibility(View.INVISIBLE);
        galdera4 = (TextView) findViewById(R.id.galdera4);
        galdera4.setVisibility(View.INVISIBLE);
        komenta = (TextView) findViewById(R.id.komenta);
        komenta.setVisibility(View.INVISIBLE);
        fotodebate = (ImageView) findViewById(R.id.fotodebate);
        fotodebate.setVisibility(View.INVISIBLE);


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
                    Intent i = new Intent(Debaterakogalderak_9.this,Komikia_8.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                break;
        }

        mp = MediaPlayer.create(getApplicationContext(), R.raw.a3_3);

        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation);
                btnNext.startAnimation(animacion);
                galdera1.startAnimation(animacion);
                galdera2.startAnimation(animacion);
                galdera3.startAnimation(animacion);
                galdera4.startAnimation(animacion);
                komenta.startAnimation(animacion);
                fotodebate.startAnimation(animacion);
                animacion.setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation arg0) {
                    }
                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }
                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        Log.d("mytag", "Al pulsar boton abrir camara en juego 3");
                        galdera1.setVisibility(View.VISIBLE);
                        galdera2.setVisibility(View.VISIBLE);
                        galdera3.setVisibility(View.VISIBLE);
                        galdera4.setVisibility(View.VISIBLE);
                        komenta.setVisibility(View.VISIBLE);
                        fotodebate.setVisibility(View.VISIBLE);

                        btnNext.setVisibility(View.VISIBLE);
                        btnNext.setEnabled(true);
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                switch (pag_anterior) {
                                    case 0:
                                        int i = 9;
                                        db = new MyOpenHelper(cont);
                                        db.ActualizarJuego_Id(i);
                                        db.close();

                                        Intent returnIntent = new Intent();
                                        returnIntent.putExtra("result", 1);
                                        setResult(Activity.RESULT_OK, returnIntent);
                                        finish();
                                }
                            }
                        })
                    ;}
                });
            }});
    }
}

