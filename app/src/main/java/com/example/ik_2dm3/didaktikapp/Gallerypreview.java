package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class Gallerypreview extends AppCompatActivity {

    ImageView GalleryPreviewImg;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.gallerypreview);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");

        Log.d("mytag", "ESTOY EN EL GALLERY PREVIEW");

        GalleryPreviewImg = (ImageView) findViewById(R.id.GalleryPreviewImg);
        Glide.with(Gallerypreview.this)
                .load(new File(path))
                .into(GalleryPreviewImg);
    }
}
