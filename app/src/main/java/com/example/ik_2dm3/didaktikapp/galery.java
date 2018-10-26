package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class galery extends AppCompatActivity {
    //Button btnHome;

    private ArrayList<Paradas> lista_paradas;
    private ListView paradasView;

    //BD
    private MyOpenHelper db;

    static final int REQ_BTN = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        db=new MyOpenHelper(this);
        lista_paradas = db.getDatos_Paradas();
        byte [] data = lista_paradas.get(0).getImagen();
        //Log.d("mytag",lista_paradas.get(0).getImagen().toString());
        Log.d("mytag", "ARRAY DE BYTE IMAGEN:"+data);
        //btnHome = (Button) findViewById(R.id.btnHome);
        try{
            toImg(data);
        }catch (IOException e){

        }


        /*btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(galery.this,MainActivity.class);



                startActivityForResult(intent, REQ_BTN);

            }
        });*/
    }

    public void toImg(byte[] byteArray) throws IOException {
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.imageView);

        image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(), image.getHeight(), false));

        //image.setImageResource(R.drawable.error);
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
