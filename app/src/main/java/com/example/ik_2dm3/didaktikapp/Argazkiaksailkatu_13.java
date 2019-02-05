package com.example.ik_2dm3.didaktikapp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Argazkiaksailkatu_13 extends AppCompatActivity {


    private MediaPlayer mp ,mp1,mp2,mp3;
    ImageView caja;
    private ImageButton btnNext, btnPreviousGame;
    private Context cont = this;
    private MyOpenHelper db;
    private int pag_anterior;
    static final int REQ_BTN = 0;
    static final int REQ_BTNATRAS = 12;
    int Cont=0;
    int Cont2=0;
    int Cont3=0;

    BroadcastReceiver miBroadcast = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                Log.i("TAG", "Screen ON");
            }
            else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                Log.i("TAG", "Screen OFF");
                mp.stop();
                mp2.stop();
                mp1.stop();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_argazkiaksailkatu_13);

        //Aquí registras los eventos que quieres escuchar
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(miBroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));


        setTitle("Argazkiak sailkatu");
        btnPreviousGame = findViewById(R.id.btnPreviousGame);
        btnPreviousGame.setEnabled(false);
        btnPreviousGame.setVisibility(View.INVISIBLE);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setVisibility(View.INVISIBLE);


        mp2 = MediaPlayer.create(this, R.raw.musica_juego_13 );
        ImageView arpa = findViewById(R.id.arpa);
        ImageView flauta = findViewById(R.id.flauta);
        ImageView bateria = findViewById(R.id.bateria);
        ImageView pianoa = findViewById(R.id.pianoa);
        ImageView maraka = findViewById(R.id.maraka);
        ImageView violin = findViewById(R.id.violin);
        ImageView triangulo = findViewById(R.id.triangulo);
        ImageView clarinete = findViewById(R.id.clarinete);
        ImageView trompeta = findViewById(R.id.trompeta);


        /*TextView aire = findViewById(R.id.aire);
        TextView cuerda = findViewById(R.id.cuerda);
        TextView perkuzioa = findViewById(R.id.perkuzioa);*/

        ImageView cestaAirea = findViewById(R.id.cestaAire);
        ImageView cestaHarizkoa = findViewById(R.id.cestaHarizkoa);
        ImageView cestaPerkuzioa = findViewById(R.id.cestaPerkuzioa);

        //Recojer clicl listeners
        /*ºººººººººººººººAIREºººººººººººººººººººººººººººººººº*/
        flauta.setOnLongClickListener(ClickListener1);
        clarinete.setOnLongClickListener(ClickListener1);
        trompeta.setOnLongClickListener(ClickListener1);
        cestaAirea.setOnDragListener(dragListener1);
        /*ºººººººººººººººCUERDAºººººººººººººººººººººººººººººººº*/
        arpa.setOnLongClickListener(ClickListener2);
        pianoa.setOnLongClickListener(ClickListener2);
        violin.setOnLongClickListener(ClickListener2);
        cestaHarizkoa.setOnDragListener(dragListener2);
        /*ºººººººººººººººPERKUZIOAºººººººººººººººººººººººººººººººº*/
        bateria.setOnLongClickListener(ClickListener3);
        maraka.setOnLongClickListener(ClickListener3);
        triangulo.setOnLongClickListener(ClickListener3);
        cestaPerkuzioa.setOnDragListener(dragListener3);
