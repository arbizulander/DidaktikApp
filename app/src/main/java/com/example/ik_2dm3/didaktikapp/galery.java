package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class galery extends AppCompatActivity {
    Button btnHome;


    static final int REQ_BTN = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        btnHome = (Button) findViewById(R.id.btnHome);


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(galery.this,MainActivity.class);



                startActivityForResult(intent, REQ_BTN);

            }
        });
    }
}
