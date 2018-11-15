package com.example.ik_2dm3.didaktikapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;

public class funcionesComunes extends AppCompatActivity {


   /* public void Reproducir_cancion (Context cont, int ID){
        MediaPlayer mp = null;
        //ACCIONES AL ACABAR CANCION//
        mp = MediaPlayer.create(cont,ID);
        mp.start();
    }*/

    /*public void toImg(String byteArray, ImageView idImagen) throws IOException {

        byte[] decodedString = Base64.decode(byteArray, Base64.DEFAULT);
        //ImageView image = (ImageView) findViewById(R.id.imageView);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        Log.d("mytag","BITMAP: "+decodedByte);
        image.setImageBitmap(decodedByte);

    }*/

}
