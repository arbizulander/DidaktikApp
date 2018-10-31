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

        PopUp(contenido);

    }

    public void PopUp(View v){
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
    }

    public void abrirCamara (){

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("galery", this.MODE_APPEND);

        File image = new File(directory,"img" + getTimeStamp() + ".jpg");
        uriSavedImage = Uri.fromFile(image);

        Log.d("mytag", uriSavedImage.toString());

        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
        //i.putExtra("return-data", true);
        startActivityForResult(i, CAMERA_PIC_REQUEST);

        /*SimpleDateFormat df;
        Calendar c;
        File path = new File( Environment.getExternalStorageDirectory(), this.getPackageName());
        String fecha;
        String nombrefoto;
        Uri ImageUri;


        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        c = Calendar.getInstance();
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        fecha = df.format(c.getTime());
        nombrefoto = "prueba.jpg"; //md5(datosUsuarios.nombre_usuario + fecha) +

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("galery", this.MODE_APPEND);

        File file = new File(directory,"prueba.jpg");
        Log.d("mytag",file.toString());
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(i, CAMERA_PIC_REQUEST);*/

        //startActivityForResult(i,0);
        //ocultar();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode)

        {

            case CAMERA_PIC_REQUEST:

                if(resultCode==RESULT_OK)
                {
                    Log.d("mytag", "OnActivityResult: " + uriSavedImage.toString());
                    handleSmallCameraPhoto(uriSavedImage);
                }
                break;

        }

    }

    private void handleSmallCameraPhoto(Uri uri)
    {
        Bitmap bmp=null;

        try {
            bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        }
        catch (FileNotFoundException e)
        {

            e.printStackTrace();
        }


        /*Intent displayIntent = new Intent(this, DisplayPhotoActivity.class);
        displayIntent.putExtra("BitmapImage", bmp);
        startActivity(displayIntent);*/


    }

    private String appFolderCheckandCreate(){

        String appFolderPath="";
        File externalStorage = Environment.getExternalStorageDirectory();

        if (externalStorage.canWrite())
        {
            appFolderPath = externalStorage.getAbsolutePath() + "/MyApp";
            File dir = new File(appFolderPath);

            if (!dir.exists())
            {
                dir.mkdirs();
            }

        }
        else
        {
            //showToast("  Storage media not found or is full ! ");
        }

        return appFolderPath;
    }



    private String getTimeStamp() {

        final long timestamp = new Date().getTime();

        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);

        final String timeString = new SimpleDateFormat("HH_mm_ss_SSS").format(cal.getTime());


        return timeString;
    }

    /*private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_APPEND);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");
        Log.d("directorio", directory.toString());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }*/

    //comprobamo si tenemos permisos para usar la camara del movil
    /*private void checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            //abrirCamara();
            Log.i("Mensaje", "No se tiene permiso para la camara!.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 225);
        } else {
            abrirCamara();
            Log.i("Mensaje", "Tienes permiso para usar la camara.");
        }
    }*/

    public void toImg(String byteArray) throws IOException {

        byte[] decodedString = Base64.decode(byteArray, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //Convert bitmap to drawable
        Drawable drawable = new BitmapDrawable(getResources(), decodedByte);
        contenido.setBackground(drawable);
    }
}
