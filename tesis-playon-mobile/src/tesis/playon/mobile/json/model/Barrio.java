package tesis.playon.mobile.json.model;

import com.google.gson.annotations.SerializedName;

public class Barrio {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nombre")
    private String nombre;

    @SuppressWarnings("unused")
    private Localidad localidad;

}
