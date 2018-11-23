package com.example.ik_2dm3.didaktikapp;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class details_list extends AppCompatActivity {

    //BD
    private MyOpenHelper db;

    //Creamos objeto para coger datos de la parada
    private Paradas pr_actual;
    private int id_parada;
    private ArrayList<Juegos> lista_juegos;
    private int ID_juego;
    private String titulo;
    private String nombre_completo;
    private Intent i;
    private int contJuegos = 0;

    //para poner la imagen de fondo
    private ConstraintLayout contenido;

    //nombre parada
    private String txtParada;
    //para coger id del textview
    private TextView textView;
    //mediaplayer para reproducir audio de la parada
    //private MediaPlayer mp;
    //boton para pasar a los juegos
    private ImageButton btnNext;

    private int REQ_OK =  0;

    private Context cont = this;
    private Animation animacion;

    private AlertDialog alert;
    private AlertDialog.Builder builder;

    //img
    private byte[] decodedString;
    private Bitmap decodedByte;
    private Drawable drawable;

    //camara
    private static final int PERMISSION_CODE =1000;
    private static final int IMAGE_CAPTURE_CODE =1001;
    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);

        contenido = findViewById(R.id.contenido);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);

        pr_actual = new Paradas();

        id_parada = getIntent().getIntExtra("id_parada", 0);

        //Cogemos todos los nombres de las paradas que hay en la BD
        db=new MyOpenHelper(this);
        pr_actual = (Paradas) db.getDatos_parada_ID(id_parada);
        setTitle(pr_actual.getNombre());
        txtParada = pr_actual.getTexto();

               // String audio = "a"+pr_actual.getId_parada()+"_";

                //PlaySound(audio);
               // mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                //    public void onCompletion(MediaPlayer mp) {

                        //btnNext.setVisibility(View.INVISIBLE);
                        animacion = AnimationUtils.loadAnimation(cont, R.anim.animation);
                        btnNext.startAnimation(animacion);
                        animacion.setAnimationListener(new Animation.AnimationListener(){
                            @Override
                            public void onAnimationStart(Animation arg0) {

                            }
                            @Override
                            public void onAnimationRepeat(Animation arg0) {
                            }
                            @Override
                            public void onAnimationEnd(Animation arg0) {
                                btnNext.setEnabled(true);
                                btnNext.setVisibility(View.VISIBLE);
                            }
                        });


                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                CargarJuegos(lista_juegos,contJuegos);
                            }
                        });

                    //}
               // });

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

    /*public void PlaySound(String fileName){
        int sound_id = this.getResources().getIdentifier(fileName, "raw",
                this.getPackageName());
        if(sound_id != 0) {
            mp = MediaPlayer.create(this, sound_id);
            mp.start();

        }
    }*/

    public void PopUp(View v){
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Ahora sacarás una foto al edificio")
                .setTitle("TITULO DE PRUEBA")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                //checkCameraPermission();
                                openCamera();

                            }
                        });
        alert = builder.create();
        alert.show();
    }

    public void toImg(String byteArray) throws IOException {

        decodedString = Base64.decode(byteArray, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //Convert bitmap to drawable
        drawable = new BitmapDrawable(getResources(), decodedByte);
        contenido.setBackground(drawable);
    }

    public void CargarJuegos (ArrayList<Juegos> Listado_juegos, int pos){
        Log.d("mytag", "CARGANDO JUEGOS");

        //lista_juegos = new ArrayList<Juegos>();
        //lista_juegos = (ArrayList<Juegos>) db.getDatos_juegos_ID(pr_actual.getId_parada());

            Log.d("mytag","ESTADO JUEGO: "+Listado_juegos.get(pos).isRealizado());

            if (pos < lista_juegos.size()) {
                if (!Listado_juegos.get(pos).isRealizado()){
                    ID_juego = Listado_juegos.get(pos).getId_juego();
                    titulo = Listado_juegos.get(pos).getNombre_juego();

                    nombre_completo = "com.example.ik_2dm3.didaktikapp." + titulo + "_" + ID_juego;
                    nombre_completo = nombre_completo.replace(" ", "");
                    Log.d("mytag", "NOMBRE JUEGO: " + nombre_completo);
                    //int cont = 0;
                    //Log.d("mytag", "NOMBRE JUEGO: " +nombre_completo);
                    i = null;
                    try {
                        i = new Intent(this, Class.forName(nombre_completo));
                        i.putExtra("Description", Listado_juegos.get(0).getTxtDescripcion());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    startActivityForResult(i, REQ_OK);
                } else {
                    contJuegos += 1;
                    CargarJuegos(lista_juegos, contJuegos);
                }

            }
            else{
                Log.d("mytag", "Juegos finalizados de parada: "+pr_actual.getNombre());
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQ_OK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                Log.d("mytag","HE VUELTO DEL JUEGO ");
                contJuegos +=1;
                if (contJuegos < lista_juegos.size()){
                    CargarJuegos(lista_juegos, contJuegos);
                }
                else{
                  Log.d("mytag", "Juegos finalizados de parada JUEGO:   "+pr_actual.getNombre());
                  Log.d("mytag", "Juegos finalizados de parada JUEGO opcion CAMARA:   "+pr_actual.isSacarFoto());

                    if (pr_actual.isSacarFoto()){
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(checkSelfPermission(Manifest.permission.CAMERA) ==
                                    PackageManager.PERMISSION_DENIED ||
                                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                            PackageManager.PERMISSION_DENIED){
                                //PERMISSIONS NOT ENABLED, REQUEST IT
                                String[] permission ={Manifest.permission.CAMERA,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE};
                                //SHOW POPUP TO REQUEST PERMISSIONS
                                requestPermissions(permission,PERMISSION_CODE);

                            }
                            else{
                                //permission already granted
                                PopUp(contenido);
                                //openCamera();
                            }
                        }
                        else{
                            //system os < marshmallow
                            PopUp(contenido);
                            //openCamera();
                        }
                    }
                }
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
            }

            //resultado de sacar foto
            if (resultCode ==IMAGE_CAPTURE_CODE){
                Log.d("mytag","VUELVO DE LA CAMARA");
            }
        }
    }


    private void openCamera(){

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera");
        values.put(MediaStore.Images.Media.DATA,"/sdcard/"+imageFileName+".jpg");



        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Log.d("mytag","" +image_uri.getPath());


        //camera intent

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri );
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    //handing permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){

            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    //PERMISSIONS FROM POPUP WAS GRANTED
                    openCamera();

                }
                else{
                    //permissions from poup was denied
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();
            //return true;
            //mp.stop();
        }
        return super.onKeyDown(keyCode, event);
    }
}
