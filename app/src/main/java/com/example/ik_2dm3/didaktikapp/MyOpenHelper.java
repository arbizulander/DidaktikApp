package com.example.ik_2dm3.didaktikapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyOpenHelper extends SQLiteOpenHelper {

    //Creamos los Strings con la ruta del archivo de BD
    private static String DB_PATH = "/data/data/com.example.ik_2dm3.didaktikapp/databases/";
    private static String DB_NAME = "Database2.db";

    private static final int DB_VERSION = 3;
    private SQLiteDatabase db;


    public MyOpenHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);

      try{
            copyDataBase(context);
        } catch(IOException e){
            Log.d("mytag", "ERROR COPYDATABASE");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("mytag", "ESTOY EN ONCREATE MYOPENHELPER");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * *******************************************
     * Copies your database from your local
     * assets-folder to the just created empty
     * database in the system folder, from
     * where it can be accessed and handled.
     * This is done by transferring bytestream.
     * *******************************************
     */
    //Copiamos el archivo BD que tenemos guardado en Assest a la memoria local del movil
    private void copyDataBase(Context context) throws IOException {


        String DB_PATH = context.getDatabasePath(DB_NAME).getPath();
        Log.d("mytag", DB_PATH);
        // Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the input file to the output file
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        /*Static.getSharedPreference(context).edit()
                .putInt("DB_VERSION", DB_VERSION).commit();*/
    }

    //Insertar un nuevo comentario
    public void insertar(String nombre,String comentario){
        /*ContentValues cv = new ContentValues();
        cv.put("user", nombre);
        cv.put("comment", comentario);
        db.insert("comments", null, cv);*/
    }

    //Borrar un comentario a partir de su id
    public void borrar(int id){
        //String[] args = new String[]{String.valueOf(id)};
        //db.delete("comments", "_id=?", args);
    }

    //Obtener la lista de comentarios en la base de datos
    public ArrayList<Paradas> getDatos_Paradas(){
            String myPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            db = this.getWritableDatabase();

            //Creamos el cursor
            ArrayList <Paradas>Paradalista = new ArrayList<Paradas>();
        Cursor c = db.rawQuery("SELECT * FROM paradas", null);

            if (c != null && c.getCount()>0) {
                c.moveToFirst();
                do {
                    //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                    int id=c.getInt(c.getColumnIndex("id_parada"));
                    String user = c.getString(c.getColumnIndex("nombre"));
                    double lat = c.getDouble(c.getColumnIndex("latitud"));
                    double longi = c.getDouble(c.getColumnIndex("longitud"));
                    int real = c.getInt(c.getColumnIndex("realizado"));
                    byte[] imagen = c.getBlob(c.getColumnIndex("imagen"));

                    boolean reali = (real!=0);

                    Paradas com =new Paradas(id,user,lat,longi,reali,imagen);
                    //Añadimos el comentario a la lista
                    Paradalista.add(com);
                    Log.d("mytag", ""+user);
                    //Log.d(com);
                } while (c.moveToNext());
            }

        //Cerramos el cursor
        c.close();
        //cursor.close();
            db.close();
        return Paradalista;
    }


}
