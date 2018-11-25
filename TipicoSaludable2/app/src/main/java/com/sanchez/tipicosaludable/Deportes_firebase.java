package com.sanchez.tipicosaludable;

/**
 * Created by lenovo on 20/11/2018.
 */

public class Deportes_firebase {

    private String nombre;
    private  String calorias;
    private String categoria;
    private String duracion;
    private  String imagen;

    public Deportes_firebase() {
    }

    public Deportes_firebase(String nombre, String calorias, String categoria, String duracion, String imagen) {
        this.nombre = nombre;
        this.calorias = calorias;
        this.categoria = categoria;
        this.duracion = duracion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return nombre.hashCode();
    }
    public static Deportes_firebase getItem(int id){
        for (Deportes_firebase item : Deportesfirebase.listadeportes){
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }
}
