package com.example.ik_2dm3.didaktikapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Galderenerantzunaaukeratu_5 extends AppCompatActivity {

    private Spinner spinner;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galderenerantzunaaukeratu_5);

        mp = MediaPlayer.create(this, R.raw.a2_2);
        mp.start();

        spinner = findViewById(R.id.spinner);

        List<String> categorias = new ArrayList<>();
        categorias.add(0, "Kolorea aukeratu");
        categorias.add("Verdea");
        categorias.add("Gorria");
        categorias.add("Morea");
        categorias.add("Gorria");
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
                    //Log.d("mitag","" + position);

                    //show selected spinner item
                    Toast.makeText(parent.getContext(),"Irabazi duzu:" +item, Toast.LENGTH_SHORT).show();

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
}
