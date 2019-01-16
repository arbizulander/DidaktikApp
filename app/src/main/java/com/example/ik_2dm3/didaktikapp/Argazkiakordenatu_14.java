package com.example.ik_2dm3.didaktikapp;

import android.content.ClipData;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Argazkiakordenatu_14 extends AppCompatActivity {

    ImageView caja;
    private MediaPlayer mp ,mp1,mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkiakordenatu_14);


        ImageView puerto1 = findViewById(R.id.argazki1);
        ImageView puerto2 = findViewById(R.id.argazki2);
        ImageView puerto3 = findViewById(R.id.argazki3);
        ImageView puerto4 = findViewById(R.id.argazki4);


        ImageView testu1 = findViewById(R.id.testua1);
        ImageView testu2 = findViewById(R.id.testua2);
        ImageView testu3 = findViewById(R.id.testua3);
        ImageView testu4 = findViewById(R.id.testua4);

        //Recojer clicl listeners
        /*ºººººººººººººººClickListenerºººººººººººººººººººººººººººººººº*/
        testu1.setOnLongClickListener(ClickListener1);
        testu2.setOnLongClickListener(ClickListener2);
        testu3.setOnLongClickListener(ClickListener3);
        testu4.setOnLongClickListener(ClickListener4);
        /*ºººººººººººººººDragListener1ºººººººººººººººººººººººººººººººº*/
        puerto1.setOnDragListener(dragListener1);
        puerto2.setOnDragListener(dragListener2);
        puerto3.setOnDragListener(dragListener3);
        puerto4.setOnDragListener(dragListener4);




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

    View.OnLongClickListener ClickListener2 = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);

            return false;
        }
    };

    View.OnLongClickListener ClickListener3 = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);

            return false;
        }
    };

    View.OnLongClickListener ClickListener4 = new View.OnLongClickListener() {
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


                    if (view.getId() == R.id.testua1) {
                        Log.d("mytag", "ONDO ONDO ONDO ONDO ONDO");
                        caja = findViewById(R.id.testua1);
                        caja.setVisibility(view.GONE);
                        correcto();
                    }else{
                        Toast.makeText(getBaseContext(),"TXARTO:" , Toast.LENGTH_SHORT).show();
                        Log.d("mytag", "TXARTO TXARTO TXARTO TXARTO TXARTO");
                    }


            }
            return true;
        }
    };

    final View.OnDragListener dragListener2 = new View.OnDragListener() {
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


                    if (view.getId() == R.id.testua2) {
                        Log.d("mytag", "ONDO ONDO ONDO ONDO ONDO");
                        caja = findViewById(R.id.testua2);
                        caja.setVisibility(view.GONE);
                        correcto();
                    }else{
                        Toast.makeText(getBaseContext(),"TXARTO:" , Toast.LENGTH_SHORT).show();
                        Log.d("mytag", "TXARTO TXARTO TXARTO TXARTO TXARTO");
                    }


            }
            return true;
        }
    };

    final View.OnDragListener dragListener3 = new View.OnDragListener() {
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


                    if (view.getId() == R.id.testua3) {
                        Log.d("mytag", "ONDO ONDO ONDO ONDO ONDO");
                        caja = findViewById(R.id.testua3);
                        caja.setVisibility(view.GONE);
                        correcto();
                    }else{
                        Toast.makeText(getBaseContext(),"TXARTO:" , Toast.LENGTH_SHORT).show();
                        Log.d("mytag", "TXARTO TXARTO TXARTO TXARTO TXARTO");
                    }


            }
            return true;
        }
    };

    final View.OnDragListener dragListener4 = new View.OnDragListener() {
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


                    if (view.getId() == R.id.testua4) {
                        Log.d("mytag", "ONDO ONDO ONDO ONDO ONDO");
                        caja = findViewById(R.id.testua4);
                        caja.setVisibility(view.GONE);
                        correcto();
                    }else{
                        Toast.makeText(getBaseContext(),"TXARTO:" , Toast.LENGTH_SHORT).show();
                        Log.d("mytag", "TXARTO TXARTO TXARTO TXARTO TXARTO");
                    }

            }
            return true;
        }
    };

    public void correcto (){

        mp1 = MediaPlayer.create(this, R.raw.correct);
        mp1.start();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
           /* Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();*/
            //return true;
            mp.stop();
            if(mp2.isPlaying())
                mp2.stop();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
