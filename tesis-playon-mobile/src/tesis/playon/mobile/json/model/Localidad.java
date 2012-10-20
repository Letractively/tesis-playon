package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Localidad implements Serializable {

    private static final long serialVersionUID = -6575958812728027346L;

    @SerializedName("id")
    private Integer id;

    @SerializedName("nombre")
    private String nombre;

    private Provincia provincia;

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

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

}