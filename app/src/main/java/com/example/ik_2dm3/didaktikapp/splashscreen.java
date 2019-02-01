package com.example.ik_2dm3.didaktikapp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.support.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

public class splashscreen extends AppCompatActivity {

    private ProgressBar mprogressBar;
    private static final int REQ_BTN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mprogressBar = (ProgressBar) findViewById(R.id.progressBar2);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(8000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(splashscreen.this);
                //Intent intent = new Intent(splashscreen.this, MainActivity.class);
                //startActivity(intent, options.toBundle());
                Intent intent = new Intent(splashscreen.this,MainActivity.class);
                AbrirLayout thread = new AbrirLayout(intent);
                thread.start();
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    class AbrirLayout extends Thread {
        private Intent i;

        public AbrirLayout(Intent i) {
            this.i = i;
        }

        @Override
        public void run() {
            runOnUiThread(() -> {
                Log.d("mytag", "... ABRIENDO INTENT...");
                startActivityForResult(i,REQ_BTN);
            });
        }
    }
}
