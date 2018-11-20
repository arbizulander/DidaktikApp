package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnjuego;
    static final int REQ_BTN = 0;

    //Controlador de bases de datos
    //private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciamos el controlador de la base de datos
        //db=new MyOpenHelper(this);

        //cogiendo id del boton
        btnStart = findViewById(R.id.btnHasi);
        btnjuego = findViewById(R.id.button2);
        //accion al pulsar el boton
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,menu_main.class);
                startActivityForResult(intent, REQ_BTN);
            }
        });

        btnjuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Desberdintasunabilatu_4.class);
                startActivity(intent);
            }
        });
    }
}
