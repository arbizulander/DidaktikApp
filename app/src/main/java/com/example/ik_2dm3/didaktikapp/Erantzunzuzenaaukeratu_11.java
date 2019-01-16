package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Erantzunzuzenaaukeratu_11 extends AppCompatActivity {

    private MediaPlayer mp, mp2;
    private ImageButton btnNext, btnPreviousGame;
    private Context cont = this;
    private MyOpenHelper db;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    private Button respuesta1, respuesta2, respuesta3, respuesta4, respuesta5, respuesta6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erantzunzuzenaaukeratu_11);
        setTitle("Entzun eta aukeratu erantzun egokia");
        btnPreviousGame = findViewById(R.id.btnPreviousGame);
        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        respuesta1 = findViewById(R.id.respuesta1);
        respuesta2 = findViewById(R.id.respuesta2);
        respuesta3 = findViewById(R.id.respuesta3);
        respuesta4 = findViewById(R.id.respuesta4);
        respuesta5 = findViewById(R.id.respuesta5);
        respuesta6 = findViewById(R.id.respuesta6);
        DesactivarBotones();

        respuesta1.setOnClickListener(v -> {
            RespuestaCorrecta(respuesta1);
        });
        respuesta2.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta2);
        });
        respuesta3.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta3);
        });
        respuesta4.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta4);
        });
        respuesta5.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta5);
        });
        respuesta6.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta6);
        });

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
                    Intent i = new Intent(Erantzunzuzenaaukeratu_11.this,Argazkiaktaulansailkatu_12.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                btnPreviousGame.setEnabled(true);
                btnPreviousGame.setVisibility(View.VISIBLE);
                btnPreviousGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Erantzunzuzenaaukeratu_11.this,Argazkia_10.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                break;
        }
        mp = MediaPlayer.create(getApplicationContext(), R.raw.a4_2);

        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ActivarBotones();
            }
        });
    }
    public void RespuestaCorrecta(Button boton){
        boton.setBackgroundColor(Color.GREEN);
        SonidoRespuesta(R.raw.correct2);
        DesactivarBotones();

    }
    public void RespuestaIncorrecta(Button boton){
        boton.setBackgroundColor(Color.RED);
        SonidoRespuesta(R.raw.fail2);
    }
    public void SonidoRespuesta(int sonido){
        mp2 = MediaPlayer.create(getApplicationContext(), sonido);
        mp2.start();
        DesactivarBotones();
        mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(sonido == R.raw.correct2){
                    switch (pag_anterior){
                        case 0:
                            int i = 11;
                            db=new MyOpenHelper(cont);
                            db.ActualizarJuego_Id(i);
                            db.close();
                            Log.d("mytag","AL ACABAR EL JUEGO FINALIZO Y VUELVO AL LISTADO PARA CARGAR SIGUIENTE...");
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("result",1);
                            setResult(Activity.RESULT_OK,returnIntent);
                            finish();
                            break;
                        case 1:
                            Log.d("mytag","El juego se ha acabado y se inicio desde lista");
                            break;
                    }
                }
                else{ActivarBotones();}
            }
        });
    }
    public void ActivarBotones(){
        respuesta1.setEnabled(true);
        respuesta2.setEnabled(true);
        respuesta3.setEnabled(true);
        respuesta4.setEnabled(true);
        respuesta5.setEnabled(true);
        respuesta6.setEnabled(true);
    }
    public void DesactivarBotones(){
        respuesta1.setEnabled(false);
        respuesta2.setEnabled(false);
        respuesta3.setEnabled(false);
        respuesta4.setEnabled(false);
        respuesta5.setEnabled(false);
        respuesta6.setEnabled(false);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mp.stop();
            Log.d("mytag","Has ido atras");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
