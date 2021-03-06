package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.IDN;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class Galderenerantzunaaukeratu_5 extends AppCompatActivity {

    private Spinner spinner;
    private MediaPlayer mp;
    private Context cont = this;
    private Button areaClick;

    private ImageButton btnNext, btnPreviousGame;
    private MyOpenHelper db;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;

    BroadcastReceiver miBroadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("TAG", "Screen ON");
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("TAG", "Screen OFF");
                mp.stop();

        }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galderenerantzunaaukeratu_5);
        setTitle("Galdera egokia aukeratu");
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        btnNext = findViewById(R.id.btnNextGame);
        btnPreviousGame = findViewById(R.id.btnPreviousGame);

        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);

        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        Log.d("mytag", "ESTOY EN EL JUEGO 2");

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        //setContentView(R.layout.activity_galderenerantzunaaukeratu_5);
        areaClick = (Button) findViewById(R.id.areaClick);
        areaClick.setBackgroundColor(Color.TRANSPARENT);


        mp = MediaPlayer.create(this, R.raw.a2_2);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //spinner.setEnabled(false);
                spinner.setEnabled(true);
                Log.d("mytag","Dentro del onCompletion");

            }
        });
        mp.start();

        spinner = findViewById(R.id.spinner);
        spinner.setEnabled(false);

        List<String> categorias = new ArrayList<>();
        categorias.add(0, "Kolorea aukeratu");
        categorias.add("Verdea");
        categorias.add("Gorria");
        categorias.add("Morea");
        categorias.add("Horia");
        categorias.add("Beltza");

        //Style the spinner
        ArrayAdapter<String> datataAdapter;
        datataAdapter = new ArrayAdapter<>(this,R.layout.spinner_item, categorias);

        //Dropdown layout style
        datataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        //attaching data adapter to spinner
        spinner.setAdapter(datataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Kolorea aukeratu")){

                    //do nothing
                }else if(parent.getItemAtPosition(position).equals("Beltza")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Oso ondo!!", Toast.LENGTH_SHORT);
                    toast.show();
                    spinner.setEnabled(false);
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.GREEN);
                    //spinner.
                    //show selected spinner item
                    Reproducir_cancion();
                    //anything else you want to do on item selection do  here
                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.RED);
                    //show selected spinner item
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // Todo Auto-generated method stub

            }
        });

        CargarSegunPag_anterior(pag_anterior);
    }

    public void CargarSegunPag_anterior(int u){
        switch(u){
            case 0:

                break;

            case 1:
                Log.d("mytag","case: 1");
                btnPreviousGame.setEnabled(true);
                btnPreviousGame.setVisibility(View.VISIBLE);
                btnPreviousGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Galderenerantzunaaukeratu_5.this,Desberdintasunakbilatu_4.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                btnNext.setEnabled(true);
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Galderenerantzunaaukeratu_5.this,Esaldizuzenaaukeratu_6.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }
    }

    public void Reproducir_cancion (){

        mp = MediaPlayer.create(this, R.raw.correct);
        mp.start();

        mp = MediaPlayer.create(getApplicationContext(), R.raw.a2_2_1);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                areaClick.setEnabled(true);
                //mp.release();
            }
        });
        mp.start();


        areaClick.setEnabled(false);
        areaClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MediaPlayer mp;
                mp = MediaPlayer.create(getApplicationContext(), R.raw.correct);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {


                        switch (pag_anterior){
                            case 0:
                                int i = 5;
                                db=new MyOpenHelper(cont);
                                db.ActualizarJuego_Id(i);
                                db.close();

                                Log.d("mytag","AL ACABAR EL JUEGO FINALIDO Y VUELVO AL LISTADO PARA CARGAR SIGUIENTE...");
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
                areaClick.setBackgroundResource(R.drawable.button_bg_round);
                areaClick.setEnabled(false);
                Log.d("mytag", "LE HAS DADO Y SE REVELA SE SUPONE JODER");
            }});
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            if (pag_anterior == 0){
                Intent i = new Intent();
                i.putExtra("keydown",REQ_BTNATRAS);
                setResult(RESULT_OK,i);
            }
            mp.stop();
        }
        return super.onKeyDown(keyCode, event);
    }

}//fin
