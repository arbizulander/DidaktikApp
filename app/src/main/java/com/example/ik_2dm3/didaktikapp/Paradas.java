package com.example.ik_2dm3.didaktikapp;

import java.sql.Blob;

public class Paradas {

    private int id_parada;
    private String nombre;
    private double latitud;
    private double longitud;
    private boolean realizado;
    private String imagen;
    private boolean sacarFoto;
    private String texto;

    public Paradas(){

    }


    public Paradas (int id, String nom, double lat, double longi, boolean real, String img, boolean scrFoto, String txt){
        id_parada = id;
        nombre = nom;
        latitud = lat;
        longitud = longi;
        realizado = real;
        imagen = img;
        sacarFoto = scrFoto;
        texto = txt;
    }

    public int getId_parada() {
        return id_parada;
    }

    public void setId_parada(int id_parada) {
        this.id_parada = id_parada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public boolean isSacarFoto() {
        return sacarFoto;
    }

    public void setSacarFoto(boolean sacarFoto) {
        this.sacarFoto = sacarFoto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String toString() {
        return nombre;
    }
}
