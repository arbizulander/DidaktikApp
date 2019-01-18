package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Paragrafoabetehitzekin_15 extends AppCompatActivity {

    private EditText texto, texto1, texto2 ,texto3, texto4, texto5, texto6;
    private MediaPlayer mp;
    private ImageButton btnNext;
    private Context cont = this;
    private MyOpenHelper db;
    private int puntos = 0;
    private int pag_anterior;
    static final int REQ_BTN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paragrafoabetehitzekin_15);
        setTitle("Paragrafo bete");
        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.a6_1);
        mp.start();

        texto = (EditText)findViewById(R.id.texto);
        texto1 = (EditText)findViewById(R.id.texto1);
        texto2 = (EditText)findViewById(R.id.texto2);
        texto3 = (EditText)findViewById(R.id.texto3);
        texto4 = (EditText)findViewById(R.id.texto4);
        texto5 = (EditText)findViewById(R.id.texto5);
        texto6 = (EditText)findViewById(R.id.texto6);
        texto.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        texto1.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        texto2.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        texto3.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        texto4.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        texto5.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        texto6.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
        texto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
            Comprobartexto(texto,"xii");
            }
        });
        texto1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                Comprobartexto(texto1,"andra mariko");
            }
        });
        texto2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                Comprobartexto(texto2,"algorta");
            }
        });
        texto3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                Comprobartexto(texto3,"areeta");
            }
        });
        texto4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                Comprobartexto(texto4,"neguri");
            }
        });
        texto5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                Comprobartexto(texto5,"negu");
            }
        });
        texto6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                Comprobartexto(texto6,"hiri");
            }
        });
        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        switch (pag_anterior){
            case 0:
                break;
            case 1:
                //btnNext.set
                btnNext.setEnabled(true);
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Paragrafoabetehitzekin_15.this,Informazioakuadroabete_16.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
        }
    }
    public void Comprobartexto(EditText campo, String respuesta){
        if(campo.getText().toString().toLowerCase().equals(respuesta)){
            campo.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            campo.setInputType(InputType.TYPE_NULL);
            campo.setKeyListener(null);
            puntos++;
            campo.setFocusable(false);
            if(puntos == 7){
                mp = MediaPlayer.create(getApplicationContext(), R.raw.correct2);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        //Pasar de juego
                        switch (pag_anterior){
                            case 0:
                                int i = 15;
                                db=new MyOpenHelper(cont);
                                db.ActualizarJuego_Id(i);
                                db.close();

                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result",1);
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();
                                break;

                            case 1:
                                break;
                        }
                    }
                });
            }
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mp.stop();
            Log.d("mytag","Has ido atras");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
