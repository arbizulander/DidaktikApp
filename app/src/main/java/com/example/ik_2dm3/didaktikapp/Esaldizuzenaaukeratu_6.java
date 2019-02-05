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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
    private ImageButton btnNext, btnPreviousGame;
    static final int REQ_BTN = 0;
    private int pag_anterior;

    private MyOpenHelper db;
    private Context cont = this;
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
        setContentView(R.layout.activity_esaldizuzenaaukeratu_6);

        setTitle("Esaldi zuzena aukeratu");

        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        btnNext = findViewById(R.id.btnNextGame);
        btnPreviousGame = findViewById(R.id.btnPreviousGame);

        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);

        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

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
        galderak.add(0, "Aukeratu esaldi egokia hemendik sakatu");
        galderak.add("Bizkaiko zubia kolorez aldatu zen herritarren iritziz kolorea itsusia zelako");
        /*EGOKIA*/galderak.add("Kolorez aldatu zen beltzak erradiazioa xurgatzen zuelako eta beroaren ondorioz bere egitura dilatatzen zelako");
        galderak.add("Kolorez aldatu zen urtero margozten zelako eta kolore beltza garestia zelako");
        galderak.add("Kolorez aldatu zen guda zibilean bota zutenean kolore horretakoa zenez, oroitzapen txarrak ekartzen zituelako");
        galderak.add("Kolorez aldatu zen UNESCOk gizadiaren ondarea deklara zezan beste kolore batekoa izan behar zelako");

        //Style the spinner
        ArrayAdapter<String> datataAdapter;
        datataAdapter = new ArrayAdapter<>(this,R.layout.spinner_item, galderak);

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
                if(parent.getItemAtPosition(position).equals("Aukeratu esaldi egokia hemendik sakatu")){

                    //do nothing
                }else if(parent.getItemAtPosition(position).equals("Kolorez aldatu zen beltzak erradiazioa xurgatzen zuelako eta beroaren ondorioz bere egitura dilatatzen zelako")){

                    spinner.setEnabled(false);
                    //on selecting a spinner item
                    String item = parent.getItemAtPosition(position).toString();
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Oso ondo!!", Toast.LENGTH_SHORT);
                    toast.show();
                    Reproducir_cancion();
                    //show selected spinner item
                    //Toast.makeText(parent.getContext(),"Irabazi duzu:" +item, Toast.LENGTH_SHORT).show();
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.GREEN);

                    //anything else you want to do on item selection do  here
                }else{
                    String item = parent.getItemAtPosition(position).toString();

                    ((TextView) parent.getChildAt(0)).setTextColor(Color.RED);
                    //show selected spinner item
                    //Toast.makeText(parent.getContext(),"Galdu duzu: "  +item, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Todo Auto-generated method stub
            }
        });

        CargarSegunPag_anterior(pag_anterior);
    }

    public void CargarSegunPag_anterior(int u) {
        switch (u) {
            case 0:

                break;

            case 1:
                Log.d("mytag", "case: 1");
                btnPreviousGame.setEnabled(true);
                btnPreviousGame.setVisibility(View.VISIBLE);
                btnPreviousGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Esaldizuzenaaukeratu_6.this, Galderenerantzunaaukeratu_5.class);
                    i.putExtra("pag_anterior", 1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                btnNext.setEnabled(true);
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setOnClickListener(v -> {
                    mp.stop();
                    finish();
                });
                break;
        }
    }

    public void Reproducir_cancion() {
        mp = MediaPlayer.create(this, R.raw.correct);
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {


                switch (pag_anterior) {
                    case 0:
                        int i = 6;
                        db = new MyOpenHelper(cont);
                        db.ActualizarJuego_Id(i);
                        db.close();

                        Log.d("mytag", "AL ACABAR EL JUEGO FINALIDO Y VUELVO AL LISTADO PARA CARGAR SIGUIENTE...");
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result", 1);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                        break;
                    case 1:

                        break;
                }

            }
        });
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


}
