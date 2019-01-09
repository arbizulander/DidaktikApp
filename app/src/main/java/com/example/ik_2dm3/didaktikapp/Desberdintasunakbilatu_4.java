package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class Desberdintasunakbilatu_4 extends AppCompatActivity {


    private ImageButton Hurrengoa;

    static final int REQ_TEXT = 0;
    private ImageButton btnNextGame, btnPreviousGame;
    static final int REQ_BTN = 0;
    private int pag_anterior;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desberdintasuna_bilatu_4);

        mp = MediaPlayer.create(this, R.raw.a2_1);
        mp.start();

        btnNextGame = findViewById(R.id.btnNextGame);
        //btnPreviousGame = findViewById(R.id.btnPreviousGame);

        //btnPreviousGame.setEnabled(false);
        //btnPreviousGame.setVisibility(View.INVISIBLE);

        btnNextGame.setEnabled(false);
        btnNextGame.setVisibility(View.INVISIBLE);

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        //Hurrengoa = findViewById(R.id.hurrengoa);

        /*Hurrengoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), Desberdintasunabilatu_4_argazki1.class);

                startActivityForResult(i, REQ_TEXT);
            }
        });*/
        CargarSegunPag_anterior(pag_anterior);
    }

    public void CargarSegunPag_anterior(int u){
        switch(u){
            case 0:

                break;

            case 1:
                /*btnPreviousGame.setEnabled(true);
                btnPreviousGame.setVisibility(View.VISIBLE);
                btnPreviousGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Elementuakbilatuargazkian_2.this,Aukeratuargazkiegokia_1.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });*/

                btnNextGame.setEnabled(true);
                btnNextGame.setVisibility(View.VISIBLE);
                btnNextGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Desberdintasunakbilatu_4.this,Galderenerantzunaaukeratu_5.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }
    }
}
