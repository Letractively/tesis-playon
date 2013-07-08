package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class PerfilPlaya implements Serializable {

    private static final long serialVersionUID = 2382561437443895633L;

    @SerializedName("perfilPlayaID")
    private Integer id;

    @SerializedName("descripcion")
    private String descripcion;

    private Playa playa;

    @SerializedName("fotoPerfil")
    // private byte[] fotoPerfil;
    private String fotoPerfil;

    @SerializedName("nombreFoto")
    private String nombreFoto;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public String getFotoPerfil() {
	return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
	this.fotoPerfil = fotoPerfil;
    }

    public String getNombreFoto() {
	return nombreFoto;
    }

    public void setNombreFoto(String nombreFoto) {
	this.nombreFoto = nombreFoto;
    }

}