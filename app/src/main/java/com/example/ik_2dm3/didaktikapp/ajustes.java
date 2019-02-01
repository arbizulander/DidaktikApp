package com.example.ik_2dm3.didaktikapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ajustes extends AppCompatActivity {

    private Button btnReset,sortzaileak;
    private MyOpenHelper db;
    static final int REQ_TEXT = 0;
    private Button button;
    private EditText edittext;
    private TextView resultText;
    private String admin="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        //btnReset = findViewById(R.id.btnReset);
        sortzaileak = findViewById(R.id.btnSortzaileak);

/*
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
*/
        sortzaileak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), Sortzaileak.class);

                startActivityForResult(i, REQ_TEXT);


            }
        });

        // components from main.xml
        button = (Button) findViewById(R.id.btnReset);
        
        edittext = (EditText) findViewById(R.id.edittext);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });
    }

    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(ajustes.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ajustes.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                            //resultText.setText(editText.getText());
                        if(editText.getText().toString().equals(admin)){
                            reset();
                        }else{
                            Log.d("mytag","Contraseña incorrecta");
                        }



                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void reset(){
        db=new MyOpenHelper(this);
        try {
            db.ResetDatabase(this);
            Toast.makeText(getApplicationContext(), "Datu-basea berrabiarazi da",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.close();


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
