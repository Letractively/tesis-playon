package tesis.playon.mobile.json.model;

import com.google.gson.annotations.SerializedName;

public class Provincia {

    @SerializedName("id")
    private Integer id;

    @SerializedName("nombre")
    private String nombre;

    @SuppressWarnings("unused")
    private Pais pais;

}