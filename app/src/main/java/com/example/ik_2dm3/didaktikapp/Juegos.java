package com.example.ik_2dm3.didaktikapp;

public class Juegos {

    private int id_juego;
    private boolean realizado;
    private String nombre_juego;
    private int id_parada;

    public Juegos (int id, String nom, boolean real, int id_par){
        id_juego = id;
        nombre_juego = nom;
        realizado = real;
        id_parada = id_par;
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public String getNombre_juego() {
        return nombre_juego;
    }

    public void setNombre_juego(String nombre_juego) {
        this.nombre_juego = nombre_juego;
    }

    public int getId_parada() {
        return id_parada;
    }

    public void setId_parada(int id_parada) {
        this.id_parada = id_parada;
    }

    public String toString() {
        return nombre_juego;
    }
}