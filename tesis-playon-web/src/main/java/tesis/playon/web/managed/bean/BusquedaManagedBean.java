package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Estadia;
import tesis.playon.web.model.EstadoPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.service.IEstadoPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.util.LatitudlongitudUtil;

@ManagedBean(name = "busquedaMB")
@RequestScoped
public class BusquedaManagedBean implements Serializable{
    
    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_PLAYAS = "mapalist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{PlayaService}")
    IPlayaService playaService;

    List<Playa> playaList;
    
    List<Playa> playasCercanasList;

    private String cuit;

    private Integer disponibilidad;

    private String direccionBusqueda;

    private String domicilio;

    private String nombreComercial;

    private String razonSocial;

    private Barrio barrio;

    private EstadoPlaya estado;

    private Estadia estadia;
    
    private Double longitud;
    
    private Double latitud;
    
    private int distancia;

    public String searchPlaya() {
	try {
	    playasCercanasList = new ArrayList<Playa>();
	    playasCercanasList.addAll(getPlayaService().findPlayasCercanas(longitud, latitud, distancia));
	    setPlayaList(playasCercanasList);
	    return LISTA_PLAYAS;
	} catch (DataAccessException e) {
	    System.out.println("NO SE CONECTAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deletePlaya(Playa playa) {
	getPlayaService().delete(playa);
    }

    public void updatePlaya(Playa playa) {
	getPlayaService().update(playa);
    }

    

    public IPlayaService getPlayaService() {
        return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
        this.playaService = playaService;
    }

    public List<Playa> getPlayaList() {
	playaList = new ArrayList<Playa>();
	playaList.addAll(getPlayaService().findAll());
	return playaList;
    }

    public void setPlayaList(List<Playa> playaList) {
	this.playaList = playaList;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getCuit() {
	return cuit;
    }

    public void setCuit(String cuit) {
	this.cuit = cuit;
    }

    public Integer getDisponibilidad() {
	return disponibilidad;
    }

    public void setDisponibilidad(Integer disponibilidad) {
	this.disponibilidad = disponibilidad;
    }

    public String getDomicilio() {
	return domicilio;
    }

    public void setDomicilio(String domicilio) {
	this.domicilio = domicilio;
    }

    public String getNombreComercial() {
	return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
	this.nombreComercial = nombreComercial;
    }

    public Barrio getBarrio() {
	return barrio;
    }

    public void setBarrio(Barrio barrio) {
	this.barrio = barrio;
    }

    public EstadoPlaya getEstado() {
	return estado;
    }

    public void setEstado(EstadoPlaya estado) {
	this.estado = estado;
    }

    public Estadia getEstadia() {
	return estadia;
    }

    public void setEstadia(Estadia estadia) {
	this.estadia = estadia;
    }

    public String getRazonSocial() {
	return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
    }

    public String getDireccionBusqueda() {
	return direccionBusqueda;
    }

    public void setDireccionBusqueda(String direccionBusqueda) {
	this.direccionBusqueda = direccionBusqueda;
    }


}
