package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btnjuego;

    //REQ
    private static final int REQ_BTN = 0;

    //Controlador de bases de datos
    //private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciamos el controlador de la base de datos
        //db=new MyOpenHelper(this);

        //cogiendo id del boton
        //botones
        Button btnStart = findViewById(R.id.btnHasi);
        btnjuego = findViewById(R.id.button2);
        //accion al pulsar el boton
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,menu_main.class);
            AbrirLayout thread = new AbrirLayout(intent);
            thread.start();
            //startActivityForResult(intent, REQ_BTN);
        });

        btnjuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Galderenerantzunaaukeratu_5.class);
                //Intent intent = new Intent(MainActivity.this,Aukeratuargazkiegokia_1.class);
                AbrirLayout thread = new AbrirLayout(intent);
                thread.start();
                //startActivity(intent);
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
            runOnUiThread(new Runnable() {
                @Override public void run() {
                    Log.d("mytag", "... ABRIENDO INTENT...");
                   startActivityForResult(i,REQ_BTN);
                }
            });
        }
    }
}
