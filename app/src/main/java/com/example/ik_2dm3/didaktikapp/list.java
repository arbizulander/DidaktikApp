package com.example.ik_2dm3.didaktikapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class list extends AppCompatActivity {


    //Declaración del spinner y su Adapter
    /*private Spinner spinComentarios;
    private ArrayAdapter spinnerAdapter;

    //Lista de comentarios y comentario actual
    private ArrayList<Paradas> lista_paradas;
    private Paradas parada;
    private MyOpenHelper db;
    private EditText txtNombre;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //Iniciamos el controlador de la base de datos
       /* db=new MyOpenHelper(this);
        lista_paradas = db.getNombres();
        txtNombre = findViewById(R.id.txtNombre);

        //Creamos el adapter y lo asociamos al spinner
        spinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,lista_paradas);
        spinComentarios.setAdapter(spinnerAdapter);
        //spinComentarios.setOnItemSelectedListener(this);

        //Si hay algun comentario seleccionado mostramos sus valores en la parte inferior
        if(parada!=null) {
            txtNombre.setText(parada.getNombre());
            //txtComentario.setText(parada.getImagen());
        }*/
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
