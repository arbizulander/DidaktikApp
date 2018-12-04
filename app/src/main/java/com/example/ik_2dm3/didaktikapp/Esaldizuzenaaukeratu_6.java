package com.example.ik_2dm3.didaktikapp;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Esaldizuzenaaukeratu_6 extends AppCompatActivity {

    private Spinner spinner;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esaldizuzenaaukeratu_6);



        mp = MediaPlayer.create(this, R.raw.a2_3);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //spinner.setEnabled(false);
                spinner.setEnabled(true);

            }
        });
        mp.start();



        spinner = findViewById(R.id.spinner);
        spinner.setEnabled(false);

        List<String> galderak = new ArrayList<>();
        galderak.add(0, "Aukeratu galdera egokia");
        galderak.add("Bizkaiko zubia kolorez aldatu zen herritarren iritziz kolorea itsusia zelako");
        /*EGOKIA*/galderak.add("Kolorez aldatu zen beltzak erradiazioa xurgatzen zuelako eta beroaren ondorioz bere egitura dilatatzen zelako");
        galderak.add("Kolorez aldatu zen urtero margozten zelako eta kolore beltza garestia zelako");
        galderak.add("Kolorez aldatu zen guda zibilean bota zutenean kolore horretakoa zenez, oroitzapen txarrak ekartzen zituelako");
        galderak.add("Kolorez aldatu zen UNESCOk gizadiaren ondarea deklara zezan beste kolore batekoa izan behar zelako");

        //Style the spinner

        ArrayAdapter<String> datataAdapter;
        datataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, galderak);

        //Dropdown layout style
        datataAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);



        //attaching data adapter to spinner
        spinner.setAdapter(datataAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


/*
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
                    ((TextView) parent.getChildAt(0)).setTextSize(10);


*/
                if(parent.getItemAtPosition(position).equals("Aukeratu galdera egokia")){


                    //do nothing
                }else if(parent.getItemAtPosition(position).equals("Kolorez aldatu zen beltzak erradiazioa xurgatzen zuelako eta beroaren ondorioz bere egitura dilatatzen zelako")){

                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();

                    Reproducir_cancion();
                    //show selected spinner item
                    Toast.makeText(parent.getContext(),"Irabazi duzu:" +item, Toast.LENGTH_SHORT).show();
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.GREEN);

                    //anything else you want to do on item selection do  here
                }else{
                    String item = parent.getItemAtPosition(position).toString();

                    ((TextView) parent.getChildAt(0)).setTextColor(Color.RED);
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

    public void Reproducir_cancion() {

        mp = MediaPlayer.create(this, R.raw.correct);
        mp.start();

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


}