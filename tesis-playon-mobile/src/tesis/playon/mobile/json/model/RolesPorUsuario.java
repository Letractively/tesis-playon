package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class RolesPorUsuario implements Serializable {

    private static final long serialVersionUID = 821855498219510902L;

    @SerializedName("id")
    private Integer id;

    @SerializedName("usuario")
    private String usuario;

    @SerializedName("rol")
    private String rol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}