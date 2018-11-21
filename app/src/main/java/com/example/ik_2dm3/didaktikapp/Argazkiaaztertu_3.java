package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class Argazkiaaztertu_3 extends AppCompatActivity {

    private MediaPlayer mp;
    private ImageButton btnNext;
    private Context cont = this;
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkiaaztertu_3);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        mp = MediaPlayer.create(this, R.raw.a1_4);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation);
                btnNext.startAnimation(animacion);
               animacion.setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation arg0) {

                    }
                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }
                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        btnNext.setVisibility(View.VISIBLE);
                        btnNext.setEnabled(true);
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int i = 3;
                                db=new MyOpenHelper(cont);
                                db.ActualizarJuego_Id(i);
                                db.close();

                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result",1);
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();
                            }
                        });
                    }
                });
            }
        });

    }
}
