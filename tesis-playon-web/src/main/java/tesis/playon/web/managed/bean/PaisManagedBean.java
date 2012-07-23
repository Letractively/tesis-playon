package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.TipoDoc;
import tesis.playon.web.service.ITipoDocService;

@ManagedBean(name = "paisMB")
@RequestScoped
public class PaisManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_PAIS = "paislist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{PaisService}")
    ITipoDocService tipoDocService;

    List<TipoDoc> tipoDocList;

    private String nombre;

    public String addTipoDoc() {
	try {
	    TipoDoc tipoDoc = new TipoDoc();
	    tipoDoc.setNombre(getNombre());
	    getTipoDocService().save(tipoDoc);
	    return LISTA_PAIS;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deleteTipoDoc(TipoDoc tipoDoc) {
	getTipoDocService().delete(tipoDoc);
    }

    public void updateTipoDoc(TipoDoc tipoDoc) {
	getTipoDocService().update(tipoDoc);
    }

    public void reset() {
	this.setNombre("");
    }

    public ITipoDocService getTipoDocService() {
	return tipoDocService;
    }

    public void setTipoDocService(ITipoDocService tipoDocService) {
	this.tipoDocService = tipoDocService;
    }

    public List<TipoDoc> getTipoDocList() {
	tipoDocList = new ArrayList<TipoDoc>();
	tipoDocList.addAll(getTipoDocService().findAll());
	return tipoDocList;
    }

    public void setUsuarioList(List<TipoDoc> tipoDocList) {
	this.tipoDocList = tipoDocList;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

}