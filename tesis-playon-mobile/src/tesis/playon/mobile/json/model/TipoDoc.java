package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class TipoDoc implements Serializable {

    private static final long serialVersionUID = 7917020389881257152L;

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