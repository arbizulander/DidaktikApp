package com.example.ik_2dm3.didaktikapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class Argazkia_10 extends AppCompatActivity {

    private ImageButton imagen1, imagen2,btnNext;
    private Context cont = this;
    private MyOpenHelper db;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkia_10);
        setTitle("Argazkiak sakatu");

        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        ImageButton imagen1 = (ImageButton) findViewById(R.id.imagen1);

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        switch (pag_anterior){
            case 0:
                break;
            case 1:

                btnNext.setEnabled(true);
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setOnClickListener(v -> {

                    Intent i = new Intent(Argazkia_10.this,Erantzunzuzenaaukeratu_11.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                break;
        }



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
            if (pag_anterior == 0){
                Intent i = new Intent();
                i.putExtra("keydown",REQ_BTNATRAS);
                setResult(RESULT_OK,i);
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}