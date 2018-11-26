package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Desberdintasunabilatu_4 extends AppCompatActivity {


    private ImageButton Hurrengoa;

    static final int REQ_TEXT = 0;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desberdintasuna_bilatu_4);

        mp = MediaPlayer.create(this, R.raw.a2_1);
        mp.start();

        Hurrengoa = findViewById(R.id.hurrengoa);



        Hurrengoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), Desberdintasunabilatu_4_argazki1.class);

                startActivityForResult(i, REQ_TEXT);


            }
        });
    }
}
