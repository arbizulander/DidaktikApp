package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Argazkiaaztertu_7 extends AppCompatActivity {

    private MediaPlayer mp;
    private ImageButton btnNextGame;
    static final int REQ_BTN = 0;
    private int pag_anterior;
    static final int REQ_BTNATRAS = 12;

    //conexion BD
    private MyOpenHelper db;
    private Context cont = this;

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
        setContentView(R.layout.activity_argazkiaaztertu_7);

        setTitle("Argazkia aukeratu");
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        btnNextGame = findViewById(R.id.btnNextGame);

        btnNextGame.setEnabled(false);
        btnNextGame.setVisibility(View.INVISIBLE);

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        mp = MediaPlayer.create(this, R.raw.a3_1);
        mp.start();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                switch (pag_anterior){
                    case 0:
                        btnNextGame.setEnabled(true);
                        btnNextGame.setVisibility(View.VISIBLE);
                        btnNextGame.setOnClickListener(v -> {
                            int i = 7;
                            db=new MyOpenHelper(cont);
                            db.ActualizarJuego_Id(i);
                            db.close();

                            mp.stop();
                            Intent e = new Intent();
                            e.putExtra("result",1);
                            setResult(Activity.RESULT_OK,e);
                            finish();
                        });
                        break;
                    case 1:
                        break;
                }
            }
        });

        CargarSegunPag_anterior(pag_anterior);
    }

    public void CargarSegunPag_anterior(int u){
        switch(u){
            case 0:

                break;
            case 1:
                btnNextGame.setEnabled(true);
                btnNextGame.setVisibility(View.VISIBLE);
                btnNextGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Argazkiaaztertu_7.this,Komikia_8.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }
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
