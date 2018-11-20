package com.example.ik_2dm3.didaktikapp;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ElementuakBilatuArgazkian_2 extends AppCompatActivity {

    private MediaPlayer mp;
    private Button areaClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementuak_bilatu_argazkian_2);
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.a1_3);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                areaClick.setEnabled(true);
                mp.release();
            }
        });
        mp.start();

            areaClick = (Button) findViewById(R.id.areaClick);
        areaClick.setEnabled(false);
        areaClick.setBackgroundColor(Color.TRANSPARENT);
        areaClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                mp.start();
                areaClick.setBackgroundResource(R.drawable.button_bg_round);
                areaClick.setEnabled(false);
                Log.d("mytag", "LE HAS DADO Y SE REVELA SE SUPONE JODER");
            }});
    }

}
