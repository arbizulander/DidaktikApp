package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Hizkisalda_18 extends AppCompatActivity {

    private LinearLayout l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12;
    private View v1, v2, v3, v5, v6, v7;
    private MediaPlayer mp;
    private ImageButton btnNext;
    private boolean kultura,iguarrako,guk,ondarea,amurrio,refort;
    private boolean kultura1,iguarrako2,guk3,ondarea5,amurrio6,refort7;
    private boolean blnSinValor, blnPulsado;
    private static boolean palabraAcabada = false, palabraChekeada = false, cheked = false;
    private ArrayList<TextView> arr;
    private TextView a1,a2,a3,a5,a6,a7;
    private Context cont = this;
    private MyOpenHelper db;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hizkisalda_18);

        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.a7_2);
        mp.start();

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        switch (pag_anterior){
            case 0:
                break;
            case 1:
                //btnNext.set
                btnNext.setEnabled(true);
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Hizkisalda_18.this,Informazioakuadroabete_19.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }

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
        blnSinValor = false;
        blnPulsado = false;

        //FILA 1
        TextView txt1_1Errejionalista = findViewById(R.id.txt1_1);
        TextView txt1_2 = findViewById(R.id.txt1_2);
        TextView txt1_3 = findViewById(R.id.txt1_3);
        TextView txt1_4 = findViewById(R.id.txt1_4);
        TextView txt1_5 = findViewById(R.id.txt1_5);
        TextView txt1_6 = findViewById(R.id.txt1_6);
        TextView txt1_7 = findViewById(R.id.txt1_7);
        TextView txt1_8 = findViewById(R.id.txt1_8);
        TextView txt1_9 = findViewById(R.id.txt1_9);
        TextView txt1_10 = findViewById(R.id.txt1_10);
        TextView txt1_11 = findViewById(R.id.txt1_11);
        TextView txt1_12 = findViewById(R.id.txt1_12);
        TextView txt1_13 = findViewById(R.id.txt1_13);
        TextView txt1_14 = findViewById(R.id.txt1_14);

        //FILA 2
        TextView txt2_1 = findViewById(R.id.txt2_1);
        TextView txt2_2 = findViewById(R.id.txt2_2);
        TextView txt2_3 = findViewById(R.id.txt2_3);
        TextView txt2_4 = findViewById(R.id.txt2_4);
        TextView txt2_5 = findViewById(R.id.txt2_5);
        TextView txt2_6 = findViewById(R.id.txt2_6);
        TextView txt2_7 = findViewById(R.id.txt2_7);
        TextView txt2_8 = findViewById(R.id.txt2_8);
        TextView txt2_9 = findViewById(R.id.txt2_9);
        TextView txt2_10 = findViewById(R.id.txt2_10);
        TextView txt2_11 = findViewById(R.id.txt2_11);
        TextView txt2_12 = findViewById(R.id.txt2_12);
        TextView txt2_13 = findViewById(R.id.txt2_13);
        TextView txt2_14 = findViewById(R.id.txt2_14);

        //FILA 3
        TextView txt3_1 = findViewById(R.id.txt3_1);
        TextView txt3_2 = findViewById(R.id.txt3_2);
        TextView txt3_3 = findViewById(R.id.txt3_3);
        TextView txt3_4 = findViewById(R.id.txt3_4);
        TextView txt3_5 = findViewById(R.id.txt3_5);
        TextView txt3_6 = findViewById(R.id.txt3_6);
        TextView txt3_7 = findViewById(R.id.txt3_7);
        TextView txt3_8 = findViewById(R.id.txt3_8);
        TextView txt3_9 = findViewById(R.id.txt3_9);
        TextView txt3_10 = findViewById(R.id.txt3_10);
        TextView txt3_11 = findViewById(R.id.txt3_11);
        TextView txt3_12 = findViewById(R.id.txt3_12);
        TextView txt3_13 = findViewById(R.id.txt3_13);
        TextView txt3_14 = findViewById(R.id.txt3_14);

        //FILA 4
        TextView txt4_1 = findViewById(R.id.txt4_1);
        TextView txt4_2 = findViewById(R.id.txt4_2);
        TextView txt4_3 = findViewById(R.id.txt4_3);
        TextView txt4_4 = findViewById(R.id.txt4_4);
        TextView txt4_5 = findViewById(R.id.txt4_5);
        TextView txt4_6 = findViewById(R.id.txt4_6);
        TextView txt4_7 = findViewById(R.id.txt4_7);
        TextView txt4_8 = findViewById(R.id.txt4_8);
        TextView txt4_9 = findViewById(R.id.txt4_9);
        TextView txt4_10 = findViewById(R.id.txt4_10);
        TextView txt4_11 = findViewById(R.id.txt4_11);
        TextView txt4_12 = findViewById(R.id.txt4_12);
        TextView txt4_13 = findViewById(R.id.txt4_13);
        TextView txt4_14 = findViewById(R.id.txt4_14);

        //FILA 5
        TextView txt5_1 = findViewById(R.id.txt5_1);
        TextView txt5_2 = findViewById(R.id.txt5_2);
        TextView txt5_3 = findViewById(R.id.txt5_3);
        TextView txt5_4 = findViewById(R.id.txt5_4);
        TextView txt5_5 = findViewById(R.id.txt5_5);
        TextView txt5_6 = findViewById(R.id.txt5_6);
        TextView txt5_7 = findViewById(R.id.txt5_7);
        TextView txt5_8 = findViewById(R.id.txt5_8);
        TextView txt5_9 = findViewById(R.id.txt5_9);
        TextView txt5_10 = findViewById(R.id.txt5_10);
        TextView txt5_11 = findViewById(R.id.txt5_11);
        TextView txt5_12 = findViewById(R.id.txt5_12);
        TextView txt5_13 = findViewById(R.id.txt5_13);
        TextView txt5_14 = findViewById(R.id.txt5_14);

        //FILA 6
        TextView txt6_1 = findViewById(R.id.txt6_1);
        TextView txt6_2 = findViewById(R.id.txt6_2);
        TextView txt6_3 = findViewById(R.id.txt6_3);
        TextView txt6_4 = findViewById(R.id.txt6_4);
        TextView txt6_5 = findViewById(R.id.txt6_5);
        TextView txt6_6Ingelesa = findViewById(R.id.txt6_6);
        TextView txt6_7 = findViewById(R.id.txt6_7);
        TextView txt6_8 = findViewById(R.id.txt6_8);
        TextView txt6_9 = findViewById(R.id.txt6_9);
        TextView txt6_10 = findViewById(R.id.txt6_10);
        TextView txt6_11 = findViewById(R.id.txt6_11);
        TextView txt6_12Smith = findViewById(R.id.txt6_12);
        TextView txt6_13IngelesaEnd = findViewById(R.id.txt6_13);
        TextView txt6_14AchucarroEnd = findViewById(R.id.txt6_14);

        //FILA 7
        TextView txt7_1 = findViewById(R.id.txt7_1);
        TextView txt7_2 = findViewById(R.id.txt7_2);
        TextView txt7_3 = findViewById(R.id.txt7_3);
        TextView txt7_4 = findViewById(R.id.txt7_4);
        TextView txt7_5 = findViewById(R.id.txt7_5);
        TextView txt7_6 = findViewById(R.id.txt7_6);
        TextView txt7_7 = findViewById(R.id.txt7_7);
        TextView txt7_8 = findViewById(R.id.txt7_8);
        TextView txt7_9 = findViewById(R.id.txt7_9);
        TextView txt7_10 = findViewById(R.id.txt7_10);
        TextView txt7_11 = findViewById(R.id.txt7_11);
        TextView txt7_12 = findViewById(R.id.txt7_12);
        TextView txt7_13 = findViewById(R.id.txt7_13);
        TextView txt7_14 = findViewById(R.id.txt7_14);

        //FILA 8
        TextView txt8_1 = findViewById(R.id.txt8_1);
        TextView txt8_2 = findViewById(R.id.txt8_2);
        TextView txt8_3 = findViewById(R.id.txt8_3);
        TextView txt8_4 = findViewById(R.id.txt8_4);
        TextView txt8_5 = findViewById(R.id.txt8_5);
        TextView txt8_6 = findViewById(R.id.txt8_6);
        TextView txt8_7 = findViewById(R.id.txt8_7);
        TextView txt8_8 = findViewById(R.id.txt8_8);
        TextView txt8_9 = findViewById(R.id.txt8_9);
        TextView txt8_10 = findViewById(R.id.txt8_10);
        TextView txt8_11 = findViewById(R.id.txt8_11);
        TextView txt8_12 = findViewById(R.id.txt8_12);
        TextView txt8_13 = findViewById(R.id.txt8_13);
        TextView txt8_14 = findViewById(R.id.txt8_14);

        //FILA 9
        TextView txt9_1 = findViewById(R.id.txt9_1);
        TextView txt9_2 = findViewById(R.id.txt9_2);
        TextView txt9_3 = findViewById(R.id.txt9_3);
        TextView txt9_4 = findViewById(R.id.txt9_4);
        TextView txt9_5 = findViewById(R.id.txt9_5);
        TextView txt9_6 = findViewById(R.id.txt9_6);
        TextView txt9_7 = findViewById(R.id.txt9_7);
        TextView txt9_8 = findViewById(R.id.txt9_8);
        TextView txt9_9 = findViewById(R.id.txt9_9);
        TextView txt9_10 = findViewById(R.id.txt9_10);
        TextView txt9_11 = findViewById(R.id.txt9_11);
        TextView txt9_12 = findViewById(R.id.txt9_12);
        TextView txt9_13 = findViewById(R.id.txt9_13);
        TextView txt9_14 = findViewById(R.id.txt9_14);


        //FILA 10
        TextView txt10_1 = findViewById(R.id.txt10_1);
        TextView txt10_2 = findViewById(R.id.txt10_2);
        TextView txt10_3 = findViewById(R.id.txt10_3);
        TextView txt10_4 = findViewById(R.id.txt10_4);
        TextView txt10_5 = findViewById(R.id.txt10_5);
        TextView txt10_6 = findViewById(R.id.txt10_6);
        TextView txt10_7 = findViewById(R.id.txt10_7);
        TextView txt10_8 = findViewById(R.id.txt10_8);
        TextView txt10_9 = findViewById(R.id.txt10_9);
        TextView txt10_10 = findViewById(R.id.txt10_10);
        TextView txt10_11 = findViewById(R.id.txt10_11);
        TextView txt10_12SmithEnd = findViewById(R.id.txt10_12);
        TextView txt10_13 = findViewById(R.id.txt10_13);
        TextView txt10_14 = findViewById(R.id.txt10_14);

        //FILA 11
        TextView txt11_1 = findViewById(R.id.txt11_1);
        TextView txt11_2 = findViewById(R.id.txt11_2);
        TextView txt11_3 = findViewById(R.id.txt11_3);
        TextView txt11_4 = findViewById(R.id.txt11_4);
        TextView txt11_5 = findViewById(R.id.txt11_5);
        TextView txt11_6 = findViewById(R.id.txt11_6);
        TextView txt11_7 = findViewById(R.id.txt11_7);
        TextView txt11_8 = findViewById(R.id.txt11_8);
        TextView txt11_9 = findViewById(R.id.txt11_9);
        TextView txt11_10 = findViewById(R.id.txt11_10);
        TextView txt11_11 = findViewById(R.id.txt11_11);
        TextView txt11_12 = findViewById(R.id.txt11_12);
        TextView txt11_13 = findViewById(R.id.txt11_13);
        TextView txt11_14 = findViewById(R.id.txt11_14);

        //FILA 12
        TextView txt12_1 = findViewById(R.id.txt12_1);
        TextView txt12_2 = findViewById(R.id.txt12_2);
        TextView txt12_3 = findViewById(R.id.txt12_3);
        TextView txt12_4 = findViewById(R.id.txt12_4);
        TextView txt12_5 = findViewById(R.id.txt12_5);
        TextView txt12_6 = findViewById(R.id.txt12_6);
        TextView txt12_7 = findViewById(R.id.txt12_7);
        TextView txt12_8 = findViewById(R.id.txt12_8);
        TextView txt12_9 = findViewById(R.id.txt12_9);
        TextView txt12_10 = findViewById(R.id.txt12_10);
        TextView txt12_11 = findViewById(R.id.txt12_11);
        TextView txt12_12 = findViewById(R.id.txt12_12);
        TextView txt12_13 = findViewById(R.id.txt12_13);
        TextView txt12_14 = findViewById(R.id.txt12_14);

        //FILA 13
        TextView txt13_1 = findViewById(R.id.txt13_1);
        TextView txt13_2 = findViewById(R.id.txt13_2);
        TextView txt13_3 = findViewById(R.id.txt13_3);
        TextView txt13_4 = findViewById(R.id.txt13_4);
        TextView txt13_5 = findViewById(R.id.txt13_5);
        TextView txt13_6 = findViewById(R.id.txt13_6);
        TextView txt13_7 = findViewById(R.id.txt13_7);
        TextView txt13_8 = findViewById(R.id.txt13_8);
        TextView txt13_9 = findViewById(R.id.txt13_9);
        TextView txt13_10 = findViewById(R.id.txt13_10);
        TextView txt13_11 = findViewById(R.id.txt13_11);
        TextView txt13_12 = findViewById(R.id.txt13_12);
        TextView txt13_13 = findViewById(R.id.txt13_13);
        TextView txt13_14 = findViewById(R.id.txt13_14);

        //FILA 14
        TextView txt14_1 = findViewById(R.id.txt14_1);
        TextView txt14_2 = findViewById(R.id.txt14_2);
        TextView txt14_3 = findViewById(R.id.txt14_3);
        TextView txt14_4 = findViewById(R.id.txt14_4);
        TextView txt14_5 = findViewById(R.id.txt14_5);
        TextView txt14_6 = findViewById(R.id.txt14_6);
        TextView txt14_7 = findViewById(R.id.txt14_7);
        TextView txt14_8 = findViewById(R.id.txt14_8);
        TextView txt14_9 = findViewById(R.id.txt14_9);
        TextView txt14_10 = findViewById(R.id.txt14_10);
        TextView txt14_11 = findViewById(R.id.txt14_11);
        TextView txt14_12 = findViewById(R.id.txt14_12);
        TextView txt14_13 = findViewById(R.id.txt14_13);
        TextView txt14_14ErrejionalistaEnd = findViewById(R.id.txt14_14);

        TextView[] textViewArray = {txt1_1Errejionalista,txt1_2,txt1_3,txt1_4,txt1_5,txt1_6,txt1_7,txt1_8,txt1_9,txt1_10,txt1_11,txt1_12,txt1_13,txt1_14,
                                    txt2_1,txt2_2,txt2_3,txt2_4,txt2_5,txt2_6,txt2_7,txt2_8,txt2_9,txt2_10,txt2_11,txt2_12,txt2_13,txt2_14,
                                    txt3_1,txt3_2,txt3_3,txt3_4,txt3_5,txt3_6,txt3_7,txt3_8,txt3_9,txt3_10,txt3_11,txt3_12,txt3_13,txt3_14,
                                    txt4_1,txt4_2,txt4_3,txt4_4,txt4_5,txt4_6,txt4_7,txt4_8,txt4_9,txt4_10,txt4_11,txt4_12,txt4_13,txt4_14,
                                    txt5_1,txt5_2,txt5_3,txt5_4,txt5_5,txt5_6,txt5_7,txt5_8,txt5_9,txt5_10,txt5_11,txt5_12,txt5_13,txt5_14,
                                    txt6_1,txt6_2,txt6_3,txt6_4,txt6_5,txt6_6Ingelesa,txt6_7,txt6_8,txt6_9,txt6_10,txt6_11,txt6_12Smith,txt6_13IngelesaEnd,txt6_14AchucarroEnd,
                                    txt7_1,txt7_2,txt7_3,txt7_4,txt7_5,txt7_6,txt7_7,txt7_8,txt7_9,txt7_10,txt7_11,txt7_12,txt7_13,txt7_14,
                                    txt8_1,txt8_2,txt8_3,txt8_4,txt8_5,txt8_6,txt8_7,txt8_8,txt8_9,txt8_10,txt8_11,txt8_12,txt8_13,txt8_14,
                                    txt9_1,txt9_2,txt9_3,txt9_4,txt9_5,txt9_6,txt9_7,txt9_8,txt9_9,txt9_10,txt9_11,txt9_12,txt9_13,txt9_14,
                                    txt10_1,txt10_2,txt10_3,txt10_4,txt10_5,txt10_6,txt10_7,txt10_8,txt10_9,txt10_10,txt10_11,txt10_12SmithEnd,txt10_13,txt10_14,
                                    txt11_1,txt11_2,txt11_3,txt11_4,txt11_5,txt11_6,txt11_7,txt11_8,txt11_9,txt11_10,txt11_11,txt11_12,txt11_13,txt11_14,
                                    txt12_1,txt12_2,txt12_3,txt12_4,txt12_5,txt12_6,txt12_7,txt12_8,txt12_9,txt12_10,txt12_11,txt12_12,txt12_13,txt12_14,
                                    txt13_1,txt13_2,txt13_3,txt13_4,txt13_5,txt13_6,txt13_7,txt13_8,txt13_9,txt13_10,txt13_11,txt13_12,txt13_13,txt13_14,
                                    txt14_1,txt14_2,txt14_3,txt14_4,txt14_5,txt14_6,txt14_7,txt14_8,txt14_9,txt14_10,txt14_11,txt14_12,txt14_13,txt14_14ErrejionalistaEnd};

        TextView[] textViewErrejionalista = {txt1_1Errejionalista,txt2_2,txt3_3,txt4_4,txt5_5,txt6_6Ingelesa,txt7_7,txt8_8,txt9_9,txt10_10,txt11_11,txt12_12,txt13_13,txt14_14ErrejionalistaEnd};
        TextView[] textViewEIngelesa = {txt6_6Ingelesa,txt6_7,txt6_8,txt6_9,txt6_10,txt6_11,txt6_12Smith,txt6_13IngelesaEnd};
        TextView[] textViewStmith = {txt6_12Smith,txt7_12,txt8_12,txt9_12,txt10_12SmithEnd};
        TextView[] textViewAchucarro = {txt14_14ErrejionalistaEnd,txt13_14,txt12_14,txt11_14,txt10_14,txt9_14,txt8_14,txt7_14,txt6_14AchucarroEnd};
        // textViewArray ={txt1_1,txt1_2,txt1_3,txt1_4};

        final int[] contLetras = {0} ;
        for (int i = 0; i < textViewArray.length; i++){
            final int j = i;
            textViewArray[i].setBackgroundColor(Color.TRANSPARENT);
            textViewArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ColorDrawable cd = (ColorDrawable) textViewArray[j].getBackground();
                    int colorCode = cd.getColor();

                    if (colorCode != Color.BLUE &&
                        colorCode != Color.CYAN &&
                        colorCode != Color.GREEN &&
                        colorCode != Color.MAGENTA ||

                        textViewArray[j] == txt6_6Ingelesa && (colorCode == Color.BLUE || colorCode == Color.CYAN)||
                        textViewArray[j] == txt6_12Smith && (colorCode == Color.CYAN || colorCode == Color.GREEN) ||
                        textViewArray[j] == txt14_14ErrejionalistaEnd && (colorCode == Color.BLUE || colorCode == Color.MAGENTA)){

                        textViewArray[j].setBackgroundColor(Color.GRAY);

                        //ERREJIONALISTA
                        if (txt1_1Errejionalista == textViewArray[j]){
                            kultura = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                        }
                        if (txt14_14ErrejionalistaEnd == textViewArray[j] && kultura == true){
                            blnSinValor = false;
                            for (int e = 0; e<textViewErrejionalista.length; e++){
                                textViewErrejionalista[e].setBackgroundColor(Color.BLUE);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //INGELESA
                        if (txt6_6Ingelesa == textViewArray[j]){
                            iguarrako = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                        }
                        if (txt6_13IngelesaEnd == textViewArray[j] && iguarrako == true){
                            blnSinValor = false;
                            for (int e = 0; e<textViewEIngelesa.length; e++){
                                textViewEIngelesa[e].setBackgroundColor(Color.CYAN);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //SMITH
                        if (txt6_12Smith == textViewArray[j]){
                            guk = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                        }
                        if (txt10_12SmithEnd == textViewArray[j] && guk == true){
                            blnSinValor = false;
                            for (int e = 0; e<textViewStmith.length; e++){
                                textViewStmith[e].setBackgroundColor(Color.GREEN);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                        //ACHUCARRO
                        if (txt14_14ErrejionalistaEnd == textViewArray[j]){
                            guk = true;
                            blnSinValor = false;
                        }
                        else{
                            blnSinValor = true;
                        }
                        if (txt6_14AchucarroEnd == textViewArray[j] && guk == true){
                            blnSinValor = false;
                            for (int e = 0; e<textViewAchucarro.length; e++){
                                textViewAchucarro[e].setBackgroundColor(Color.MAGENTA);
                            }
                        }
                        else{
                            blnSinValor = true;
                        }

                    /*if (){
                        blnSinValor = false;
                    }*/

                        if (blnPulsado){
                            Log.d("mytag","SEGUNDA LETRA PULSADA");
                            // Drawable d = getResources().getColor(Color.GRAY);
                            cd = (ColorDrawable) textViewArray[j].getBackground();
                            colorCode = cd.getColor();
                            if (blnSinValor && colorCode == Color.GRAY){
                                Log.d("mytag", "TextView pasar a gris o color: "+ textViewArray[j]);

                                textViewArray[ contLetras[0]].setBackgroundColor(Color.TRANSPARENT);
                                textViewArray[j].setBackgroundColor(Color.TRANSPARENT);

                                if (textViewArray[contLetras[0]] == txt6_6Ingelesa || textViewArray[j] == txt6_6Ingelesa){
                                    txt6_6Ingelesa.setBackgroundColor(Color.BLUE);
                                }else if (textViewArray[contLetras[0]] == txt6_12Smith || textViewArray[j] == txt6_12Smith ){
                                    txt6_12Smith.setBackgroundColor(Color.CYAN);
                                }else if (textViewArray[contLetras[0]] == txt14_14ErrejionalistaEnd || textViewArray[j] == txt14_14ErrejionalistaEnd){
                                    txt14_14ErrejionalistaEnd.setBackgroundColor(Color.BLUE);
                                }

                                Log.d("mytag","PRIMERA LETRA: "+contLetras[0]);
                                Log.d("mytag","SEGUNDA LETRA: "+j);

                                blnSinValor = false;
                            }
                            blnPulsado = false;
                        }else{
                            Log.d("mytag","PRIMERA LETRA PULSADA");
                            contLetras[0] = j;
                            blnPulsado = true;
                        }
                    }

                }
            });
        }

       /* txt1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1_1.setBackgroundColor(Color.BLUE);

            }
        });*/
        //Instanciar los LinearLayouts
        /*l1 = findViewById(R.id.actividad7_layout_1);
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
        l12 = findViewById(R.id.actividad7_layout_12);*/

        //Intancias los views
        /*v1 = findViewById(R.id.actividad7_barra_nursery);
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
        String[] textArray12 = {"A", "C", "H", "U", "C", "A", "R", "R", "O", "A", "A", "J"};*/

        //Booleans a falso
        /*kultura = false;
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
        refort7 = false;*/

        //inicializar el array de textviews
       // arr = new ArrayList<>();

        //inicializar los textviews de ayuda
        /*a1 = findViewById(R.id.actividad7_ayuda_kultura);
        a2 = findViewById(R.id.actividad7_ayuda_iguarrako);
        a3 = findViewById(R.id.actividad7_ayuda_guk);
        a5 = findViewById(R.id.actividad7_ayuda_ondarea);
        a6 = findViewById(R.id.actividad7_ayuda_amurrio);
        a7 = findViewById(R.id.actividad7_ayuda_refor);*/

        //Posicionar los TextViews
        //n ES EL NUMERO DE LA PALABRA A ADIVINAR, TEXTARRAY ES LA COLUMNA y i ES LA FILA
        /*for( int i = 0; i < textArray1.length; i++ ) {
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
        }*/

        /*for( int i = 0; i < textArray2.length; i++ ) {
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
    }*/


    //metodos para añadir los listener que no hacen nada
    /*private void aniadirListener(final TextView t){
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
    }*/

    //metodo para añadir los listener a las primera letras
    /*private void aniadirListenerPrimera(final TextView t, int n){
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

    }*/

    //metodo para añadir los listener a las ultima letras
    /*private void aniadirListenerUltima(final TextView t, int n){
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

    }*/

    /*public void comprobarCorrecto(){
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
    }*/

    /*public void comprobarFinalizado(){
        if(kultura&iguarrako&guk&ondarea&amurrio&refort&
                kultura1&iguarrako2&guk3&ondarea5&amurrio6&refort7){
            //Pasar de juego
            switch (pag_anterior){
                case 0:
                    int i = 18;
                    db=new MyOpenHelper(cont);
                    db.ActualizarJuego_Id(i);
                    db.close();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",1);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                    break;

                case 1:
                    break;
            }
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

        c.start();*/
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (pag_anterior == 0){
                Intent i = new Intent();
                i.putExtra("keydown",REQ_BTNATRAS);
                setResult(RESULT_OK,i);
            }
            mp.stop();
            Log.d("mytag","Has ido atras");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}