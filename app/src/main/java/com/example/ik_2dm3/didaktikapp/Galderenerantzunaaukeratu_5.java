package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Galdera Egokia aukeratu");
        





        setContentView(R.layout.activity_galderenerantzunaaukeratu_5);
        areaClick = (Button) findViewById(R.id.areaClick);
        areaClick.setBackgroundColor(Color.TRANSPARENT);


        mp = MediaPlayer.create(this, R.raw.a2_2);




        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //spinner.setEnabled(false);
                spinner.setEnabled(true);
                Log.d("mitag","Dentro del onCompletion");
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
        datataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, categorias);

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

                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();


                    //show selected spinner item
                    Toast.makeText(parent.getContext(),"Irabazi duzu:" +item, Toast.LENGTH_SHORT).show();

                    Reproducir_cancion();




                    //anything else you want to do on item selection do  here
                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    //Log.d("mitag","" + position);

                    //show selected spinner item
                    Toast.makeText(parent.getContext(),"Galdu duzu: "  +item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // Todo Auto-generated method stub

            }
        });






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
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",1);
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
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
            Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();
            //return true;
            mp.stop();
        }
        return super.onKeyDown(keyCode, event);
    }

}//fin
