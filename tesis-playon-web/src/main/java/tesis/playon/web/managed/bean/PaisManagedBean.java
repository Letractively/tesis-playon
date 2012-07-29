package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IBarrioService;
import tesis.playon.web.service.ILocalidadService;
import tesis.playon.web.service.IPaisService;
import tesis.playon.web.service.IProvinciaService;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "paisMB")
@RequestScoped
public class PaisManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    //private static final String LISTA_PAIS = "paislist";

    //private static final String ERROR = "error";

    @ManagedProperty(value = "#{PaisService}")
    IPaisService paisService;

    @ManagedProperty(value = "#{ProvinciaService}")
    IProvinciaService provinciaService;

    @ManagedProperty(value = "#{LocalidadService}")
    ILocalidadService localidadService;

    @ManagedProperty(value = "#{BarrioService}")
    IBarrioService barrioService;

    List<Pais> paisList;
    
    private Pais pais;
    
    private Provincia provincia;
    
    private Localidad localidad;
    
    private Barrio barrio;

    List<Provincia> provinciaList;

    List<Localidad> localidadList;

    List<Barrio> barrioList;

    public IPaisService getPaisService() {
        return paisService;
    }

    public void setPaisService(IPaisService paisService) {
        this.paisService = paisService;
    }

    public IProvinciaService getProvinciaService() {
        return provinciaService;
    }

    public void setProvinciaService(IProvinciaService provinciaService) {
        this.provinciaService = provinciaService;
    }

    public ILocalidadService getLocalidadService() {
        return localidadService;
    }

    public void setLocalidadService(ILocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    public IBarrioService getBarrioService() {
        return barrioService;
    }

    public void setBarrioService(IBarrioService barrioService) {
        this.barrioService = barrioService;
    }

    public List<Pais> getPaisList() {
	paisList = new ArrayList<Pais>();
	paisList.addAll(getPaisService().findAll());
	return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public List<Provincia> getProvinciaList() {
	if(pais != null){
	    provinciaList = getProvinciaService().findProvincias(pais);
	}
	else
	    provinciaList = new ArrayList<Provincia>();
	return provinciaList;
    }

    public void setProvinciaList(List<Provincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public List<Localidad> getLocalidadList() {
        return localidadList;
    }

    public void setLocalidadList(List<Localidad> localidadList) {
        this.localidadList = localidadList;
    }

    public List<Barrio> getBarrioList() {
        return barrioList;
    }

    public void setBarrioList(List<Barrio> barrioList) {
        this.barrioList = barrioList;
    }
    
    public void handlePaisChange() {
	getLocalidadList();
    }
    
//    public void handleProvinciaChange() {
//	if(provinciaId != 0){
//	    Provincia provincia = getProvinciaService().findByProvinciaId(provinciaId);
//	    localidadList = getLocalidadService().find
//	}
//	else
//	    provinciaList = new ArrayList<Provincia>();
//    }
}