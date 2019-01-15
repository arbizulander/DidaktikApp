package com.example.ik_2dm3.didaktikapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Argazkiaktaulansailkatu_12 extends AppCompatActivity {

    private MediaPlayer mp, mp2;
    private ImageButton btnNext, btnPreviousGame, gizaetxea, sutea, musika, zinema, otoitza;
    private ImageView respuesta1, respuesta2, respuesta3, respuesta4, respuesta5;
    private Context cont = this;
    private MyOpenHelper db;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    private int contador = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkiaktaulansailkatu_12);
        setTitle("Egin klik agindu zuzena");
        btnPreviousGame = findViewById(R.id.btnPreviousGame);
        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        respuesta1 =(ImageView)findViewById(R.id.respuesta1);
        respuesta2 =(ImageView)findViewById(R.id.respuesta2);
        respuesta3 =(ImageView)findViewById(R.id.respuesta3);
        respuesta4 =(ImageView)findViewById(R.id.respuesta4);
        respuesta5 =(ImageView)findViewById(R.id.respuesta5);
        gizaetxea = findViewById(R.id.gizaetxea);
        sutea = findViewById(R.id.sutea);
        musika = findViewById(R.id.musika);
        zinema = findViewById(R.id.zinema);
        otoitza = findViewById(R.id.otoitza);
        gizaetxea.setOnClickListener(v -> {
            respuesta1.setImageResource(R.drawable.gizaetxea);
            RespuestaCorrecta(0);

        });
        sutea.setOnClickListener(v -> {
            respuesta3.setImageResource(R.drawable.sutea);
            RespuestaCorrecta(2);

        });
        musika.setOnClickListener(v -> {
            respuesta5.setImageResource(R.drawable.musika);
            RespuestaCorrecta(4);

        });
        zinema.setOnClickListener(v -> {
            respuesta2.setImageResource(R.drawable.zinema);
            RespuestaCorrecta(1);

        });
        otoitza.setOnClickListener(v -> {
            respuesta4.setImageResource(R.drawable.otoitza);
            RespuestaCorrecta(3);

        });
        DesactivarBotones();
        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        switch (pag_anterior){
            case 0:
                break;
            case 1:
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
                    Intent i = new Intent(Argazkiaktaulansailkatu_12.this,Erantzunzuzenaaukeratu_11.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                break;
        }
        mp = MediaPlayer.create(getApplicationContext(), R.raw.a4_3);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ActivarBotones();
            }
        });
    }
    public void RespuestaCorrecta(int numero){
        if(contador == numero){
            contador++;
            if(contador == 5){
                //Fin de juego y victoria
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                mp2.start();
                mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //Siguiente tarea
                        Log.d("mytag","Juego completado");
                    }
                });
                DesactivarBotones();
            }
        }
        else{
            Resetjuego();
        }
    }
    public void ActivarBotones(){
        gizaetxea.setEnabled(true);
        sutea.setEnabled(true);
        musika.setEnabled(true);
        zinema.setEnabled(true);
        otoitza.setEnabled(true);
    }
    public void DesactivarBotones(){
        gizaetxea.setEnabled(false);
        sutea.setEnabled(false);
        musika.setEnabled(false);
        zinema.setEnabled(false);
        otoitza.setEnabled(false);
    }
    public void Resetjuego(){
        contador = 0;
        respuesta1.setImageDrawable(null);
        respuesta2.setImageDrawable(null);
        respuesta3.setImageDrawable(null);
        respuesta4.setImageDrawable(null);
        respuesta5.setImageDrawable(null);

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
