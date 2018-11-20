package com.example.ik_2dm3.didaktikapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Desberdintasunabilatu_4_argazki3 extends AppCompatActivity {

    Button Atzera;
    static final int REQ_TEXT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desberdintasunabilatu_4_argazki3);

        Atzera = (Button) findViewById(R.id.atz_argazki3);

        Atzera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(), Desberdintasunabilatu_4_argazki2.class);

                startActivityForResult(i, REQ_TEXT);




            }
        });
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
