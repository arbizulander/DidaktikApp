package com.example.ik_2dm3.didaktikapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class details_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);

        String id_parada = getIntent().getStringExtra("id_parada");
        //int id = Integer.parseInt(id_parada);
        Toast.makeText(getApplicationContext(), "valor ID: "+ id_parada, Toast.LENGTH_LONG).show();

        /*switch (id_parada){
            case "0":
                Toast.makeText(getApplicationContext(), "valor ID: "+ id_parada, Toast.LENGTH_LONG).show();
                break;
        }*/
    }
}
