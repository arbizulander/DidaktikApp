package com.example.ik_2dm3.didaktikapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

public class menu_main extends AppCompatActivity {
    //Button btnHome;
    Button btnIbilbidea;
    Button btnGaleria;
    Button btnOndareak;

    static final int REQ_BTN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        setTitle("Menua");

        //btnHome = (Button) findViewById(R.id.btnHome);
        btnIbilbidea = findViewById(R.id.btnIbilbidea);
        btnGaleria = findViewById(R.id.btnGaleria);
        btnOndareak = findViewById(R.id.btnOndareak);

        /*btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(menu_main.this,MainActivity.class);



                startActivityForResult(intent, REQ_BTN);

            }
        });*/

        btnIbilbidea.setOnClickListener(v -> {


            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(menu_main.this,MapsActivity.class);
            startActivity(intent, options.toBundle());

            //startActivityForResult(intent, REQ_BTN);
        });

        btnGaleria.setOnClickListener(v -> {
            Intent intent = new Intent(menu_main.this,galery.class);
            startActivityForResult(intent, REQ_BTN);
        });

        btnOndareak.setOnClickListener(v -> {
            Intent intent = new Intent(menu_main.this,list.class);
            startActivityForResult(intent, REQ_BTN);
        });


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();
            //return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
