package com.sanchez.tipicosaludable.model;

/**
 * Created by ADMIN on 26/10/2018.
 */

public class Historial {
    private String uid;
    private String Calorías_consumidas;
    private String Calorías_excedentes;
    private String Calorías_finales;
    private String Calorías_máximas;
    private String Fecha;
    private String Nombre_usuario;
    private String conten;
    public Historial() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCalorías_consumidas() {
        return Calorías_consumidas;
    }

    public void setCalorías_consumidas(String calorías_consumidas) {
        Calorías_consumidas = calorías_consumidas;
    }

    public String getCalorías_excedentes() {
        return Calorías_excedentes;
    }

    public void setCalorías_excedentes(String calorías_excedentes) {
        Calorías_excedentes = calorías_excedentes;
    }

    public String getCalorías_finales() {
        return Calorías_finales;
    }

    public void setCalorías_finales(String calorías_finales) {
        Calorías_finales = calorías_finales;
    }

    public String getCalorías_máximas() {
        return Calorías_máximas;
    }

    public void setCalorías_máximas(String calorías_máximas) {
        Calorías_máximas = calorías_máximas;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getNombre_usuario() {
        return Nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        Nombre_usuario = nombre_usuario;
    }

    public String getConten() {
        return conten;
    }

    public void setConten(String conten) {
        this.conten = conten;


    }


    @Override
    public String toString() {
        return "Historial{" +
                "Nombre_usuario='" + Nombre_usuario + '\'' +
                '}';
    }
}
