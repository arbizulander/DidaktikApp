package com.example.ik_2dm3.didaktikapp;

import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Aukeratuargazkiegokia_1 extends AppCompatActivity {

    //los botones del juego
    private ImageButton img1, img2, img3;

    //las imágenes
    private int imagenes[];
    private int imagen_correcta;
    //se guardan duplicadas en un array
    private ImageButton array_botones[];

    //para barajar
    //el ArrayList que recoge el resultado de barajar
    private ArrayList<Integer> arrayAleatorio;
    private ArrayList<Integer> arrayImagenes;

    ArrayList<Integer> arrayBarajado;

    //durante un segundo se bloquea el juego y no se puede pulsar ningún botón
    boolean bloqueo = false;

    //para controlar las pausas del juego
    //final Handler handler = new Handler();

    ImageButton primero;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aukeratu_argazkia_egokia_1);
        cargarImagenes();
        //cargarBotones();
        //iniciar();

        /*img1.setImageResource(R.drawable.juego1_1);
        img2.setImageResource(R.drawable.juego1_2);
        img3.setImageResource(R.drawable.juego1_3);
*/
        iniciar();
    }

    public void cargarImagenes(){

       imagenes = new int[]{
                R.drawable.juego1_1,
                R.drawable.juego1_2,
                R.drawable.juego1_3
        };
       imagen_correcta = R.drawable.juego1_3;

        //fondo = R.drawable.fondo;
    }

    public ArrayList<Integer> barajar(int longitud) {
        ArrayList resultadoA = new ArrayList<Integer>();
        for(int i=0; i<longitud; i++) {

            if (!resultadoA.contains(imagenes[i % longitud / 2])) {
                resultadoA.add(imagenes[i % longitud / 2]);
                //Log.d("mytag","ya lo tiene");
            }
        }
        Collections.shuffle(resultadoA);
        return  resultadoA;
    }

    public void cargarBotones(){
        img1 = (ImageButton) findViewById(R.id.imageButton);
        img2 = (ImageButton) findViewById(R.id.imageButton2);
        img3 = (ImageButton) findViewById(R.id.imageButton3);
        array_botones = new ImageButton[]{img1, img2, img3};
    }

    public void iniciar(){
       arrayBarajado = barajar(imagenes.length*2);
        cargarBotones();
        Log.d("mytag","VALORES IMAGENES: "+ imagenes[0]+"  "+imagenes[1]+"  "+imagenes[2]);
        Log.d("mytag", "·VALORES ARRAYBARAJADO: "+ arrayBarajado.get(0).intValue()+"  "+arrayBarajado.get(1).intValue()+"   "+arrayBarajado.get(2).intValue());

        imagenes = new int []{arrayBarajado.get(0),arrayBarajado.get(1),arrayBarajado.get(2)};

        //Collections.shuffle(imagenes);
        //MOSTRAMOS LA IMAGEN
        for(int i=0; i<array_botones.length; i++) {
            array_botones[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            array_botones[i].setImageResource(imagenes[i]);
        }


        //AÑADIMOS LOS EVENTOS A LOS BOTONES DEL JUEGO
        for(int i=0; i <array_botones.length; i++){
            final int j = i;
            array_botones[i].setEnabled(true);
            array_botones[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comprobar(imagenes[j]);
                }
            });
        }
    }

    public void comprobar(final int ValorImagen){

        if (ValorImagen == imagen_correcta){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Has  ganado!!", Toast.LENGTH_LONG);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "ERROR!!", Toast.LENGTH_LONG);
            toast.show();
        }

    }

}
