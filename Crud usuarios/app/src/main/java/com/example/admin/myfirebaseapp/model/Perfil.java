package com.example.admin.myfirebaseapp.model;

/**
 * Created by ADMIN on 29/10/2018.
 */

public class Perfil {
    private String uid;
    private String Actividad;
    private String Calorías_maximas;
    private String Contextura;
    private String Edad;
    private String Genero;
    private String Imc;
    private String Peso;
    private String Talla;
    public Perfil() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }

    public String getCalorías_maximas() {
        return Calorías_maximas;
    }

    public void setCalorías_maximas(String calorías_maximas) {
        Calorías_maximas = calorías_maximas;
    }

    public String getContextura() {
        return Contextura;
    }

    public void setContextura(String contextura) {
        Contextura = contextura;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getImc() {
        return Imc;
    }

    public void setImc(String imc) {
        Imc = imc;
    }

    public String getPeso() {
        return Peso;
    }

    public void setPeso(String peso) {
        Peso = peso;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    @Override
    public String toString() {
        return Actividad;
    }
}
