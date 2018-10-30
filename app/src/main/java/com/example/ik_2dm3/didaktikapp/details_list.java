package com.example.ik_2dm3.didaktikapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class details_list extends AppCompatActivity {

    //BD
    private MyOpenHelper db;
    //Creamos objeto para coger datos de la parada
    private Paradas pr_actual;
    private ArrayList<Juegos> lista_juegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);

        pr_actual = new Paradas();
        int id_parada = getIntent().getIntExtra("id_parada", 0);

        //Cogemos todos los nombres de las paradas que hay en la BD
        db=new MyOpenHelper(this);
        pr_actual = (Paradas) db.getDatos_parada_ID(id_parada);

        String nombre_parada = pr_actual.getNombre();
        Toast.makeText(getApplicationContext(), "Nombre parada: "+ nombre_parada, Toast.LENGTH_LONG).show();

        lista_juegos = (ArrayList<Juegos>) db.getDatos_juegos_ID(id_parada);

        String nombre_juego = lista_juegos.get(0).getNombre_juego();

        Toast.makeText(getApplicationContext(), "Nombre juego: "+ nombre_juego, Toast.LENGTH_LONG).show();
    }
}
