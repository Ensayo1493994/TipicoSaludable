package com.sanchez.tipicosaludable.model;

/**
 * Created by ADMIN on 26/10/2018.
 */

public class Historial {
    private String uid;
    private Double Calorías_consumidas;
    private Double Calorías_excedentes;
    private int Calorías_finales;
    private Double Calorías_máximas;
    private String Fecha;
    private String usuario;
    private int calorias_acumuladas;
    public Historial() {

    }

    public Historial(String uid, Double calorías_consumidas, Double calorías_excedentes, int calorías_finales, Double calorías_máximas, String fecha, String usuario, int calorias_acumuladas) {
        this.uid = uid;
        Calorías_consumidas = calorías_consumidas;
        Calorías_excedentes = calorías_excedentes;
        Calorías_finales = calorías_finales;
        Calorías_máximas = calorías_máximas;
        Fecha = fecha;
        this.usuario = usuario;
        this.calorias_acumuladas = calorias_acumuladas;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getCalorías_consumidas() {
        return Calorías_consumidas;
    }

    public void setCalorías_consumidas(Double calorías_consumidas) {
        Calorías_consumidas = calorías_consumidas;
    }

    public Double getCalorías_excedentes() {
        return Calorías_excedentes;
    }

    public void setCalorías_excedentes(Double calorías_excedentes) {
        Calorías_excedentes = calorías_excedentes;
    }

    public int getCalorías_finales() {
        return Calorías_finales;
    }

    public void setCalorías_finales(int calorías_finales) {
        Calorías_finales = calorías_finales;
    }

    public Double getCalorías_máximas() {
        return Calorías_máximas;
    }

    public void setCalorías_máximas(Double calorías_máximas) {
        Calorías_máximas = calorías_máximas;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCalorias_acumuladas() {
        return calorias_acumuladas;
    }

    public void setCalorias_acumuladas(int calorias_acumuladas) {
        this.calorias_acumuladas = calorias_acumuladas;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "usuario='" + usuario + '\'' +
                '}'+ "calmaximas"+ Calorías_máximas;
    }
}
