package com.example.ik_2dm3.didaktikapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Argazkiaaztertu_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MediaPlayer mp;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkiaaztertu_3);

        mp = MediaPlayer.create(this, R.raw.a1_4);
        mp.start();


    }
}
