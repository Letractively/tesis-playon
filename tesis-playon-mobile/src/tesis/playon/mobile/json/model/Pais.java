package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Pais implements Serializable {

    private static final long serialVersionUID = 1156223909817372767L;

    @SerializedName("id")
    private Integer id;

    @SerializedName("nombre")
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}