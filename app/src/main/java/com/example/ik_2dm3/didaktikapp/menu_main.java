package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu_main extends AppCompatActivity {
    Button btnHome;
    Button btnIbilbidea;
    Button btnGaleria;

    static final int REQ_BTN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);


        btnHome = (Button) findViewById(R.id.btnHome);
        btnIbilbidea = (Button) findViewById(R.id.btnIbilbidea);
        btnGaleria = (Button) findViewById(R.id.btnGaleria);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(menu_main.this,MainActivity.class);



                startActivityForResult(intent, REQ_BTN);

            }
        });

        btnIbilbidea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(menu_main.this,MapsActivity.class);



                startActivityForResult(intent, REQ_BTN);

            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(menu_main.this,galery.class);



                startActivityForResult(intent, REQ_BTN);

            }
        });
    }
}
