package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    //REQ
    private static final int REQ_BTN = 0;
    private boolean blnCargado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*if (!blnCargado) {
            setTheme(R.style.SplashTheme);
            blnCargado = true;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnHasi);

        btnStart.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this,menu_main.class);
            Log.d("mytag", "... ABRIENDO INTENT...");
            startActivityForResult(intent,REQ_BTN);
            //AbrirLayout thread = new AbrirLayout(intent);
            //thread.start();
            //startActivityForResult(intent, REQ_BTN);
        });
    }

    /*class AbrirLayout extends Thread {
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
    }*/
}
