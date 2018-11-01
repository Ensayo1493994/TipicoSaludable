package com.example.admin.myfirebaseapp.model;

/**
 * Created by ADMIN on 29/10/2018.
 */

public class Comida {

    private String uuid,nombre, carbohidratos, proteinas, imagen;
    private int caorias, cloriasconsumidas;

    public Comida() {
    }

    public Comida(String uuid, String nombre, String carbohidratos, String proteinas, String imagen, int caorias, int cloriasconsumidas) {
        this.uuid = uuid;
        this.nombre = nombre;
        this.carbohidratos = carbohidratos;
        this.proteinas = proteinas;
        this.imagen = imagen;
        this.caorias = caorias;
        this.cloriasconsumidas = cloriasconsumidas;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(String carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public String getProteinas() {
        return proteinas;
    }

    public void setProteinas(String proteinas) {
        this.proteinas = proteinas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCaorias() {
        return caorias;
    }

    public void setCaorias(int caorias) {
        this.caorias = caorias;
    }

    public int getCloriasconsumidas() {
        return cloriasconsumidas;
    }

    public void setCloriasconsumidas(int cloriasconsumidas) {
        this.cloriasconsumidas = cloriasconsumidas;
    }

    @Override
    public String toString() {
        return nombre;
    }
}