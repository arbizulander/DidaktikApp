package com.example.ik_2dm3.didaktikapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class menu_main extends AppCompatActivity {
    //Button btnHome;
    private Button btnIbilbidea;
    private Button btnGaleria;
    private Button btnOndareak;
    private ImageButton btnAjustes;

    static final int REQ_TEXT = 0;


    static final int REQ_BTN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        setTitle("Menua");

        btnIbilbidea = findViewById(R.id.btnIbilbidea);
        btnGaleria = findViewById(R.id.btnGaleria);
        btnOndareak = findViewById(R.id.btnOndareak);
        btnAjustes = findViewById(R.id.btnAjustes);

        btnIbilbidea.setOnClickListener(v -> {

           /* ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);

            startActivity(intent, options.toBundle());*/
            Intent intent = new Intent(menu_main.this,MapsActivity.class);
            overridePendingTransition(R.anim.zoom_forward_out, R.anim.zoom_forward_in);

            AbrirLayout thread = new AbrirLayout(intent, REQ_BTN);
            thread.start();
            //startActivityForResult(intent, REQ_BTN);
        });

        btnGaleria.setOnClickListener(v -> {
            Log.d("mytag","... ABRIENDO GALERIA ...");
            Intent intent = new Intent(menu_main.this,galery.class);
            Log.d("mytag", "... ABRIENDO INTENT...");
            startActivityForResult(intent,REQ_BTN);
            //AbrirLayout thread = new AbrirLayout(intent, REQ_BTN);
            //thread.start();
            //startActivityForResult(intent, REQ_BTN);
        });

        btnOndareak.setOnClickListener(v -> {
            Intent intent = new Intent(menu_main.this,list.class);
            AbrirLayout thread = new AbrirLayout(intent, REQ_BTN);
            thread.start();
            //startActivityForResult(intent, REQ_BTN);
        });


        btnAjustes.setOnClickListener(v -> {
            Intent intent = new Intent(menu_main.this,ajustes.class);

            //startActivityForResult(intent, REQ_BTN);
        });

        btnAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getBaseContext(), ajustes.class);

                startActivityForResult(i, REQ_TEXT);



            }
        });


    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQ_TEXT:
                if (resultCode==RESULT_OK){
                    Log.d("mifiltro","todo bien text");
                }else {
                    Log.d("mifiltro","error text");
                }
                break;

            default:
        }
    }

    class AbrirLayout extends Thread {
        private Intent i;
        private int req;

        public AbrirLayout(Intent i, int req) {
            this.i = i;
            this.req = req;
        }

        @Override
        public void run() {
            Log.d("mytag", "... ABRIENDO INTENT...");
            startActivityForResult(i,req);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            /*Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();*/
            //return true;
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
