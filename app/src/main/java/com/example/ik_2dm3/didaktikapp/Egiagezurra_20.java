package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Egiagezurra_20 extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egiagezurra_20);

        Button egia_1 = (Button) findViewById(R.id.egia_1);
        Button egia_2 = (Button) findViewById(R.id.egia_2);
        Button egia_3 = (Button) findViewById(R.id.egia_3);
        Button egia_4 = (Button) findViewById(R.id.egia_4);

        Button gezurra_1 = (Button) findViewById(R.id.gezurra_1);
        Button gezurra_2 = (Button) findViewById(R.id.gezurra_2);
        Button gezurra_3 = (Button) findViewById(R.id.gezurra_3);
        Button gezurra_4 = (Button) findViewById(R.id.gezurra_4);

        /*ºººººººººººººººººººººººMEDIAPLAYERººººººººººººººººººººº*/
        mp = MediaPlayer.create(this, R.raw.a7_2);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                egia_1.setEnabled(true);
                egia_2.setEnabled(true);
                egia_3.setEnabled(true);
                egia_4.setEnabled(true);

                gezurra_1.setEnabled(true);
                gezurra_2.setEnabled(true);
                gezurra_3.setEnabled(true);
                gezurra_3.setEnabled(true);



                Log.d("mytag","Dentro del onCompletion");

            }
        });
        mp.start();

        egia_1.setEnabled(false);
        egia_2.setEnabled(false);
        egia_3.setEnabled(false);
        egia_4.setEnabled(false);

        gezurra_1.setEnabled(false);
        gezurra_2.setEnabled(false);
        gezurra_3.setEnabled(false);
        gezurra_4.setEnabled(false);

        egia_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp1;
                mp1 = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                mp1.start();
            }
        });
    }
}
