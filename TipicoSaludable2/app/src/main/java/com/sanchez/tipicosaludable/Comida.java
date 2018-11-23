package com.sanchez.tipicosaludable;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by lenovo on 3/09/2018.
 */

public class Comida {
    private String Nombre, Carbohidratos, Proteinas, Receta,idDrawable, Calorias;

    public Comida() {
    }

    public Comida(String nombre, String carbohidratos, String proteinas, String receta, String idDrawable, String calorias) {
        Nombre = nombre;
        Carbohidratos = carbohidratos;
        Proteinas = proteinas;
        Receta = receta;
        this.idDrawable = idDrawable;
        Calorias = calorias;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCarbohidratos() {
        return Carbohidratos;
    }

    public void setCarbohidratos(String carbohidratos) {
        Carbohidratos = carbohidratos;
    }

    public String getProteinas() {
        return Proteinas;
    }

    public void setProteinas(String proteinas) {
        Proteinas = proteinas;
    }

    public String getReceta() {
        return Receta;
    }

    public void setReceta(String receta) {
        Receta = receta;
    }

    public String getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(String idDrawable) {
        this.idDrawable = idDrawable;
    }

    public String getCalorias() {
        return Calorias;
    }

    public void setCalorias(String calorias) {
        Calorias = calorias;
    }

    public int getId() {
        return Nombre.hashCode();
    }

    /*

    public static Comida[] ITEMS = {

       new Comida("Empanadas","las empanadas de pipián nos proporcionan 12% de proteínas.","las empanadas de pipián nos proporcionan un 40% De carbohidratos.",134
               , R.drawable.empanada),
       new Comida("Tamales de Pipian","Los tamales de pipián nos proporciona un 30% de carbohidratos ","los tamales de pipián nos proporcionan 17% de proteínas.",144, R.drawable.tamal),
       new Comida("Aplanchados","Los aplanchados nos proporcionan 47% de carbohidratos","los aplanchados nos proporcionan 26% de proteínas",133, R.drawable.aplanchados),
       new Comida("Carantanta","la carantanta nos  proporciona 40%  de carbohidratos","la carantanta nos proporciona un 6%  de proteínas.",530, R.drawable.carantanta),
       new Comida("Aborrajado","El aborrajado nos brinda 30% de carbohidratos.","un aborrajado nos proporciona 11% de proteínas", 167,R.drawable.aborrajados),
       new Comida("Ullucos","el ulluco nos brinda 57% de carbohidratos","los ullucos nos proporcionan 5% de proteínas",56,R.drawable.ullucos),
       new Comida("Cucas","Las cucas nos brindan un 67% de carbohidratos.","las cucas nos proporcionan un 5% de proteínas.",109,R.drawable.cucas),
       new Comida("Gelatina de Pata","la gelatina de pata nos brinda 43 g de carbohidratos.","la gelatina de pata nos proporciona 83 g de proteínas.",30,R.drawable.gelatinablanca),
       new Comida("Morcilla","La morcilla nos proporciona 8% carbohidratos.","la morcilla nos proporciona 11% de proteínas.",288,R.drawable.morcilla),
       new Comida("Chunchullo","El chunchullo nos brinda un 2% de carbohidrato.","el chunchullo nos proporciona un 7% de proteínas",40,R.drawable.chunchullo),
       new Comida("Papa Frita Criolla","la papa criolla nos brinda un 60% de carbohidratos.","la papa frita criolla nos brinda un 7% de proteínas",83,R.drawable.papafrita),
       new Comida("Arepa de Choclo","la arepa de choclo nos proporciona 64% de carbohidratos.","la arepa de choclo nos brinda 12% de proteínas",299,R.drawable.arepadechoclo),
       new Comida("Patacon","el pataco nos brinda un 31% de carbohidratos","el patacón nos brinda un 1% de proteínas",160,R.drawable.patacon),
       new Comida("Papa Rellena","la papa rellena nos brinda 40% de carbohidratos","la papa rellena nos brinda 15% de proteína.",262,R.drawable.papa_rellena),
       new Comida("Mote","el mote nos brinda 49% carbohidratos ","el mote nos proporciona un 6% en proteínas.",311,R.drawable.mote),
       new Comida("Manjar Blanco","el manjar banco nos brinda el 35% de carbohidratos. ","el manjar blanco nos proporciona un 4% de proteínas",292,R.drawable.manjarblanco),
       new Comida("Huevos Pericos","los huevos pericos nos brindan un 8% de carbohidratos.","los huevos nos brindan un 29% de proteínas",185,R.drawable.huevoperico),
       new Comida("Hojaldras","las hojaldras nos proporcionan un 64% de carbohidratos.","las hojaldras nos proporcionan un 12% de proteínas.",185,R.drawable.hojaldras),



    };*/

    public static Comida getItem(int id){
        for (Comida item : Fragment_galeria.listacomida){
            if (item.getId() == id){
                return item;
            }
        }
        return null;
    }


}
