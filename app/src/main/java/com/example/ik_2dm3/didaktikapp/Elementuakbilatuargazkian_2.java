package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class Elementuakbilatuargazkian_2 extends AppCompatActivity {

    private MediaPlayer mp;
    private Button areaClick;
    private Context cont = this;
    private ImageButton btnNextGame, btnPreviousGame;
    static final int REQ_BTN = 0;
    private int pag_anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementuak_bilatu_argazkian_2);
        setTitle("Elementuak bilatu argazkian");
        btnNextGame = findViewById(R.id.btnNextGame);
        btnPreviousGame = findViewById(R.id.btnPreviousGame);

        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);

        btnNextGame.setEnabled(false);
        btnNextGame.setVisibility(View.INVISIBLE);

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.a1_3);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                areaClick.setEnabled(true);
                //mp.release();
            }
        });
        mp.start();

        areaClick = (Button) findViewById(R.id.areaClick);
        areaClick.setEnabled(false);
        areaClick.setBackgroundColor(Color.TRANSPARENT);
        areaClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp;
                mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",1);
                        setResult(Activity.RESULT_OK,returnIntent);

                        switch (pag_anterior){
                            case 0:
                                finish();
                                break;
                            case 1:
                                break;
                        }

                    }
                });
                areaClick.setBackgroundResource(R.drawable.button_bg_round);
                areaClick.setEnabled(false);
                Log.d("mytag", "LE HAS DADO Y SE REVELA SE SUPONE JODER");
            }});

        CargarSegunPag_anterior(pag_anterior);

    }


    public void CargarSegunPag_anterior(int u){
        switch(u){
            case 0:

                break;

            case 1:
                btnPreviousGame.setEnabled(true);
                btnPreviousGame.setVisibility(View.VISIBLE);
                btnPreviousGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Elementuakbilatuargazkian_2.this,Aukeratuargazkiegokia_1.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                btnNextGame.setEnabled(true);
                btnNextGame.setVisibility(View.VISIBLE);
                btnNextGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Elementuakbilatuargazkian_2.this,Argazkiaaztertu_3.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
                Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                        Toast.LENGTH_SHORT).show();
                //return true;
                mp.stop();
                finish();

            }
            return super.onKeyDown(keyCode, event);
    }
}
