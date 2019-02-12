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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnHasi);

        btnStart.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this,menu_main.class);
            Log.d("mytag", "... ABRIENDO INTENT...");
            startActivityForResult(intent,REQ_BTN);
        });
    }

}
