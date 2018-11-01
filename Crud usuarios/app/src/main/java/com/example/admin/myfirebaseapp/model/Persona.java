package com.example.admin.myfirebaseapp.model;

/**
 * Created by ADMIN on 08/10/2018.
 */

public class Persona {
    private String uid;
    private String Nombre;
    private String Correo;
    private String Fecha;

    public Persona() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    @Override
    public String toString() {
        return Correo;
    }
}
