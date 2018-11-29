package com.example.ik_2dm3.didaktikapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String title = getIntent().getStringExtra("title");

        String ruta = getIntent().getStringExtra("rutaimg");

        File imgFile = new File(ruta);
        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


        //byte[] byteArray = getIntent().getExtras().getByteArray("image");
        //Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
       //img.setImageBitmap(bmp);

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
    }
}
