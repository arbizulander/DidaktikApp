package com.example.ik_2dm3.didaktikapp;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import static android.os.Environment.getExternalStorageDirectory;
import java.io.File;
import java.io.OutputStream;
import java.net.URI;
import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.Date;

public class galery extends AppCompatActivity {

    private static final int PERMISSION_CODE =1000;
    private static final int IMAGE_CAPTURE_CODE =1001;
    Button mCaptureBtn;
    ImageView miImageView;

    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        miImageView = findViewById(R.id.miImageView);
        mCaptureBtn = findViewById(R.id.capture_image);

        //button click
        mCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if(resultCode == RESULT_OK){
            //set the captured to our Imageview
            miImageView.setImageURI(image_uri);
        }
    }
}
