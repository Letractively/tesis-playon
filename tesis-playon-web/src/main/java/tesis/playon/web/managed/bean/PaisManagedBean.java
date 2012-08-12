package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tesis.playon.web.model.Barrio;
import tesis.playon.web.model.Localidad;
import tesis.playon.web.model.Pais;
import tesis.playon.web.model.Provincia;
import tesis.playon.web.service.IPaisService;
import tesis.playon.web.service.IProvinciaService;

/**
 * 
 * @author gmorales
 * 
 */
@ManagedBean(name = "paisMB")
@ViewScoped
public class PaisManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    // private static final String LISTA_PAIS = "paislist";

    // private static final String ERROR = "error";

    @ManagedProperty(value = "#{PaisService}")
    IPaisService paisService;

    @ManagedProperty(value = "#{ProvinciaService}")
    IProvinciaService provinciaService;

    private Map<String, Pais> paises = new HashMap<String, Pais>();

    private Map<String, Provincia> provincias = new HashMap<String, Provincia>();

    private Map<String, Map<String, Provincia>> provinciasData = new HashMap<String, Map<String, Provincia>>();

    private String pais;

    private String provincia;

    private Localidad localidad;

    private Barrio barrio;

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

    public Map<String, Pais> getPaises() {
	List<Pais> paisList = getPaisService().findAll();
	for (Pais pais : paisList) {
	    System.out.println(pais.getNombre());
	    paises.put(pais.getNombre(), pais);

	    Map<String, Provincia> prov = new HashMap<String, Provincia>();
	    List<Provincia> provinciaList = getProvinciaService().findProvincias(pais);
	    for (Provincia provincia : provinciaList) {
		System.out.println(provincia.getNombre());
		prov.put(provincia.getNombre(), provincia);
	    }
	    provinciasData.put(pais.getNombre(), prov);
	}
	return paises;
    }

    public void setPaises(Map<String, Pais> paises) {
	this.paises = paises;
    }

    public Map<String, Provincia> getProvincias() {
	return provincias;
    }

    public Map<String, Map<String, Provincia>> getProvinciasData() {
	return provinciasData;
    }

    public void setProvinciasData(Map<String, Map<String, Provincia>> provinciasData) {
	this.provinciasData = provinciasData;
    }

    public void setProvincias(Map<String, Provincia> provincias) {
	this.provincias = provincias;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void handlePaisChange() {
	if (pais != null && !pais.equals("")) {
	    System.out.println("EL PAIS VALIA ALGOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
	    System.out.println("PAIS: " + pais);
	    provincias = provinciasData.get(pais);
	} else {
	    System.out.println("EL PAIS ERA NULOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
	    provincias = new HashMap<String, Provincia>();
	}
    }
}