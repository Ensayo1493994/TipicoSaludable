package com.sanchez.tipicosaludable;

/**
 * Created by Sánchez on 28/09/2018.
 */

public class UltimoConsumo {
    public  String Nombre,idDrawable;


    public UltimoConsumo() {
    }

    public UltimoConsumo(String nombre, String idDrawable) {
        Nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(String idDrawable) {
        this.idDrawable = idDrawable;
    }

    public int getId() {
        return Nombre.hashCode();
    }
    /*
    public static UltimoConsumo[] ITEMS = {
            new UltimoConsumo("Empanadas",R.drawable.empanada),
            new UltimoConsumo("Tamales de Pipian", R.drawable.tamal),
            new UltimoConsumo("Aplanchados",R.drawable.aplanchados),
            new UltimoConsumo("Carantanta",R.drawable.carantanta),
            new UltimoConsumo("Aborrajado",R.drawable.aborrajados),
            new UltimoConsumo("Ullucos",R.drawable.ullucos),
            new UltimoConsumo("Cucas",R.drawable.cucas),
            new UltimoConsumo("Gelatina de Pata",R.drawable.gelatinablanca),
            new UltimoConsumo("Morcilla",R.drawable.morcilla),
            new UltimoConsumo("Chunchullo",R.drawable.chunchullo),
            new UltimoConsumo("Papa Frita Criolla",R.drawable.papafrita),
            new UltimoConsumo("Arepa de Choclo",R.drawable.arepadechoclo),
            new UltimoConsumo("Patacon",R.drawable.patacon),
            new UltimoConsumo("Papa Rellena",R.drawable.papa_rellena),
            new UltimoConsumo("Mote",R.drawable.mote),
            new UltimoConsumo("Manjar Blanco",R.drawable.manjarblanco),
            new UltimoConsumo("Huevos Pericos",R.drawable.huevoperico),
            new UltimoConsumo("Hojaldras",R.drawable.hojaldras),
    };*/

    public static UltimoConsumo getItem(int id){
        for (UltimoConsumo item : ScrollingDetalle.ultimoconsumo){
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }
}
