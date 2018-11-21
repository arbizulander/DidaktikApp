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
import android.widget.Toast;

import pl.droidsonroids.gif.GifImageView;

public class Elementuakbilatuargazkian_2 extends AppCompatActivity {

    private MediaPlayer mp;
    private Button areaClick;
    private Context cont = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementuak_bilatu_argazkian_2);
        setTitle("Elementuak bilatu argazkian");

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
                        finish();
                    }
                });
                areaClick.setBackgroundResource(R.drawable.button_bg_round);
                areaClick.setEnabled(false);
                Log.d("mytag", "LE HAS DADO Y SE REVELA SE SUPONE JODER");
            }});
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
                Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                        Toast.LENGTH_SHORT).show();
                //return true;
                mp.stop();
            }
            return super.onKeyDown(keyCode, event);
    }
}
