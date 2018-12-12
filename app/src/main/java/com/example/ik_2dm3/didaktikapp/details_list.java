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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.os.Environment.getExternalStorageDirectory;

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
    private String[] titulo_juegos;
    private ListView juegosView;

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
    private int pag_anterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);

        contenido = findViewById(R.id.contenido);

        pr_actual = new Paradas();

        id_parada = getIntent().getIntExtra("id_parada", 0);
        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        //Cogemos todos los nombres de las paradas que hay en la BD
        db=new MyOpenHelper(this);
        pr_actual = (Paradas) db.getDatos_parada_ID(id_parada);
        setTitle(pr_actual.getNombre());
        txtParada = pr_actual.getTexto();

        //ponemos como background la imagen de BD de esa parada
            /*try {
            if (pr_actual.getImagen()!= null){
                toImg(pr_actual.getImagen());
            }
            else{
                contenido.setBackgroundResource(R.drawable.error);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        lista_juegos = (ArrayList<Juegos>) db.getDatos_juegos_ID(id_parada);
        db.close();

        CargarSegunPag_anterior(pag_anterior);

    }

    public void CargarSegunPag_anterior(int u){

        switch (u){
            case 0:
                Log.d("mytag", "...Cargando juegos viniendo desde el MAPA...");
                CargarJuegos(lista_juegos,0);
                /*Log.d("mytag", "...Cargando juegos viniendo desde el MAPA...");
                try {
                    titulo_juegos = new String [lista_juegos.size()];
                    for (int i = 0; i<titulo_juegos.length; i++){
                        titulo_juegos[i] = (i+1)+ "." + lista_juegos.get(i).getNombre_juego();
                    }

                    ID_juego = lista_juegos.get(u).getId_juego();
                    titulo = lista_juegos.get(u).getNombre_juego();
                    nombre_completo = "com.example.ik_2dm3.didaktikapp." + titulo + "_" + ID_juego;
                    nombre_completo = nombre_completo.replace(" ", "");
                    i = new Intent(this, Class.forName(nombre_completo));
                    i.putExtra("Description", lista_juegos.get(0).getTxtDescripcion());
                    i.putExtra("pag_anterior",0);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                startActivityForResult(i, REQ_OK);*/

                break;

            case 1:
                juegosView = findViewById(R.id.paradas_lista_juegos);
                juegosView.setVisibility(View.VISIBLE);

                titulo_juegos = new String [lista_juegos.size()];
                for (int i = 0; i<titulo_juegos.length; i++){
                    titulo_juegos[i] = (i+1)+ "." + lista_juegos.get(i).getNombre_juego();
                }

                //Pasamos array al ArrayAdapter para que salga en el ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titulo_juegos);
                juegosView.setAdapter(adapter);

                juegosView.setOnItemClickListener((parent, view, position, id) -> {

                    ID_juego = lista_juegos.get(position).getId_juego();
                    titulo = lista_juegos.get(position).getNombre_juego();
                    nombre_completo = "com.example.ik_2dm3.didaktikapp." + titulo + "_" + ID_juego;
                    nombre_completo = nombre_completo.replace(" ", "");
                    Log.d("mytag", "NOMBRE JUEGO: " + nombre_completo);
                    i = null;
                    try {
                        i = new Intent(this, Class.forName(nombre_completo));
                        i.putExtra("Description", lista_juegos.get(0).getTxtDescripcion());
                        i.putExtra("pag_anterior",1);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    startActivityForResult(i, REQ_OK);
                });
                break;
        }
    }

    public void PopUp(View v){
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Ahora sacarás una foto al edificio")
                .setTitle("TITULO DE PRUEBA")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                dialog.cancel();
                                //checkCameraPermission();
                                openCamera();

                            }
                        });
        alert = builder.create();
        alert.show();
    }

    /*public void toImg(String byteArray) throws IOException {

        decodedString = Base64.decode(byteArray, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //Convert bitmap to drawable
        drawable = new BitmapDrawable(getResources(), decodedByte);
        contenido.setBackground(drawable);
    }*/

    public void CargarJuegos (ArrayList<Juegos> Listado_juegos, int pos){
        Log.d("mytag", "CARGANDO JUEGOS");

        //lista_juegos = new ArrayList<Juegos>();
        //lista_juegos = (ArrayList<Juegos>) db.getDatos_juegos_ID(pr_actual.getId_parada());

        Log.d("mytag","ESTADO JUEGO numero "+pos+" : "+Listado_juegos.get(pos).isRealizado());

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
            }
            /*else {
                if (pos < lista_juegos.size()) {
                    contJuegos += 1;
                    CargarJuegos(lista_juegos, contJuegos);
                }
            }*/
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

                switch (pag_anterior){

                    case 0:
                        Log.d("mytag","HE VUELTO DEL JUEGO ");
                        contJuegos+=1;
                        Log.d("mytag", "Contador juegos : "+ contJuegos + " TOtal juegos: "+lista_juegos.size());
                        if (contJuegos < lista_juegos.size()){
                            CargarJuegos(lista_juegos, contJuegos);
                        }
                        else{

                            db=new MyOpenHelper(cont);
                            db.ActualizarParada_Id(1);
                            db.close();

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
                                        Log.d("mytag","POPUP para camara");
                                        //permission already granted
                                        PopUp(contenido);
                                        //openCamera();
                                    }
                                }
                                else{
                                    Log.d("mytag","POPUP para camara");
                                    //system os < marshmallow
                                    PopUp(contenido);
                                    //openCamera();
                                }
                            }
                        }
                        // The user picked a contact.
                        // The Intent's data Uri identifies which contact was selected.

                        // Do something with the contact here (bigger example below)
                        break;

                    case 1:

                        break;
                }



            }

            //resultado de sacar foto
            if (resultCode ==IMAGE_CAPTURE_CODE){
                Log.d("mytag","VUELVO DE LA CAMARA");
                finish();
            }
        }
    }


    private void openCamera(){

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File dir = new File(getExternalStorageDirectory(),"DidaktikApp");
        if(!dir.exists()){
            dir.mkdir();
        }

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera");
        values.put(MediaStore.Images.Media.DATA,getExternalStorageDirectory()+"/DidaktikApp/"+imageFileName+".jpg");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        //Log.d("mytag","" +image_uri.getPath());

        //abrir camara
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
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
