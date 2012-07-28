package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IPaisService;

/**
 * 
 * @author gmorales
 *
 */
@ManagedBean(name = "paisMB")
@RequestScoped
public class PaisManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_PAIS = "paislist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{PaisService}")
    IPaisService paisService;

    List<Pais> paisList;

    private String nombre;
    
    private Set<Provincia> provincias;

    public String addPais() {
	try {
	    Pais pais = new Pais();
	    pais.setNombre(getNombre());
	    getPaisService().save(pais);
	    return LISTA_PAIS;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deletePais(Pais pais) {
	getPaisService().delete(pais);
    }

    public void updatePais(Pais pais) {
	getPaisService().update(pais);
    }

    public void reset() {
	this.setNombre("");
    }

    public List<Pais> getPaisList() {
	paisList = new ArrayList<Pais>();
	paisList.addAll(getPaisService().findAll());
	return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
	this.paisList = paisList;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }
    
    public Set<Provincia> getProvincias() {
	provincias = new HashSet<Provincia>(0);
	Pais pais = getPaisService().findByNombrPais(getNombre());
	provincias.addAll(getPaisService().findProvincia(pais));
        return provincias;
    }

    public void setProvincias(Set<Provincia> provincias) {
        this.provincias = provincias;
    }

    public IPaisService getPaisService() {
        return paisService;
    }

    public void setPaisService(IPaisService paisService) {
        this.paisService = paisService;
    }
    
}