package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
    static final int REQ_BTNATRAS = 12;
    private ImageView fotodebate;
    private TextView galdera1, galdera2, galdera3, galdera4, komenta;

    BroadcastReceiver miBroadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("TAG", "Screen ON");
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("TAG", "Screen OFF");
                mp.stop();

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debaterakogalderak_9);
        setTitle("Debaterako galderak");

        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

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
                Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation);

            if (pag_anterior == 0){
                btnNext.startAnimation(animacion);
            }

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

                        if (pag_anterior == 0){
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
                            });
                        }

                    ;}
                });
            //}}
        //);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            if (pag_anterior == 0){
                Intent i = new Intent();
                i.putExtra("keydown",REQ_BTNATRAS);
                setResult(RESULT_OK,i);
            }
            mp.stop();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

