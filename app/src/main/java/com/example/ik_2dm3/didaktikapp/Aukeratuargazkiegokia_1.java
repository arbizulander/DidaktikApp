package com.example.ik_2dm3.didaktikapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifImageView;


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
    private ArrayList<Integer> arrayBarajado;

    //durante un segundo se bloquea el juego y no se puede pulsar ningún botón
    private boolean bloqueo = false;


    //funciones comunes
    funcionesComunes Funcion_Comun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aukeratu_argazkia_egokia_1);
        cargarImagenes();
        iniciar();
    }

    public void cargarImagenes(){

       imagenes = new int[]{
                R.drawable.juego1_1,
                R.drawable.juego1_2,
                R.drawable.juego1_3
        };
       imagen_correcta = R.drawable.juego1_3;
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
                    comprobar(imagenes[j], array_botones[j]);
                }
            });
        }
    }

    public void comprobar(final int ValorImagen, ImageButton pulsado){

        pulsado.setEnabled(false);

        if (ValorImagen == imagen_correcta){
            int valorcancion = R.raw.correct;

            Animation animacion = AnimationUtils.loadAnimation(this, R.anim.animacion_img);
            pulsado.startAnimation(animacion);

            Reproducir_cancion(this,valorcancion, pulsado);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Has  ganado!!", Toast.LENGTH_SHORT);
            toast.show();


        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "ERROR!!", Toast.LENGTH_SHORT);
            toast.show();
        }

        pulsado.setEnabled(true);
    }

    public void Reproducir_cancion (Context cont, int ID,ImageButton pulsado){
        MediaPlayer mp;
        //ACCIONES AL ACABAR CANCION//
        mp = MediaPlayer.create(cont,ID);
        mp.start();

        Animation animacion= null;

        for(int i=0; i <array_botones.length; i++){
            if (!array_botones[i].equals(pulsado)){
                final int j = i;
                animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_semitransparent);
                array_botones[i].startAnimation(animacion);


                animacion.setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation arg0) {
                    }
                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }
                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        Double valor = 0.5;
                        array_botones[j].setAlpha(valor.floatValue());

                    }
                });

            }
        }




        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {

                //pulsado.setPivotX(0);
                // pulsado.setPivotY(0);

                //pulsado.setScaleY(2);
                //pulsado.setScaleX(2);

                GifImageView gifImageView = (GifImageView) findViewById(R.id.GifImageView);
                gifImageView.setImageResource(R.drawable.banana);

                Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_gif);

                gifImageView.startAnimation(animacion);

                gifImageView.setZ(1111);
                //pulsado.set
                //pulsado.setImageResource(R.drawable.check);
            }
        });
    }

}
