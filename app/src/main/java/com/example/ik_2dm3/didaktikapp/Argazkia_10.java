package com.example.ik_2dm3.didaktikapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class Argazkia_10 extends AppCompatActivity {

    private ImageButton imagen1, imagen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkia_10);

        ImageButton imagen1 = (ImageButton) findViewById(R.id.imagen1);



        imagen1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:

                        ImageButton imagen1 = (ImageButton) findViewById(R.id.imagen1);
                        imagen1.setVisibility(View.INVISIBLE);

                        ImageButton imagen2 = (ImageButton) findViewById(R.id.imagen2);

                        imagen2.setVisibility(View.VISIBLE);


                        break;

                    case MotionEvent.ACTION_UP:

                        ImageButton imagen3 = (ImageButton) findViewById(R.id.imagen1);
                        imagen3.setVisibility(View.VISIBLE);

                        ImageButton imagen4 = (ImageButton) findViewById(R.id.imagen2);

                        imagen4.setVisibility(View.INVISIBLE);


                        break;
                }


                return false;
            }
        });

        
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
                /*Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                        Toast.LENGTH_SHORT).show();*/

        }
        return super.onKeyDown(keyCode, event);
    }
}