package com.example.ik_2dm3.didaktikapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class list extends AppCompatActivity {

    //array donde guardaremos los titulos de las paradas
    private String[] titulo;

    //Aqui guardaremos los datos de la BD
    private ArrayList<Paradas> lista_paradas;
    private ListView paradasView;

    //BD
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        paradasView = findViewById(R.id.paradas_lista);

        //Cogemos todos los nombres de las paradas que hay en la BD
        db=new MyOpenHelper(this);
        lista_paradas = db.getDatos_Paradas();

        //Los pasamos a un array para poder hacer el ArrayAdapter con el ListView
        titulo = new String [lista_paradas.size()];
        for (int i = 0; i<titulo.length; i++){
            titulo[i] = (i+1)+ "." + lista_paradas.get(i).getNombre();
        }

        //Pasamos array al ArrayAdapter para que salga en el ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titulo);
        paradasView.setAdapter(adapter);
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
