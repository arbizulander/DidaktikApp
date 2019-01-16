package com.example.ik_2dm3.didaktikapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import org.w3c.dom.Text;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class list extends AppCompatActivity {

    //array donde guardaremos los titulos,imagenes de las paradas
    private String[] titulo;
    private ArrayList<ImageItem> imagenes = new ArrayList<ImageItem>();

    //Aqui guardaremos los datos de la BD
    private ArrayList<Paradas> lista_paradas;
    private ListView paradasView;

    //BD
    private MyOpenHelper db;
    private int REQ_OK =  0;
    private GridView gridView;
    LoadAlbumImages loadAlbumTask;

    private Context cont = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Titulo cabecera pagina
        setTitle("Ondareak");

        gridView = findViewById(R.id.gridView);
        //paradasView = findViewById(R.id.paradas_lista);

        //Cogemos todos los nombres de las paradas que hay en la BD

        //Los pasamos a un array para poder hacer el ArrayAdapter con el ListView
        //titulo = new String [lista_paradas.size()];


        //Pasamos array al ArrayAdapter para que salga en el ListView
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titulo);
        paradasView.setAdapter(adapter);

        paradasView.setOnItemClickListener((parent, view, position, id) -> {
            int item = position;

            Intent intent = new Intent(list.this, details_list.class);
            int prueba = lista_paradas.get(item).getId_parada();

            //meto el id en los extras para saber que parada es
            intent.putExtra("id_parada", prueba);
            intent.putExtra("pag_anterior",1);
            startActivityForResult(intent, REQ_OK);
        });*/


        int iDisplayWidth = getResources().getDisplayMetrics().widthPixels;
        Resources resources = getApplicationContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = iDisplayWidth / (metrics.densityDpi / 160f);

        if(dp < 360)
        {
            dp = (dp - 17) / 2;
            float px = com.example.ik_2dm3.didaktikapp.Function.convertDpToPixel(dp, getApplicationContext());
            gridView.setColumnWidth(Math.round(px));
        }

        loadAlbumTask = new LoadAlbumImages();
        loadAlbumTask.execute();

    }

    public Bitmap toImg(byte[] byteArray) throws IOException {
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
       /* View convertView = LayoutInflater.from(this).inflate(
                R.layout.grid_item_card_list, parent, false);*/
        //ImageView image = (ImageView) findViewById(R.id.galleryImage);
        //image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(), image.getHeight(), false));
        //image.setImageResource(R.drawable.error);
        return bmp;
    }


    class LoadAlbumImages extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            imagenes.clear();

        }

        protected String doInBackground(String... args) {
            Log.d("mytag", "ESTOY EN EL DOINBACKGROUND");
            String xml = "";

            db=new MyOpenHelper(cont);
            lista_paradas = db.getDatos_Paradas();
            db.close();

            //imagenes = new ImageItem();
            for (int i = 0; i<lista_paradas.size(); i++){
                //titulo[i] = (i+1)+ "." + lista_paradas.get(i).getNombre();
                byte [] data = lista_paradas.get(i).getImagen().getBytes();
                byte[] decodedString = Base64.decode(data, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeResource(getResources(), R.drawable.blank);
                if (data == null){
                    Log.d("mytag","IMAGEN DE ERROR");
                    decodedByte = BitmapFactory.decodeResource(getResources(),
                            R.drawable.error);
                }else{
                    decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                }
                //Log.d("mytag", "AÑADIENDO A IMANES: "+ lista_paradas.get(i).getImagen());
                //Bitmap decodedByte = BitmapFactory.decodeByteArray(data, 0, data.length);
                Log.d("mytag", "BYTE ARRAY: "+data.length);

                //Log.d("mytag", "BMP: "+bmp.toString());
                imagenes.add(new ImageItem(decodedByte, lista_paradas.get(i).getNombre()));
            }

            /*db=new MyOpenHelper(cont);
            lista_paradas = db.getDatos_Paradas();
            db.close();

            titulo = new String [lista_paradas.size()];*/

            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            list.LoadAlbumImages.SingleAlbumAdapter adapter = new list.LoadAlbumImages.SingleAlbumAdapter(list.this, imagenes);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        final int position, long id) {
                    Log.d("mytag", "ESTOY DENTRO DEL ON POST EXECUTE");
                    Log.d("mytag", "POSICION DE IMAGEN: "+position);

                    try{
                        Intent intent = new Intent(list.this, details_list.class);
                        int prueba = lista_paradas.get(position).getId_parada();

                        //meto el id en los extras para saber que parada es
                        intent.putExtra("id_parada", prueba);
                        intent.putExtra("pag_anterior",1);
                        startActivityForResult(intent, REQ_OK);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

        /*public void aniadir_imgGaleria (){

            files = dir.listFiles();
            File imgFile = new File(files[files.length-1].toString());

            Log.d("mytag","RUTA AL VOLVER DE LA CAMARA de ultima img:  " + imgFile);

            HashMap<String, String> map = new HashMap<String, String>();

            Long tsLong = System.currentTimeMillis()/1000;
            String ts = tsLong.toString();

            map.put("album_name", "DidaktikApp");
            map.put("path", imgFile.toString());
            map.put("timestamp", ts);
            map.put("date", null);
            map.put("date", null);

            imageList.add(map);
        }*/

        class SingleAlbumAdapter extends BaseAdapter {
            private Activity activity;
            private ArrayList<ImageItem> data;

            public SingleAlbumAdapter(Activity a, ArrayList<ImageItem> d) {
                activity = a;
                data = d;
            }
            public int getCount() {
                return data.size();
            }
            public Object getItem(int position) {
                return position;
            }
            public long getItemId(int position) {
                return position;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                list.LoadAlbumImages.SingleAlbumViewHolder holder = null;
                if (convertView == null) {
                    holder = new list.LoadAlbumImages.SingleAlbumViewHolder();
                    convertView = LayoutInflater.from(activity).inflate(
                            R.layout.grid_item_card_list, parent, false);

                    holder.galleryImage = (ImageView) convertView.findViewById(R.id.galleryImage);
                    holder.nombre_parada = (TextView) convertView.findViewById(R.id.nombre_parada);

                    convertView.setTag(holder);
                } else {
                    holder = (list.LoadAlbumImages.SingleAlbumViewHolder) convertView.getTag();
                }
                holder.galleryImage.setId(position);
                holder.nombre_parada.setId(position);

                ImageItem song = new ImageItem();
                song = data.get(position);
                Log.d("mytag","IMAGEN: "+position+"   "+song.getImage());
                Drawable d = new BitmapDrawable(getResources(), song.getImage());
                //TextView txt = new TextView(activity);
                //txt.setText(song.getTitle());

                try {
                    //Glide.with(activity).load((song.getImage()).r.d);

                    Glide.with(activity).load(d).into(holder.galleryImage);
                    holder.nombre_parada.setText(song.getTitle());
                    //Glide.with(activity).load(txt).into(holder.nombre_parada);

                } catch (Exception e) {}
                return convertView;
            }

        }
        class SingleAlbumViewHolder {
            ImageView galleryImage;
            TextView nombre_parada;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQ_OK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Log.d("mytag","Vuelves de lista detallada");

            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // Esto es lo que hace mi botón al pulsar ir a atrás
            /*Toast.makeText(getApplicationContext(), "Voy hacia atrás!!",
                    Toast.LENGTH_SHORT).show();*/
        }
        return super.onKeyDown(keyCode, event);
    }
}
