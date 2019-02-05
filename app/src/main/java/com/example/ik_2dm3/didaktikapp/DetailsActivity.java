package com.example.ik_2dm3.didaktikapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import java.io.File;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallerypreview);
        String title = getIntent().getStringExtra("title");
        setTitle(title);

        String ruta = getIntent().getStringExtra("rutaimg");

        File imgFile = new File(ruta);
        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
