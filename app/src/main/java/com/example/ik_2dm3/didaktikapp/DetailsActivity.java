package com.example.ik_2dm3.didaktikapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String title = getIntent().getStringExtra("title");
        setTitle(title);

        String ruta = getIntent().getStringExtra("rutaimg");

        File imgFile = new File(ruta);
        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


        //byte[] byteArray = getIntent().getExtras().getByteArray("image");
        //Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
       //img.setImageBitmap(bmp);

        //TextView titleTextView = (TextView) findViewById(R.id.title);
        //titleTextView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            //deleteCache(this);
            /*Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();*/
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
