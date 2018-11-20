package com.example.ik_2dm3.didaktikapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.view.animation.AnimationUtils.currentAnimationTimeMillis;
import static okio.HashingSink.md5;

public class details_list extends AppCompatActivity {

    //BD
    private MyOpenHelper db;
    //Creamos objeto para coger datos de la parada
    private Paradas pr_actual;
    private ArrayList<Juegos> lista_juegos;
    private ConstraintLayout contenido;
    private String txtPrueba;
    private TextView textView;
    private MediaPlayer mp;
    private ImageButton btnNext;
    private int REQ_OK =  0;
    private Context cont = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);

        contenido = findViewById(R.id.contenido);
        textView = findViewById(R.id.textView);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        pr_actual = new Paradas();

        int id_parada = getIntent().getIntExtra("id_parada", 0);

        //Cogemos todos los nombres de las paradas que hay en la BD
        db=new MyOpenHelper(this);
        pr_actual = (Paradas) db.getDatos_parada_ID(id_parada);
        setTitle(pr_actual.getNombre());
        txtPrueba = pr_actual.getTexto();
        textView.setText(txtPrueba);
        textView.setMovementMethod(new ScrollingMovementMethod());

        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.animation);
        textView.startAnimation(animacion);
        animacion.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {

            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                String audio = "a"+pr_actual.getId_parada()+"_";

                PlaySound(audio);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        btnNext.setEnabled(true);
                        //btnNext.setVisibility(View.INVISIBLE);
                        Animation animacion = AnimationUtils.loadAnimation(cont, R.anim.animation);
                        btnNext.startAnimation(animacion);
                        btnNext.setVisibility(View.VISIBLE);

                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CargarJuegos(lista_juegos);
                            }
                        });

                    }
                });
            }
        });

        //ponemos como background la imagen de BD de esa parada
            try {
            if (pr_actual.getImagen()!= null){
                toImg(pr_actual.getImagen());
            }
            else{
                contenido.setBackgroundResource(R.drawable.error);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        lista_juegos = (ArrayList<Juegos>) db.getDatos_juegos_ID(id_parada);
        db.close();
        //PopUp(contenido);

    }

    public void PlaySound(String fileName){
        int sound_id = this.getResources().getIdentifier(fileName, "raw",
                this.getPackageName());
        if(sound_id != 0) {
            mp = MediaPlayer.create(this, sound_id);
            mp.start();

        }
    }

    /*public void PopUp(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Mensaje de prueba")
                .setTitle("TITULO DE PRUEBA")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                //checkCameraPermission();
                                abrirCamara();

                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }*/

    public void toImg(String byteArray) throws IOException {

        byte[] decodedString = Base64.decode(byteArray, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //Convert bitmap to drawable
        Drawable drawable = new BitmapDrawable(getResources(), decodedByte);
        contenido.setBackground(drawable);
    }

    public void CargarJuegos (ArrayList<Juegos> Listado_juegos){
        Log.d("mytag", "CARGANDO JUEGOS");
        int ID_juego = Listado_juegos.get(0).getId_juego();
        String titulo = Listado_juegos.get(0).getNombre_juego();

        String nombre_completo = "com.example.ik_2dm3.didaktikapp."+titulo+"_"+ID_juego;
        nombre_completo = nombre_completo.replace(" ","");
        Log.d("mytag", "NOMBRE JUEGO: " +nombre_completo);
        int cont = 0;
            //Log.d("mytag", "NOMBRE JUEGO: " +nombre_completo);
        Intent i = null;
        try {
            i = new Intent(this, Class.forName(nombre_completo));
            i.putExtra("Description",Listado_juegos.get(0).getTxtDescripcion());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivityForResult(i, REQ_OK);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();
            //return true;
            mp.stop();
        }
        return super.onKeyDown(keyCode, event);
    }
}
