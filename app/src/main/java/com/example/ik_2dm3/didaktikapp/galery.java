package com.example.ik_2dm3.didaktikapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.Toast;


import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import static android.os.Environment.getExternalStorageDirectory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class galery extends AppCompatActivity {

    //para la imagen
    private Uri image_uri;

    private Context cont = this;
    private FloatingActionButton my_fab;

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    private Cargargaleria cg;
    private AbrirLayout thread;
    private int REQ_OK =  0;
    final ArrayList<ImageItem> imageItems = new ArrayList<>();
    private File[] files;
    private File dir;
    private Boolean blnCargado = false;
    //private ProgressBar pbarProgreso;
    //private ProgressDialog pDialog;

    //private MiTareaAsincrona tarea;

    //REQ
    private static final int PERMISSION_CODE =1000;
    private static final int IMAGE_CAPTURE_CODE =1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        my_fab = findViewById(R.id.my_fab);
        gridView = findViewById(R.id.gridView);

        setTitle("Galeria");

        dir = new File(getExternalStorageDirectory(),"DidaktikApp");

        //dentro del if cargar imagenes en galeria
        if (dir.exists() && blnCargado == false && dir.listFiles().length>0){
            Log.d("mytag","PRIMER CARGANDO GALERIA...");
            files = dir.listFiles();
            cg = new Cargargaleria(dir);
            cg.start();
            blnCargado = true;
        }


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


           /* my_fab.animate()
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
                            my_fab.animate()
                                    .scaleY(1)
                                    .scaleX(1)
                                    .setInterpolator(interpolador)
                                    .setDuration(600)
                                    .setListener (new Animator.AnimatorListener(){
                                        @Override
                                        public void onAnimationStart(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animator animation) {
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
                                        }

                                        @Override
                                        public void onAnimationCancel(Animator animation) {

                                        }

                                        @Override
                                        public void onAnimationRepeat(Animator animation) {

                                        }
                                    });
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });*/
        });

        //File[] files = dir.listFiles();
    }

    /*private
        //Hacemos un Loop por cada fichero para extraer el nombre de cada uno
        for (int i = 0; i < files.length; i++){

            File imgFile = new File(files[i].toString());
            Bitmap bmImg = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageItems.add(new Ima void cargarGaleria(File f){
        // Array TEXTO donde guardaremos los nombres de los ficheros
        Log.d("mytag","...CARGANDO GALERIA...");
        final ArrayList<ImageItem> imageItems = new ArrayList<>();

        //Defino la ruta donde busco los ficheros
        //File f = new File(Environment.getExternalStorageDirectory() + "/MiBotiquin/");
        //Creo el array de tipo File con el contenido de la carpeta
        File[] files = f.listFiles();
geItem(bmImg, "Image#" + i));
            Log.d("mytag", "... CARGANDO IMG " + i +" ...");
        }
        Log.d("mytag", "... GALERIA CARGADA ...");


        gridAdapter = new GridViewAdapter(cont, R.layout.grid_item_layout, imageItems);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener((parent, v, position, id) -> {

            //new MiTareaAsincrona(galery.this).execute("Carga Finalizada X2");

            Log.d("mytag", "... Onclick de GRIDVIEW ...");

            ImageItem item = (ImageItem) parent.getItemAtPosition(position);
            //Create intent
            Intent intent = new Intent(galery.this, DetailsActivity.class);
            intent.putExtra("title", item.getTitle());

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            item.getImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            intent.putExtra("rutaimg", files[position].toString());

            try {
                //Start details activity
                AbrirLayout thread = new AbrirLayout(intent, 0);
                thread.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        });
    }*/


    private void openCamera(){

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File dir = new File(getExternalStorageDirectory(),"DidaktikApp");

        if (dir.exists()){
            //cg.interrupt();
        }
        else if(!dir.exists()){
            dir.mkdir();
        }

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera");
        values.put(MediaStore.Images.Media.DATA,getExternalStorageDirectory()+"/DidaktikApp/"+imageFileName+".png");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


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

            Log.d("mytag", "... FOTO SACADA ...");
            File dir = new File(getExternalStorageDirectory(),"DidaktikApp");

            //thread.interrupt();
            //deleteCache(this);
            //dentro del if cargar imagenes en galeria
            if (dir.exists()){
                files = dir.listFiles();
                if (dir.listFiles().length>1){
                    Log.d("mytag","AÑADIENDO IMAGEN A GALERIA...");
                    cg.aniadir_imgGaleria();
                }
                else{
                    Log.d("mytag","CARGANDO GALERIA...");
                    cg = new Cargargaleria(dir);
                    cg.start();
                }


                //new MiTareaAsincrona(galery.this).execute("Carga Finalizada X2");
                //cg = new Cargargaleria(dir);
                //cg.start();
                //GridAdapter.notifyDataSetChanged();
                //gridView.setAdapter(gridAdapter);
            }
            //set the captured to our Imageview
            //miImageView.setImageURI(image_uri);
        }
    }

    class Cargargaleria extends Thread {
        //private File f;

        public Cargargaleria(File f) {
           //this.f = f;
        }

        @Override
        public void run() {
            runOnUiThread(() -> {
                // Array TEXTO donde guardaremos los nombres de los ficheros
                Log.d("mytag", "...CARGANDO GALERIA...");
                //final ArrayList<ImageItem> imageItems = new ArrayList<>();

                //Defino la ruta donde busco los ficheros
                //File f = new File(Environment.getExternalStorageDirectory() + "/MiBotiquin/");
                //Creo el array de tipo File con el contenido de la carpeta
                //files = f.listFiles();

                try{
                    //Hacemos un Loop por cada fichero para extraer el nombre de cada uno
                    for (int i = 0; i < files.length; i++) {

                        //Picasso picasso = new Picasso();
                        //picasso.load(new File(files[i].toString())).fit().error(R.drawable.ajustes).into(this.imageView).memoryPolicy(MemoryPolicy.NO_CACHE);

                        File imgFile = new File(files[i].toString());
                        Bitmap bmImg = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        imageItems.add(new ImageItem(bmImg, "Image#" + i));
                        Log.d("mytag", "... CARGANDO IMG " + i + " ...");
                    }
                }catch(Exception e){
                    //e.printStackTrace();
                }

                Log.d("mytag", "... GALERIA CARGADA ...");

                gridAdapter = new GridViewAdapter(cont, R.layout.grid_item_layout, imageItems);
                gridView.setAdapter(gridAdapter);
                gridView.setOnItemClickListener((parent, v, position, id) -> {

                    //new MiTareaAsincrona(galery.this).execute("Carga Finalizada X2");
                    Log.d("mytag", "... Onclick de GRIDVIEW ...");

                    ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                    //Create intent
                    Intent intent = new Intent(galery.this, DetailsActivity.class);
                    intent.putExtra("title", item.getTitle());
                    /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    item.getImage().compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();*/
                    try{
                        intent.putExtra("rutaimg", files[position].toString());
                        //Start details activity
                        Log.d("mytag", "... ABRIENDO INTENT...");
                        startActivityForResult(intent,0);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
            });
        }

        public void aniadir_imgGaleria (){

            files = dir.listFiles();
            File imgFile = new File(files[files.length-1].toString());
            Bitmap bmImg = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageItems.add(new ImageItem(bmImg, "Image#" + (files.length-1)));
        }



        /*@Override
        public void run() {
            // Array TEXTO donde guardaremos los nombres de los ficheros
            Log.d("mytag", "...CARGANDO GALERIA...");
            final ArrayList<ImageItem> imageItems = new ArrayList<>();

            //Defino la ruta donde busco los ficheros
            //File f = new File(Environment.getExternalStorageDirectory() + "/MiBotiquin/");
            //Creo el array de tipo File con el contenido de la carpeta
            File[] files = f.listFiles();

            //Hacemos un Loop por cada fichero para extraer el nombre de cada uno
            for (int i = 0; i < files.length; i++) {

                File imgFile = new File(files[i].toString());
                Bitmap bmImg = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                imageItems.add(new ImageItem(bmImg, "Image#" + i));
                Log.d("mytag", "... CARGANDO IMG " + i + " ...");
            }
            Log.d("mytag", "... GALERIA CARGADA ...");


            gridAdapter = new GridViewAdapter(cont, R.layout.grid_item_layout, imageItems);
            gridView.setAdapter(gridAdapter);
        }*/
            /*@Override
            public void run() {
                // Array TEXTO donde guardaremos los nombres de los ficheros
                Log.d("mytag","...CARGANDO GALERIA...");
                final ArrayList<ImageItem> imageItems = new ArrayList<>();

                //Defino la ruta donde busco los ficheros
                //File f = new File(Environment.getExternalStorageDirectory() + "/MiBotiquin/");
                //Creo el array de tipo File con el contenido de la carpeta
                File[] files = f.listFiles();

                //Hacemos un Loop por cada fichero para extraer el nombre de cada uno
                for (int i = 0; i < files.length; i++){

                    File imgFile = new File(files[i].toString());
                    Bitmap bmImg = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    imageItems.add(new ImageItem(bmImg, "Image#" + i));
                    Log.d("mytag", "... CARGANDO IMG " + i +" ...");
                }
                Log.d("mytag", "... GALERIA CARGADA ...");


                gridAdapter = new GridViewAdapter(cont, R.layout.grid_item_layout, imageItems);
                gridView.setAdapter(gridAdapter);
            }*/

    }

    class AbrirLayout extends Thread {
        private Intent i;
        private int req;

        public AbrirLayout(Intent i, int req) {
            this.i = i;
            this.req = req;
        }
        @Override
        public void run() {
            runOnUiThread(() -> {
                Log.d("mytag", "... ABRIENDO INTENT...");
                startActivityForResult(i,req);
            });
        }
    }


    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        Log.d("mytag","ESTOY EN CACHE");
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    Log.d("mytag","ERROR DE CACHE AL BORRAR");
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás

            /*Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();*/
            deleteCache(this);
            if (dir.exists() && blnCargado == false && dir.listFiles().length>0) {
                cg.interrupt();
            }

            finish();

        }
        return super.onKeyDown(keyCode, event);
    }
}
