package com.sanchez.tipicosaludable;

/**
 * Created by lenovo on 20/11/2018.
 */

public class Deportes_firebase {

    private String Nombre;
    private  String Calorias;
    private  String Descripcion;
    private String Categoria;
    private String Duracion;
    private  String Imagen;



    public Deportes_firebase(String nombre, String calorias, String descripcion, String categoria, String duracion, String imagen) {
        Nombre = nombre;
        Calorias = calorias;
        Descripcion = descripcion;
        Categoria = categoria;
        Duracion = duracion;
        Imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCalorias() {
        return Calorias;
    }

    public void setCalorias(String calorias) {
        Calorias = calorias;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String duracion) {
        Duracion = duracion;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }
    public int getId() {
        return Nombre.hashCode();
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
