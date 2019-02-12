package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Desberdintasunakbilatu_4 extends AppCompatActivity {

    private ImageButton Hurrengoa;

    static final int REQ_TEXT = 0;
    private ImageButton btnNextGame;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;
    private int pag_anterior, refrescar;

    //las imágenes
    private int imagenes[];

    //se guardan duplicadas en un array
    private ImageButton array_botones[];

    //los botones del juego
    private ImageButton img1, img2, img3;

    MediaPlayer mp;

    private View pantalla;
    private float guardX, guardY;
    private float centreX, centreY;
    private Context cont = this;

    //variables para guardar tamaño y punto original de la imagen pulsada
    private int heightI, widthI;
    private MyOpenHelper db;

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
        setContentView(R.layout.activity_desberdintasuna_bilatu_4);

        setTitle("Desbertintasunak bilatu");
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        mp = MediaPlayer.create(this, R.raw.a2_1);

        btnNextGame = findViewById(R.id.btnNextGame);

        btnNextGame.setEnabled(false);
        btnNextGame.setVisibility(View.INVISIBLE);

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);
        refrescar = getIntent().getIntExtra("refrescar", 0);

        cargarImagenes();
        iniciar();
        HabilitarDeshabilitarBtns(false);
        CargarSegunPag_anterior(pag_anterior);
        if (refrescar == 0){
            PlaySound();
        }
        else{
            HabilitarDeshabilitarBtns(true);
        }

    }

    public void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.desberdintasunabilatu_argazki1,
                R.drawable.desberdintasunabilatu_argazki2,
                R.drawable.desberdintasunabilatu_argazki3
        };
    }

    public void cargarBotones(){
        img1 = (ImageButton) findViewById(R.id.imageButton);
        img2 = (ImageButton) findViewById(R.id.imageButton2);
        img3 = (ImageButton) findViewById(R.id.imageButton3);
        array_botones = new ImageButton[]{img1, img2, img3};
    }

    public void iniciar(){

        cargarBotones();
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
                    ampliar(imagenes[j], array_botones[j]);
                }
            });
        }
    }

    public void CargarSegunPag_anterior(int u){
        switch(u){
            case 0:
                break;
            case 1:
                btnNextGame.setEnabled(true);
                btnNextGame.setVisibility(View.VISIBLE);
                btnNextGame.setOnClickListener(v -> {
                    mp.stop();
                    Intent i = new Intent(Desberdintasunakbilatu_4.this,Galderenerantzunaaukeratu_5.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });
                break;
        }
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

    public void ampliar(final int ValorImagen, ImageButton pulsado){

        widthI = pulsado.getWidth();
        heightI = pulsado.getHeight();

        pulsado.setEnabled(false);

        pantalla = findViewById(R.id.idLayout);

        HabilitarDeshabilitarBtns(false );

        Animation animacion= null;

        for(int i=0; i <array_botones.length; i++){
            if (!array_botones[i].equals(pulsado)){
                final int j = i;
                animacion = AnimationUtils.loadAnimation(cont, R.anim.animation_semitransparent);
                array_botones[i].startAnimation(animacion);

                animacion.setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation arg0) {
                        pulsado.setOnClickListener(null);
                    }
                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }
                    @Override
                    public void onAnimationEnd(Animation arg0) {
                        Double valor = 0.5;
                        array_botones[j].setAlpha(valor.floatValue());
                        pulsado.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("mytag", "Volviendo imagen a posicion original");
                                //pulsado.setScaleY((float)heightI);
                                //pulsado.setScaleX((float)widthI);
                                pulsado.setX(guardX);
                                pulsado.setY(guardY);
                                pulsado.setScaleX(1);
                                pulsado.setScaleY(1);
                                HabilitarDeshabilitarBtns(true );
                                img1.setAlpha(1f);
                                img2.setAlpha(1f);
                                img3.setAlpha(1f);
                                iniciar();
                            }
                        });
                    }
                });

            }
        }
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
            guardX = pulsado.getX();
            guardY = pulsado.getY();
            pulsado.setEnabled(false);
            pulsado.setClickable(false);
            Log.d("mytag","VALORES FINALES: "+(centreX-centreX_Img)+"  "+(centreY-centreY_Img));

            trans.setDuration(200);
            trans.setStartOffset(400);
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
                    pulsado.setEnabled(true);
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
                        }
                    });
                }
            });
            Log.d("mytag", "CENTROPANTALLA:  "+centreX +"  "+centreY);
    }

    public void PlaySound(){
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                HabilitarDeshabilitarBtns(true);
                if (pag_anterior == 0){
                    btnNextGame.setEnabled(true);
                    btnNextGame.setVisibility(View.VISIBLE);

                    btnNextGame.setOnClickListener(v -> {
                        int i = 4;
                        db=new MyOpenHelper(cont);
                        db.ActualizarJuego_Id(i);
                        db.close();

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",1);
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    });
                }
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
            mp.stop();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
