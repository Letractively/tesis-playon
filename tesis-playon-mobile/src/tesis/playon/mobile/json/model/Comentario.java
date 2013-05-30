package tesis.playon.mobile.json.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    @SerializedName("comentarioID")
    private Integer id;

    @SerializedName("comentario")
    private String comentario;

    @SerializedName("habilitado")
    private Boolean habilitado;

    private Playa playa;

    private Usuario usuario;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getComentario() {
	return comentario;
    }

    public void setComentario(String comentario) {
	this.comentario = comentario;
    }

    public Boolean getHabilitado() {
	return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
	this.habilitado = habilitado;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

}