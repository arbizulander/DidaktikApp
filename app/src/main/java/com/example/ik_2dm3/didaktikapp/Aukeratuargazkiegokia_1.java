package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifImageView;

import static android.os.SystemClock.sleep;

public class Aukeratuargazkiegokia_1 extends AppCompatActivity {

    //conexion BD
    private MyOpenHelper db;

    //los botones del juego
    private ImageButton img1, img2, img3;

    //las imágenes
    private int imagenes[];
    private int imagen_correcta;
    //se guardan duplicadas en un array
    private ImageButton array_botones[];
    private ImageButton btnNextGame;

    //para barajar
    //el ArrayList que recoge el resultado de barajar
    private ArrayList<Integer> arrayBarajado;

    private View pantalla;

    //durante un segundo se bloquea el juego y no se puede pulsar ningún botón
    private boolean bloqueo = false;

    private float centreX, centreY;

   private MediaPlayer mp;

    private Context cont = this;
    private TextView txtDescripcion;

    private int pag_anterior;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;

    BroadcastReceiver miBroadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("TAG", "Screen ON");
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("TAG", "Screen OFF");
                mp.stop();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aukeratu_argazkia_egokia_1);
        //txtDescripcion = findViewById(R.id.txtDescripcion);
        btnNextGame = findViewById(R.id.btnNextGame);
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        mp = MediaPlayer.create(this, R.raw.a1_1);

        setTitle("Aukeratu argazki egokia");

        String valor = getIntent().getExtras().getString("Description");
        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);
        Log.d("mytag","pag anterior es " + pag_anterior);

        cargarImagenes();
        iniciar();
        HabilitarDeshabilitarBtns(false);

        CargarSegunPag_anterior(pag_anterior);
        PlaySound();
    }

    public void CargarSegunPag_anterior(int u){
        switch(u){
            case 0:
                break;

            case 1:
                //PlaySound();
                btnNextGame.setVisibility(View.VISIBLE);
                btnNextGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Aukeratuargazkiegokia_1.this,Elementuakbilatuargazkian_2.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }
    }

    public void PlaySound(){

            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    HabilitarDeshabilitarBtns(true);
                    Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_alpha1to0);
                }
            });
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

    public void HabilitarDeshabilitarBtns(Boolean habilitado){
        if (habilitado){
            img1.setEnabled(true);
            img2.setEnabled(true);
            img3.setEnabled(true);
        }
        else{
            img1.setEnabled(false);
            img2.setEnabled(false);
            img3.setEnabled(false);
        }
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

        pantalla = findViewById(R.id.idLayout);

        if (ValorImagen == imagen_correcta){

            HabilitarDeshabilitarBtns(false);

            int valorcancion = R.raw.correct;

            //calcular punto del centro del layout
            centreX=pantalla.getWidth()  / 2;
            centreY=pantalla.getHeight() / 2;

            float centreX_Img = pulsado.getWidth()/2;
            float centreY_Img = pulsado.getHeight()/2;

          TranslateAnimation trans = null;
             if (pulsado == img1){
                 trans = new TranslateAnimation(pulsado.getX(),centreX-centreX_Img,pulsado.getY(), centreY-centreY_Img);
            }else if (pulsado == img2){
                 trans = new TranslateAnimation(0,0,pulsado.getY(), centreY-centreY_Img);
             }else if (pulsado == img3){
                 trans = new TranslateAnimation(0,-(centreX-centreX_Img),pulsado.getY(), (centreY-centreY_Img));
            }

            Log.d("mytag","VALORES INICIALES: "+pulsado.getX()+"  "+pulsado.getY());
            Log.d("mytag","VALORES FINALES: "+(centreX-centreX_Img)+"  "+(centreY-centreY_Img));

            trans.setDuration(2000);
            trans.setStartOffset(4000);
            pulsado.startAnimation(trans);
            pulsado.setZ(111);

            trans.setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation arg0) {
                    }
                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }
                    @Override
                    public void onAnimationEnd(Animation arg0) {

                        Animation animacion = null;
                        if (pulsado == img1){
                            animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_scalex2_img1);
                        }else if (pulsado == img2){
                            animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_scalex2_img2);
                        }else if (pulsado == img3){
                            animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_scalex2_img3);
                        }

                        //poner pantalla en el centro del layout
                        pulsado.setX(centreX-centreX_Img);
                        pulsado.setY(centreY-centreY_Img);

                        //Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_scalex2);
                        pulsado.startAnimation(animacion);
                        animacion.setAnimationListener(new Animation.AnimationListener(){
                            @Override
                            public void onAnimationStart(Animation arg0) {

                            }
                            @Override
                            public void onAnimationRepeat(Animation arg0) {
                            }
                            @Override
                            public void onAnimationEnd(Animation arg0) {
                            double TamanioX = 1.8;
                            double TamanioY = 1.8;

                                pulsado.setScaleX( ((float) TamanioX));
                                pulsado.setScaleY(  ((float) TamanioY));

                                //sleep(1000);

                             switch (pag_anterior){

                                 case 0:
                                     int i = 1;
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
                        });
                    }
                });

            Log.d("mytag", "CENTROPANTALLA:  "+centreX +"  "+centreY);

            Reproducir_cancion(this,valorcancion, pulsado);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Oso ondo!!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Txarto!!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void Reproducir_cancion (Context cont, int ID,ImageButton pulsado){
        pulsado.setEnabled(false);
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
                GifImageView gifImageView = (GifImageView) findViewById(R.id.GifImageView);
                gifImageView.setImageResource(R.drawable.banana);
                Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_gif);
                gifImageView.startAnimation(animacion);
                gifImageView.setZ(1111);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            if (pag_anterior == 0){
                Intent i = new Intent();
                i.putExtra("keydown",REQ_BTNATRAS);
                setResult(RESULT_OK,i);
            }
            //Log.d("mytag","Back disabled");
            mp.stop();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Log.d("mytag","Back disabled");
    }
}