/********************************************************************************/

        pag_anterior = getIntent().getIntExtra("pag_anterior", 0);

        switch (pag_anterior){
            case 0:
                break;
            case 1:
                btnNext.setEnabled(true);
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setOnClickListener(v -> {
                    mp.stop();
                    if(mp2.isPlaying())
                        mp2.stop();


                    finish();
                });

                btnPreviousGame.setEnabled(true);
                btnPreviousGame.setVisibility(View.VISIBLE);
                btnPreviousGame.setOnClickListener(v -> {
                    mp.stop();
                    if(mp2.isPlaying())
                        mp2.stop();
                    Intent i = new Intent(Argazkiaksailkatu_13.this,Argazkiaktaulansailkatu_12.class);
                    i.putExtra("pag_anterior",1);
                    startActivityForResult(i, REQ_BTN);
                    finish();
                });

                break;
        }


        arpa.setEnabled(false);
        pianoa.setEnabled(false);
        violin.setEnabled(false);
        flauta.setEnabled(false);
        clarinete.setEnabled(false);
        trompeta.setEnabled(false);
        bateria.setEnabled(false);
        maraka.setEnabled(false);
        triangulo.setEnabled(false);

        /*ºººººººººººººººººººººººMEDIAPLAYERººººººººººººººººººººº*/
        mp = MediaPlayer.create(this, R.raw.a4_4);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                arpa.setEnabled(true);
                pianoa.setEnabled(true);
                violin.setEnabled(true);
                flauta.setEnabled(true);
                clarinete.setEnabled(true);
                trompeta.setEnabled(true);
                bateria.setEnabled(true);
                maraka.setEnabled(true);
                triangulo.setEnabled(true);
                musica_fondo();


                Log.d("mytag","Dentro del onCompletion");



            }
        });
        mp.start();
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
            Log.d("mytag","CESTA AIRE");
            final View view = (View) event.getLocalState();
            int dragEvent = event.getAction();


            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("mytag","CESTA AIRE -- ACTION_DRAG_ENTERED");
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d("mytag","CESTA AIRE -- ACTION_DRAG_EXITED");
                    break;
                //ACCION QUE HACE CUANDO TERMINA EL DRAG I LOS SUELTAS EN ALGUNA BIÑETA CON DRAG LSITENER
                case DragEvent.ACTION_DROP:
                    Log.d("mytag","CESTA AIRE -- ACTION_DROP");
                    Log.d("mytag","CESTA AIRE<--------------- ACTION DROP");

                    if(view.getId()== R.id.arpa || view.getId()== R.id.bateria || view.getId()== R.id.triangulo ||
                            view.getId()== R.id.maraka || view.getId()== R.id.pianoa){
                        Toast.makeText(getBaseContext(),"Txarto!!" , Toast.LENGTH_SHORT).show();
                        fallo();
                    }

                    if (view.getId() == R.id.flauta) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.flauta);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont ++;
                    }
                    if (view.getId() == R.id.trompeta) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.trompeta);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont ++;
                    }
                    if (view.getId() == R.id.clarinete) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.clarinete);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont ++;
                    }
                    if(Cont==9){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        switch (pag_anterior){
                            case 0:

                                int i = 13;
                                db=new MyOpenHelper(cont);
                                db.ActualizarJuego_Id(i);
                                db.close();
                                Log.d("mytag","AL ACABAR EL JUEGO FINALIZO Y VUELVO AL LISTADO PARA CARGAR SIGUIENTE...");
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result",1);
                                setResult(Activity.RESULT_OK,returnIntent);
                                mp2.stop();
                                finish();
                                break;
                            case 1:
                                Log.d("mytag","El juego se ha acabado y se inicio desde lista");
                                break;
                        }

                    }

            }
            return true;
        }
    };

    //LONGCLICK LISTENER

    View.OnLongClickListener ClickListener2 = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);

            Log.d("mytag", "CUERDA CUERDA");

            return false;
        }
    };

    /*ºººººººººººººººCUERDAºººººººººººººººººººººººººººººººº*/

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


                    if(view.getId()== R.id.clarinete || view.getId()== R.id.bateria || view.getId()== R.id.triangulo ||
                            view.getId()== R.id.maraka || view.getId()== R.id.flauta || view.getId()== R.id.trompeta ){
                        Toast.makeText(getBaseContext(),"Txarto!!" , Toast.LENGTH_SHORT).show();
                        fallo();
                    }

                    if (view.getId() == R.id.arpa) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.arpa);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont ++;
                    }

                    if (view.getId() == R.id.violin) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.violin);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont ++;
                    }
                    if (view.getId() == R.id.pianoa) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.pianoa);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont ++;
                    }
                    if(Cont ==9){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        switch (pag_anterior){
                            case 0:
                                int i = 11;
                                db=new MyOpenHelper(cont);
                                db.ActualizarJuego_Id(i);
                                db.close();
                                Log.d("mytag","AL ACABAR EL JUEGO FINALIZO Y VUELVO AL LISTADO PARA CARGAR SIGUIENTE...");
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result",1);
                                setResult(Activity.RESULT_OK,returnIntent);
                                finish();
                                break;
                            case 1:
                                Log.d("mytag","El juego se ha acabado y se inicio desde lista");
                                break;
                        }

                    }




            }
            return true;
        }
    };


    //LONGCLICK LISTENER

    View.OnLongClickListener ClickListener3 = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, myShadowBuilder, v, 0);



            return false;
        }
    };

    /*ºººººººººººººººPERKUZIOAºººººººººººººººººººººººººººººººº*/

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

                    if(view.getId()== R.id.arpa || view.getId()== R.id.pianoa || view.getId()== R.id.flauta
                            || view.getId()== R.id.clarinete || view.getId()== R.id.violin || view.getId()== R.id.trompeta   ){
                        Toast.makeText(getBaseContext(),"Txarto!!" , Toast.LENGTH_SHORT).show();
                        fallo();

                    }


                    if (view.getId() == R.id.bateria) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.bateria);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont ++;
                    }
                    if (view.getId() == R.id.maraka) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.maraka);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont ++;
                    }
                    if (view.getId() == R.id.triangulo) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Oso ondo!!", Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("mytag", "ID ARPA"+view.getId());
                        caja = findViewById(R.id.triangulo);
                        caja.setVisibility(view.GONE);
                        correcto();
                        Cont++;
                    }
                    if(Cont ==9){
                        if(Cont ==9){
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Oso ondo!!", Toast.LENGTH_SHORT);
                            toast.show();
                            switch (pag_anterior){
                                case 0:
                                    int i = 11;
                                    db=new MyOpenHelper(cont);
                                    db.ActualizarJuego_Id(i);
                                    db.close();
                                    Log.d("mytag","AL ACABAR EL JUEGO FINALIZO Y VUELVO AL LISTADO PARA CARGAR SIGUIENTE...");
                                    Intent returnIntent = new Intent();
                                    returnIntent.putExtra("result",1);
                                    setResult(Activity.RESULT_OK,returnIntent);
                                    finish();
                                    break;
                                case 1:
                                    Log.d("mytag","El juego se ha acabado y se inicio desde lista");
                                    break;
                            }

                        }


                    }

            }
            return true;
        }
    };


    public void correcto (){

        mp1 = MediaPlayer.create(this, R.raw.correct);
        mp1.start();


    }

    public void fallo (){

        mp1 = MediaPlayer.create(this, R.raw.fail);
        mp1.start();
    }

    public void musica_fondo (){


        mp2.start();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás
           /* Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();*/
            //return true;
            if (pag_anterior == 0){
                Intent i = new Intent();
                i.putExtra("keydown",REQ_BTNATRAS);
                setResult(RESULT_OK,i);
            }
            mp.stop();
            if(mp2.isPlaying())
                mp2.stop();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
