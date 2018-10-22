package com.example.saranghae.crud_firebase_deportes.Model;

/**
 * Created by Saranghae on 4/10/2018.
 */

public class Deportes {

    private String Uuid;
    private String Nombre;
    private  String Calorias;
    private  String Descripcion;
    private String Categoria;
    private  String Imagen;


    public Deportes(){

    }

    public Deportes(String uuid, String nombre, String calorias, String descripcion, String categoria, String imagen) {
        Uuid = uuid;
        Nombre = nombre;
        Calorias = calorias;
        Descripcion = descripcion;
        Categoria = categoria;
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

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
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
                ", Calorias='" + Calorias + '\'' +
                ", Categoria='" + Categoria + '\'' +
                '}';
    }
}
