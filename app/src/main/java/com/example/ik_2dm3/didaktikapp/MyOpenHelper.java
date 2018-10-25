package com.example.ik_2dm3.didaktikapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.ik_2dm3.didaktikapp/databases/";
    private static String DB_NAME = "Database2.db";

    //private static final String COMMENTS_TABLE_CREATE = "CREATE TABLE comments(_id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, comment TEXT)";
    //private static final String DB_NAME = "comments.sqlite";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /*private void openDataBase () throws SQLException{
        String myPath = DB_PATH + DB_NAME;
        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insertar un nuevo comentario
    public void insertar(String nombre,String comentario){
        ContentValues cv = new ContentValues();
        cv.put("user", nombre);
        cv.put("comment", comentario);
        db.insert("comments", null, cv);
    }

    //Borrar un comentario a partir de su id
    public void borrar(int id){
        String[] args = new String[]{String.valueOf(id)};
        db.delete("comments", "_id=?", args);
    }

    //Obtener la lista de comentarios en la base de datos
    public ArrayList<Paradas> getNombres(){
            //Creamos el cursor
            ArrayList <Paradas>Paradalista = new ArrayList<Paradas>();
            Cursor c = db.rawQuery("select * from comments", null);
            if (c != null && c.getCount()>0) {
                c.moveToFirst();
                do {
                    //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                    int id=c.getInt(c.getColumnIndex("_id"));
                    String user = c.getString(c.getColumnIndex("user"));
                    double lat = c.getDouble(c.getColumnIndex("latitud"));
                    double longi = c.getDouble(c.getColumnIndex("longitud"));
                    int real = c.getInt(c.getColumnIndex("realizado"));
                    byte[] imagen = c.getBlob(c.getColumnIndex("imagen"));

                    boolean reali = (real!=0);

                    Paradas com =new Paradas(id,user,lat,longi,reali,imagen);
                    //Añadimos el comentario a la lista
                    Paradalista.add(com);
                } while (c.moveToNext());
            }

        //Cerramos el cursor
        c.close();
        return Paradalista;
    }


}
