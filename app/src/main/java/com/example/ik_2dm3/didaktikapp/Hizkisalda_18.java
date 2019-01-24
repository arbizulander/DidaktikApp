package com.example.ik_2dm3.didaktikapp;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Hizkisalda_18 extends AppCompatActivity {

    private LinearLayout l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    private View v1, v2, v3, v5, v6, v7;
    private boolean kultura,iguarrako,guk,ondarea,amurrio,refort;
    private boolean kultura1,iguarrako2,guk3,ondarea5,amurrio6,refort7;
    private static boolean palabraAcabada = false, palabraChekeada = false, cheked = false;
    private ArrayList<TextView> arr;
    private TextView a1,a2,a3,a5,a6,a7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hizkisalda_18);

        //Instanciar los LinearLayouts
        l1 = findViewById(R.id.actividad7_layout_1);
        l2 = findViewById(R.id.actividad7_layout_2);
        l3 = findViewById(R.id.actividad7_layout_3);
        l4 = findViewById(R.id.actividad7_layout_4);
        l5 = findViewById(R.id.actividad7_layout_5);
        l6 = findViewById(R.id.actividad7_layout_6);
        l7 = findViewById(R.id.actividad7_layout_7);
        l8 = findViewById(R.id.actividad7_layout_8);
        l9 = findViewById(R.id.actividad7_layout_9);
        l10 = findViewById(R.id.actividad7_layout_10);
        l11 = findViewById(R.id.actividad7_layout_11);
        l12 = findViewById(R.id.actividad7_layout_12);

        //Intancias los views
        v1 = findViewById(R.id.actividad7_barra_nursery);
        v1.setVisibility(View.INVISIBLE);
        v2 = findViewById(R.id.actividad7_barra_achucarro);
        v2.setVisibility(View.INVISIBLE);
        v3 = findViewById(R.id.actividad7_barra_guk);
        v3.setVisibility(View.INVISIBLE);
        v5 = findViewById(R.id.actividad7_barra_ingelesa);
        v5.setVisibility(View.INVISIBLE);
        v6 = findViewById(R.id.actividad7_barra_amurrio);
        v6.setVisibility(View.INVISIBLE);
        v7 = findViewById(R.id.actividad7_barra_smith);
        v7.setVisibility(View.INVISIBLE);
        //Crear los arrays con las letras correspondientes
        String[] textArray1 =  {"F", "A", "Z", "F", "R", "K", "T", "U", "P", "A", "V", "I"};
        String[] textArray2 =  {"W", "E", "R", "G", "Q", "A", "J", "I", "D", "A", "A", "I"};
        String[] textArray3 =  {"B", "A", "M", "L", "W", "N", "X", "Z", "A", "E", "U", "N"};
        String[] textArray4 =  {"N", "G", "Q", "I", "S", "P", "V", "H", "E", "K", "B", "G"};
        String[] textArray5 =  {"U", "Z", "I", "K", "N", "A", "V", "X", "U", "L", "A", "E"};
        String[] textArray6 =  {"R", "R", "S", "F", "B", "I", "C", "X", "B", "E", "A", "L"};
        String[] textArray7 =  {"S", "M", "M", "A", "J", "A", "S", "C", "R", "K", "J", "E"};
        String[] textArray8 =  {"E", "A", "I", "J", "X", "K", "T", "M", "I", "T", "A", "S"};
        String[] textArray9 =  {"R", "X", "T", "J", "Q", "T", "F", "A", "U", "I", "A", "A"};
        String[] textArray10 = {"Y", "J", "H", "S", "O", "T", "O", "A", "H", "K", "A", "W"};
        String[] textArray11 = {"U", "A", "O", "B", "A", "K", "A", "U", "S", "O", "N", "X"};
        String[] textArray12 = {"A", "C", "H", "U", "C", "A", "R", "R", "O", "A", "A", "J"};

        //Booleans a falso
        kultura = false;
        iguarrako = false;
        guk = false;
        ondarea = false;
        amurrio = false;
        refort = false;

        kultura1 = false;
        iguarrako2 = false;
        guk3 = false;
        ondarea5 = false;
        amurrio6 = false;
        refort7 = false;

        //inicializar el array de textviews
        arr = new ArrayList<>();

        //inicializar los textviews de ayuda
        a1 = findViewById(R.id.actividad7_ayuda_kultura);
        a2 = findViewById(R.id.actividad7_ayuda_iguarrako);
        a3 = findViewById(R.id.actividad7_ayuda_guk);
        a5 = findViewById(R.id.actividad7_ayuda_ondarea);
        a6 = findViewById(R.id.actividad7_ayuda_amurrio);
        a7 = findViewById(R.id.actividad7_ayuda_refor);

        //Posicionar los TextViews
        //n ES EL NUMERO DE LA PALABRA A ADIVINAR, TEXTARRAY ES LA COLUMNA y i ES LA FILA
        for( int i = 0; i < textArray1.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray1[i]);

            //Comprobar si son la primera o la ultima letra y añadirles el listener
            switch (i){
                case 11:
                    aniadirListenerPrimera(textView,5);
                    break;
                default:
                    aniadirListener(textView);
                    break;
            }
            l1.addView(textView);

            arr.add(textView);
        }

        for( int i = 0; i < textArray2.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray2[i]);


            switch (i){
                case 11:
                    aniadirListenerPrimera(textView,5);
                    break;
                default:
                    aniadirListener(textView);
                    break;
            }

            l2.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray3.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray3[i]);

            switch (i){
                case 9:
                    aniadirListenerPrimera(textView, 6);
                    break;
                case 8:
                    aniadirListenerPrimera(textView,6);
                    break;
                default:
                    aniadirListener(textView);
                    break;
            }

            l3.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray4.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray4[i]);

            switch (i){
                case 0:
                    aniadirListenerPrimera(textView,1);
                    break;
                default:
                    aniadirListener(textView);
                    break;
            }

            l4.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray5.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray5[i]);

            aniadirListener(textView);

            l5.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray6.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray6[i]);

            switch (i){
                case 2:
                    aniadirListenerPrimera(textView,7);
                    break;
                default:
                    aniadirListener(textView);
                    break;
            }

            l6.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray7.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray7[i]);

            switch (i){

                default:
                    aniadirListener(textView);
                    break;
            }

            l7.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray8.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray8[i]);

            switch(i){

                default:
                    aniadirListener(textView);
                    break;
            }
            aniadirListener(textView);

            l8.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray9.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray9[i]);

            //aniadirListener(textView);
            switch (i){
                case 11:
                    aniadirListenerUltima(textView,5);
                    break;
                default:
                    aniadirListener(textView);
                    break;
            }

            l9.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray10.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray10[i]);

            switch (i){
                case 0:
                    aniadirListenerUltima(textView,1);
                    break;
                case 2:
                    aniadirListenerUltima(textView,7);
                    break;
                case 7:
                    aniadirListenerPrimera(textView,3);
                    break;
                case 3:
                    aniadirListenerUltima(textView,3);
                    break;
                default:
                    aniadirListener(textView);
                    break;
            }

            l10.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray11.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray11[i]);

            aniadirListener(textView);

            l11.addView(textView);
            arr.add(textView);
        }

        for( int i = 0; i < textArray12.length; i++ ) {
            TextView textView = new TextView(this);
            textView.setWidth(50);
            textView.setHeight(65);
            textView.setPadding(5,5,5,5);
            textView.setTextSize(15);
            textView.setGravity(TextView.TEXT_ALIGNMENT_GRAVITY);
            textView.setText(textArray12[i]);

            switch (i){
                case 0:
                    aniadirListenerPrimera(textView,2);
                    break;
                case 8:
                    aniadirListenerUltima(textView,2);
                    break;
                case 9:
                    aniadirListenerUltima(textView, 6);
                    break;
                default:
                    aniadirListener(textView);
                    break;
            }

            l12.addView(textView);
            arr.add(textView);
        }
    }


    //metodos para añadir los listener que no hacen nada
    private void aniadirListener(final TextView t){
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!palabraChekeada){
                    if(!cheked){
                        palabraChekeada = true;
                        t.setBackgroundResource(R.drawable.rounded_edges);
                        cheked = true;
                    } else {
                        cheked = false;
                        t.setBackgroundColor(Color.TRANSPARENT);
                    }
                } else {
                    t.setBackgroundResource(R.drawable.rounded_edges);
                    limpiarArr();
                }
            }
        });
    }

    //metodo para añadir los listener a las primera letras
    private void aniadirListenerPrimera(final TextView t, int n){
        switch (n){
            case 1:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            kultura = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(kultura1){
                                kultura = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v1.setVisibility(View.VISIBLE);
                                Log.d("mytag","DSFSD");
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                kultura = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 2:
                t.setOnClickListener(new View.OnClickListener() {//IGURRAKO
                    @Override
                    public void onClick(View v) {
                        Log.d("mytag","has pulsado " + n);
                        if(!palabraChekeada){
                            iguarrako = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(iguarrako2){
                                iguarrako = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v2.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                iguarrako = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 3:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            guk = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(guk3){
                                guk = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v3.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                guk = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 5:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            ondarea = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(ondarea5){
                                ondarea = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v5.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                ondarea = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 6:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                             amurrio = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(amurrio6){
                                amurrio = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v6.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                amurrio = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 7:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            refort = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(refort7){
                                refort = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v7.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                refort = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
        }

    }

    //metodo para añadir los listener a las ultima letras
    private void aniadirListenerUltima(final TextView t, int n){
        switch (n){
            case 1:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            kultura1 = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(kultura){
                                kultura1 = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v1.setVisibility(View.VISIBLE);
                                Log.d("mytag","KULTURAAAA");
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                kultura1 = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 2:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            Log.d("mytag","SOY LA ULTIMA LETRA!");
                            iguarrako2 = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(iguarrako){
                                iguarrako2 = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v2.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                iguarrako2 = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 3:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            guk3 = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(guk){
                                guk3 = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v3.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                guk3 = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 5:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            ondarea5 = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(ondarea){
                                ondarea5 = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v5.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                ondarea5 = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 6:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            amurrio6 = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(amurrio){
                                amurrio6 = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v6.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                amurrio6 = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
            case 7:
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!palabraChekeada){
                            refort7 = true;
                            t.setBackgroundResource(R.drawable.rounded_edges);
                            palabraChekeada = true;
                        } else {
                            if(refort){
                                refort7 = true;
                                palabraAcabada = true;
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                comprobarCorrecto();
                                v7.setVisibility(View.VISIBLE);
                            } else {
                                t.setBackgroundResource(R.drawable.rounded_edges);
                                refort7 = false;
                                limpiarArr();
                            }
                        }
                    }
                });
                break;
        }

    }

    public void comprobarCorrecto(){
        if(palabraAcabada){
            if(kultura & kultura1){
                a1.setVisibility(View.INVISIBLE);
            }
            if(iguarrako & iguarrako2){
                a2.setVisibility(View.INVISIBLE);
            }
            if(guk & guk3){
                a3.setVisibility(View.INVISIBLE);
            }
            if(ondarea & ondarea5){
                a5.setVisibility(View.INVISIBLE);
            }
            if(amurrio & amurrio6){
                a6.setVisibility(View.INVISIBLE);
            }
            if(refort & refort7){
                a7.setVisibility(View.INVISIBLE);
            }
            limpiarArr();
            palabraAcabada = false;
            comprobarFinalizado();
        }
    }

    public void comprobarFinalizado(){
        if(kultura&iguarrako&guk&ondarea&amurrio&refort&
                kultura1&iguarrako2&guk3&ondarea5&amurrio6&refort7){
            Toast.makeText(this, "Has acabado todas las palabras, ENHORABUENA!!\nHacer que se vuelva a la principal", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void limpiarArr(){
        CountDownTimer c = new CountDownTimer(200,200) {
            @Override
            public void onTick(long millisUntilFinished) { }

            @Override
            public void onFinish() {
                for (int i = 0; i < arr.size(); i++){
                    arr.get(i).setBackgroundColor(Color.TRANSPARENT);
                    palabraChekeada = false;
                    cheked = false;
                }
            }
        };

        c.start();
    }

}