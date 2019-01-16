package com.example.ik_2dm3.didaktikapp;

import android.content.ClipData;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Argazkiakordenatu_14 extends AppCompatActivity {

    private MediaPlayer mp ,mp1,mp2;
    ImageView caja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkiakordenatu_14);


        ImageView puerto1 = findViewById(R.id.argazki1);
        ImageView puerto2 = findViewById(R.id.argazki2);
        ImageView puerto3 = findViewById(R.id.argazki3);
        ImageView puerto4 = findViewById(R.id.argazki4);


        TextView testu1 = findViewById(R.id.testua1);
        TextView testu2 = findViewById(R.id.testua2);
        TextView testu3 = findViewById(R.id.testua3);
        TextView testu4 = findViewById(R.id.testua4);

        //Recojer clicl listeners
        /*ºººººººººººººººAIREºººººººººººººººººººººººººººººººº*/
        puerto1.setOnLongClickListener(ClickListener1);
        testu1.setOnDragListener(dragListener1);




    }

    //LONGCLICK LISTENER

    View.OnLongClickListener ClickListener1 = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);



            return false;
        }
    };
    /*ºººººººººººººººAIREºººººººººººººººººººººººººººººººº*/
    final View.OnDragListener dragListener1 = new View.OnDragListener() {
        //ACCIONES QUE HACE DESPUES DEL LONGCLICK
        @Override
        public boolean onDrag(View v, DragEvent event) {
            final View view = (View) event.getLocalState();
            int dragEvent = event.getAction();


            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                //ACCION QUE HACE CUANDO TERMINA EL DRAG I LOS SUELTAS EN ALGUNA BIÑETA CON DRAG LSITENER
                case DragEvent.ACTION_DROP:
                    //Meter los alimentos en la bolsa

                    if(view.getId()== R.id.testua2 || view.getId()== R.id.testua3 || view.getId()== R.id.testua4){
                        Toast.makeText(getBaseContext(),"TXARTO:" , Toast.LENGTH_SHORT).show();

                    }

                    if (view.getId() == R.id.testua1) {
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.testua1);
                        caja.setVisibility(view.GONE);
                        correcto();
                    }
                    if (view.getId() == R.id.trompeta) {
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.trompeta);
                        caja.setVisibility(view.GONE);
                        correcto();
                    }
                    if (view.getId() == R.id.clarinete) {
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.clarinete);
                        caja.setVisibility(view.GONE);
                        correcto();
                    }

            }
            return true;
        }
    };

    public void correcto (){

        mp1 = MediaPlayer.create(this, R.raw.correct);
        mp1.start();


    }
}
