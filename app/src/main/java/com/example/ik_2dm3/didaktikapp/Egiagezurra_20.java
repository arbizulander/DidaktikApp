package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Egiagezurra_20 extends AppCompatActivity {


    private MediaPlayer mp, mp2;
    private ImageButton btnPreviousGame,btnNext;
    private Context cont = this;
    private MyOpenHelper db;
    private int pag_anterior;
    private int puntos = 0;
    static final int REQ_BTN = 0;
    private Button respuesta1, respuesta2, respuesta3, respuesta4, respuesta5, respuesta6, respuesta7, respuesta8;
    static final int REQ_BTNATRAS = 12;

    BroadcastReceiver miBroadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("TAG", "Screen ON");
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("TAG", "Screen OFF");
                mp.stop();
                mp2.stop();

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egiagezurra_20);

        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        setTitle("Egia edo gezurra");
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
        respuesta7 = findViewById(R.id.respuesta7);
        respuesta8 = findViewById(R.id.respuesta8);


        respuesta1.setOnClickListener(v -> {
            RespuestaCorrecta(respuesta1,respuesta2);
        });
        respuesta2.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta2);
        });
        respuesta3.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta3);
        });
        respuesta4.setOnClickListener(v -> {
            RespuestaCorrecta(respuesta4,respuesta3);
        });
        respuesta5.setOnClickListener(v -> {
            RespuestaCorrecta(respuesta5,respuesta6);
        });
        respuesta6.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta6);
        });
        respuesta7.setOnClickListener(v -> {

            RespuestaCorrecta(respuesta7,respuesta8);
        });
        respuesta8.setOnClickListener(v -> {
            RespuestaIncorrecta(respuesta8);

        });


                ActivarBotones();

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        switch (pag_anterior) {
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
                    Intent i = new Intent(Egiagezurra_20.this, Informazioakuadroabete_19.class);
                    i.putExtra("pag_anterior", 1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }

    }

    public void RespuestaCorrecta(Button boton, Button boton2){
        boton.setBackgroundColor(Color.GREEN);
        SonidoRespuesta(R.raw.correct2, boton);
        boton.setEnabled(false);
        boton2.setEnabled(false);
        puntos++;
        comprobarVictoria();
    }
    public void RespuestaIncorrecta(Button boton){
        boton.setBackgroundColor(Color.RED);
        SonidoRespuesta(R.raw.fail2, boton);
    }
    public void SonidoRespuesta(int sonido, Button boton){
        mp2 = MediaPlayer.create(getApplicationContext(), sonido);
        mp2.start();
    }
    public void ActivarBotones(){
        respuesta1.setEnabled(true);
        respuesta2.setEnabled(true);
        respuesta3.setEnabled(true);
        respuesta4.setEnabled(true);
        respuesta5.setEnabled(true);
        respuesta6.setEnabled(true);
        respuesta7.setEnabled(true);
        respuesta8.setEnabled(true);
    }
    public void DesactivarBotones(){
        respuesta1.setEnabled(false);
        respuesta2.setEnabled(false);
        respuesta3.setEnabled(false);
        respuesta4.setEnabled(false);
        respuesta5.setEnabled(false);
        respuesta6.setEnabled(false);
        respuesta7.setEnabled(false);
        respuesta8.setEnabled(false);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (pag_anterior == 0){
                Intent i = new Intent();
                i.putExtra("keydown",REQ_BTNATRAS);
                setResult(RESULT_OK,i);
            }
            mp.stop();
            Log.d("mytag","Has ido atras");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    public void comprobarVictoria(){
        if(puntos == 4){
            switch (pag_anterior){
                case 0:
                    int i = 20;
                    db=new MyOpenHelper(cont);
                    db.ActualizarJuego_Id(i);
                    db.close();
                    Log.d("mytag","AL ACABAR EL JUEGO FINALIZO Y VUELVO AL LISTADO PARA CARGAR SIGUIENTE...");
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",1);
                    setResult(Activity.RESULT_OK,returnIntent);
                    mp2.stop();
                    finish();
                    break;
                case 1:
                    Log.d("mytag","El juego se ha acabado y se inicio desde lista");
                    break;
            }
        }
    }
}
