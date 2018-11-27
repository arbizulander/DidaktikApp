package com.example.ik_2dm3.didaktikapp;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import static android.os.Environment.getExternalStorageDirectory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class galery extends AppCompatActivity {

    //para la imagen
    private RelativeLayout pantalla;
    Uri image_uri;

    private Context cont = this;
    private FloatingActionButton my_fab;

    //REQ
    private static final int PERMISSION_CODE =1000;
    private static final int IMAGE_CAPTURE_CODE =1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        pantalla = findViewById(R.id.pantalla);
        //miImageView = findViewById(R.id.miImageView);
        //botones
        //Button mCaptureBtn = findViewById(R.id.capture_image);
        my_fab = (FloatingActionButton) findViewById(R.id.my_fab);

        File dir = new File(getExternalStorageDirectory(),"DidaktikApp");

        //dentro del if cargar imagenes en galeria
        if (dir.exists()){
            Log.d("mytag","CARGANDO GALERIA...");
            cargarGaleria(dir);
        }

        final Interpolator interpolador = AnimationUtils.loadInterpolator(getBaseContext(),
                android.R.interpolator.fast_out_slow_in);

        //button click
        my_fab.setOnClickListener(v -> {

            //IF SYSTEM IS >= MARSMALLOW, REQUEST RUNTIME PERMISSION
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
                    openCamera();
                }
            }
            else{
                //system os < marshmallow
                openCamera();
            }


            my_fab.animate()
                    .scaleX(0)
                    .scaleY(0)
                    .setInterpolator(interpolador)
                    .setDuration(600)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            /*my_fab.animate()
                                    .scaleY(1)
                                    .scaleX(1)
                                    .setInterpolator(interpolador)
                                    .setDuration(600)
                                    .start();*/
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
        });
    }

    private void cargarGaleria(File f){
        // Array TEXTO donde guardaremos los nombres de los ficheros
        ArrayList<Image> items = new ArrayList<Image>();

        //Defino la ruta donde busco los ficheros
        //File f = new File(Environment.getExternalStorageDirectory() + "/MiBotiquin/");
        //Creo el array de tipo File con el contenido de la carpeta
        File[] files = f.listFiles();

        //Hacemos un Loop por cada fichero para extraer el nombre de cada uno
        int i = 0;
        while ( i < 2)
            {
                Log.d("mytag", "NOMBRE FICHERO: "+files[i].getAbsolutePath());
                //Sacamos del array files un fichero
                TableRow lineatabla = new TableRow(this);
                ImageView imageView = new ImageView(this);
                ImageView imageView2 = new ImageView(this);

                File imgFile = new  File(files[i].toString());

                if(imgFile.exists()){

                    Bitmap bmImg = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    imageView.setImageBitmap(bmImg);

                }
                else{
                    imageView.setImageResource(R.drawable.error);
                }

                //imageView.setim;
                //imageView.setImageResource(R.drawable.error);
                imageView2.setImageResource(R.drawable.juego1_2);

                //imageView.setMaxHeight(450);
                //imageView.setMaxWidth(450);
                imageView.setPadding(0,0,50,0);
                imageView.setBackgroundColor(Color.BLUE);
                imageView2.setBackgroundColor(Color.BLUE);


            TableLayout id_tabla = (TableLayout) findViewById(R.id.id_tabla);
            /*LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );*/

            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                   500,
                    500
            );


            //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            lineatabla.addView(imageView, layoutParams);
            i++;
            if (i < 2){
                lineatabla.addView(imageView2, layoutParams);
                i++;
            }
            lineatabla.setGravity(Gravity.CENTER);
            lineatabla.setPadding(0,0,0,50);
            id_tabla.addView(lineatabla);


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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        my_fab.setScaleX(1);
        my_fab.setScaleY(1);
        if(resultCode == RESULT_OK){

            //set the captured to our Imageview
            //miImageView.setImageURI(image_uri);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
