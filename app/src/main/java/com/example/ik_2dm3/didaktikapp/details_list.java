package com.example.ik_2dm3.didaktikapp;

import android.Manifest;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static okio.HashingSink.md5;

public class details_list extends AppCompatActivity {

    //BD
    private MyOpenHelper db;
    //Creamos objeto para coger datos de la parada
    private Paradas pr_actual;
    private ArrayList<Juegos> lista_juegos;
    private ConstraintLayout contenido;
    private static final int CAMERA_PIC_REQUEST = 1;
    private static final int SELECT_PICTURE = 2;
    private static String DB_PATH_GALERY = "/data/data/com.example.ik_2dm3.didaktikapp/";
    private Uri uriSavedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_list);

        contenido = findViewById(R.id.contenido);

        pr_actual = new Paradas();

        int id_parada = getIntent().getIntExtra("id_parada", 0);

        //Cogemos todos los nombres de las paradas que hay en la BD
        db=new MyOpenHelper(this);
        pr_actual = (Paradas) db.getDatos_parada_ID(id_parada);
        setTitle(pr_actual.getNombre());

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
}
