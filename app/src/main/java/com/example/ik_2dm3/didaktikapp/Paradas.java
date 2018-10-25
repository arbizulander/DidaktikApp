package com.example.ik_2dm3.didaktikapp;

public class Paradas {

    private int id_parada;
    private String nombre;
    private double latitud;
    private double longitud;
    private boolean realizado;
    private byte[] imagen;

    public Paradas (int id, String nom, double lat, double longi, boolean real, byte[] img){
        id_parada = id;
        nombre = nom;
        latitud = lat;
        longitud = longi;
        realizado = real;
        imagen = img;
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String toString() {
        return nombre;
    }
}
