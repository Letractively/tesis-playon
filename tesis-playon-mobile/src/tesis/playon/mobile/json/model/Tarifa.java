package tesis.playon.mobile.json.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Tarifa implements Serializable {

    private static final long serialVersionUID = -897727738173522616L;

    @SerializedName("tarifaID")
    private Integer id;

    @SerializedName("importe")
    private Float importe;

    @SerializedName("vigente")
    private Boolean vigente;

    @SerializedName("fechaAlta")
    private Date fechaAlta;

    @SerializedName("fechaBaja")
    private Date fechaBaja;

    private Playa playa;

    private TipoEstadia tipoEstadia;

    private CategoriaVehiculo categoriaVehiculo;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Float getImporte() {
	return importe;
    }

    public void setImporte(Float importe) {
	this.importe = importe;
    }

    public Boolean getVigente() {
	return vigente;
    }

    public void setVigente(Boolean vigente) {
	this.vigente = vigente;
    }

    public Date getFechaAlta() {
	return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
	this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
	return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
	this.fechaBaja = fechaBaja;
    }

    public Playa getPlaya() {
	return playa;
    }

    public void setPlaya(Playa playa) {
	this.playa = playa;
    }

    public TipoEstadia getTipoEstadia() {
	return tipoEstadia;
    }

    public void setTipoEstadia(TipoEstadia tipoEstadia) {
	this.tipoEstadia = tipoEstadia;
    }

    public CategoriaVehiculo getCategoriaVehiculo() {
	return categoriaVehiculo;
    }

    public void setCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
	this.categoriaVehiculo = categoriaVehiculo;
    }

}