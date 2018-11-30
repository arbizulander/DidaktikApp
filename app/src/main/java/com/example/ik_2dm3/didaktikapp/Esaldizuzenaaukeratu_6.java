package com.example.ik_2dm3.didaktikapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Esaldizuzenaaukeratu_6 extends AppCompatActivity {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esaldizuzenaaukeratu_6);

        spinner = findViewById(R.id.spinner);

        List<String> galderak = new ArrayList<>();
        galderak.add(0, "Galdera Egokia aukeratu");
        galderak.add("Bizkaiko zubia kolorez aldatu zen herritarren iritziz kolorea itsusia zelako");
        /*EGOKIA*/galderak.add("Kolorez aldatu zen beltzak erradiazioa xurgatzen zuelako eta beroaren ondorioz bere egitura dilatatzen zelako");
        galderak.add("Kolorez aldatu zen urtero margozten zelako eta kolore beltza garestia zelako");
        galderak.add("Kolorez aldatu zen guda zibilean bota zutenean kolore horretakoa zenez, oroitzapen txarrak ekartzen zituelako");
        galderak.add("Kolorez aldatu zen UNESCOk gizadiaren ondarea deklara zezan beste kolore batekoa izan behar zelako");

        //Style the spinner

        ArrayAdapter<String> datataAdapter;
        datataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, galderak);

        //Dropdown layout style
        datataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        //attaching data adapter to spinner
        spinner.setAdapter(datataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Galdera Egokia aukeratu")){


                    //do nothing
                }else if(parent.getItemAtPosition(position).equals("Kolorez aldatu zen beltzak erradiazioa xurgatzen zuelako eta beroaren ondorioz bere egitura dilatatzen zelako")){

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
