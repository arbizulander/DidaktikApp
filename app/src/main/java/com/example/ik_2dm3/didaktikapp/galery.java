package com.example.ik_2dm3.didaktikapp;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.pm.PackageManager.PERMISSION_DENIED;

public class galery extends AppCompatActivity {
    //Button btnHome;

    private ArrayList<Paradas> lista_paradas;
    private ListView paradasView;

    //BD
    private MyOpenHelper db;

    private Button btnCamara;

    static final int REQ_BTN = 0;
    static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;

    ImageView imgTakenPic;
    private static final int CAM_REQUEST=1313;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        db=new MyOpenHelper(this);
        lista_paradas = db.getDatos_Paradas();
        String data = lista_paradas.get(0).getImagen();
        //Log.d("mytag",lista_paradas.get(0).getImagen().toString());
        //Log.d("mytag", "ARRAY DE BYTE IMAGEN:"+data);
        //btnHome = (Button) findViewById(R.id.btnHome);
        btnCamara = (Button) findViewById(R.id.btnCamara);
        imgTakenPic = (ImageView) findViewById(R.id.imageView);
        /*try{
           toImg(data);
        }catch (IOException e){

        }*/

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"Fotos didaktikapp.jpg"));
                //i.putExtra("return-data", true);
                startActivityForResult(i, CAM_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck == PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            Log.d("myTag", "ventana de pedir acceso");

        }

        if(resultCode != RESULT_CANCELED) {
            if (requestCode == CAM_REQUEST) {

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgTakenPic.setImageBitmap(bitmap);

                dispatchTakePictureIntent();

                //String ruta = guardarImagen(this, "prueba" + i, bitmap);
                createDirectoryAndSaveFile(bitmap,"Fotos didaktikapp.png");
                //Log.d("mytag",ruta);

            }
        }
    }

    private String guardarImagen (Context context, String nombre, Bitmap imagen){
        ContextWrapper cw = new ContextWrapper(context);
        int i = 0;
        i++;
        File dirImages = cw.getDir("galery", Context.MODE_PRIVATE);
        File myPath = new File(dirImages, "prueba" + i + ".jpg");

        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(myPath);
            //imagen.getRowBytes();
            imagen.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            //imagen.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        createDirectoryAndSaveFile(imagen,myPath.getName());
        return myPath.getAbsolutePath();

    }


    /*public void toImg(String byteArray) throws IOException {

        byte[] decodedString = Base64.decode(byteArray, Base64.DEFAULT);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        Log.d("mytag","BITMAP: "+decodedByte);
        image.setImageBitmap(decodedByte);

    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
            Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();
            //return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"FotosDidaktikApp");
        int result = 0;

        if (direct.exists()) {
            Log.d("myTag","directorio existe en:" + Environment.getExternalStorageDirectory().getAbsolutePath()+"/FotosDidaktikApp");
            result = 2;//el fichero existe
        }else{
            try {
                if (direct.mkdir()) {
                    Log.d("myAppName", "directorio creado en:" + direct.toString());
                    result = 1; // se ha creado fichero
                } else {
                    Log.d("myAppName", "creat folder fails:" + direct.toString());
                    result = 0; // fallo al crear fichero
                }
            }catch (Exception ecp){
                ecp.printStackTrace();
            }
        }

        File file = new File(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/FotosDidaktikApp"), fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(file);

            imageToSave.compress(Bitmap.CompressFormat.PNG, 100, out);
            Log.d("myTag","Imagen comprimida y guardada en "+Environment.getExternalStorageDirectory().getAbsolutePath()+"/FotosDidaktikApp");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

}
