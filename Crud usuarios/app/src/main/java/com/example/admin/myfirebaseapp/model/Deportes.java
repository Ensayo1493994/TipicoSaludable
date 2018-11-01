package com.example.admin.myfirebaseapp.model;

/**
 * Created by APRENDIZ on 26/10/2018.
 */

public class Deportes {

    private String Uuid;
    private String Nombre;
    private  String Calorias;
    private  String Descripcion;
    private String Categoria;
    private String Duracion;
    private  String Imagen;


    public Deportes(){

    }

    public Deportes(String uuid, String nombre, String calorias, String descripcion,  String duracion, String imagen) {
        Uuid = uuid;
        Nombre = nombre;
        Calorias = calorias;
        Descripcion = descripcion;
        Duracion = duracion;
        Imagen = imagen;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
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

    @Override
    public String toString() {
        return "Deportes{" +
                "Nombre='" + Nombre + '\'' +
                '}';
    }
}