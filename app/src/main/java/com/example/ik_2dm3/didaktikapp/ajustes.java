package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class ajustes extends AppCompatActivity {

    private Button btnReset,sortzaileak;
    private MyOpenHelper db;
    static final int REQ_TEXT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        btnReset = findViewById(R.id.btnReset);
        sortzaileak = findViewById(R.id.btnSortzaileak);

        btnReset.setOnClickListener(v -> {
            db=new MyOpenHelper(this);
            try {
                db.ResetDatabase(this);
                Toast.makeText(getApplicationContext(), "Base de Datos reseteada",
                        Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            db.close();
        });

        sortzaileak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getBaseContext(), Sortzaileak.class);

                startActivityForResult(i, REQ_TEXT);



            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
           /* Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();*/
            //return true;
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQ_TEXT:
                if (resultCode==RESULT_OK){
                    Log.d("mifiltro","todo bien text");
                }else {
                    Log.d("mifiltro","error text");
                }
                break;

            default:
        }
    }

}
