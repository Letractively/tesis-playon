/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.Tarifa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.service.ITarifaService;

/**
 * @author Pablo
 * 
 */
@ManagedBean(name = "tarifaMB")
@RequestScoped
public class TarifaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_TARIFAS = "tarifalist";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    List<Tarifa> tarifaList;

    private static List<Tarifa> tarifaPlayaList;

    private static List<Tarifa> promocionesPlayaList;

    private Integer id;

    private Float importe;

    private Boolean vigente;

    private Date fechaAlta;

    private Date fechaBaja;

    private Playa playa;

    private TipoEstadia tipoEstadia;

    private CategoriaVehiculo categoriaVehiculo;

    private static Tarifa tarifaSelected;

    private static Playa playaSelected;

    public String addTarifaAdmin() {
	try {
	    Tarifa tarifa = new Tarifa();
	    tarifa.setImporte(getImporte());
	    tarifa.setVigente(true);
	    tarifa.setPlaya(getPlaya());
	    tarifa.setTipoEstadia(getTipoEstadia());
	    tarifa.setCategoriaVehiculo(getCategoriaVehiculo());
	    getTarifaService().save(tarifa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó la tarifa correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_TARIFAS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar la tarifa"
		    + tarifaSelected.getPlaya().getNombreComercial(), "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public String deleteTarifaAdmin(Tarifa tarifa) {
	try {
	    getTarifaService().delete(tarifa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se borró la tarifa de la playa: "
		    + tarifaSelected.getPlaya().getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_TARIFAS;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(
		    FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo borrar la tarifa de la playa: " + tarifaSelected.getPlaya().getNombreComercial(),
		    "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public void deleteTarifa(Tarifa tarifa) {
	getTarifaService().delete(tarifa);
    }

    public String updateTarifaAdmin(Tarifa tarifa) {
	tarifaSelected = tarifa;
	return "tarifaeditadmin";
    }

    public String updateTarifaAdmin() {
	try {
	    getTarifaService().update(tarifaSelected);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La tarifa se modificó correctamente",
		    "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_TARIFAS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		    "Error,la tarifa no se pudo modificar", "Por favos, intentelo mas tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void updateTarifa(Tarifa tarifa) {
	getTarifaService().update(tarifa);
    }

    public String tomarSeleccionPlaya(Playa playa) {
	playaSelected = playa;
	this.playa = playa;
	if ("Secured".equals(estaLogueado())) {
	    return "tarifaplayalist";
	} else {
	    return "accessdenied?faces-redirect=true";    
	}
	
    }

    public String estaLogueado() {

	Authentication sc = SecurityContextHolder.getContext().getAuthentication();

	if (!"[ROLE_ADMIN]".equals(sc.getAuthorities().toString())
		&& !"[ROLE_PLAYA_GERENTE]".equals(sc.getAuthorities().toString())
		&& !"[ROLE_CLIENT]".equals(sc.getAuthorities().toString())) {
	    return "UnSecured";
	}
	return "Secured";
    }

    public void reset() {
	this.setFechaBaja(null);
	this.setImporte(0.f);
	this.setVigente(false);

    }

    public ITarifaService getTarifaService() {
	return tarifaService;
    }

    public void setTarifaService(ITarifaService tarifaService) {
	this.tarifaService = tarifaService;
    }

    public List<Tarifa> getTarifaList() {
	tarifaList = new ArrayList<Tarifa>();
	tarifaList.addAll(getTarifaService().findAll());
	return tarifaList;
    }

    public void setTarifaList(List<Tarifa> tarifaList) {
	this.tarifaList = tarifaList;
    }

    public List<Tarifa> getTarifaPlayaList() {
	tarifaPlayaList = new ArrayList<Tarifa>();
	tarifaPlayaList.addAll(getTarifaService().findByPlaya(playaSelected));
	return tarifaPlayaList;
    }

    public void setTarifaPlayaList(List<Tarifa> tarifaPlayaList) {
	TarifaManagedBean.tarifaPlayaList = tarifaPlayaList;
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

    public Integer getId() {
	return id;
    }

    public Tarifa getTarifaSelected() {
	return tarifaSelected;
    }

    public static Playa getPlayaSelected() {
	return playaSelected;
    }

    public List<Tarifa> getPromocionesPlayaList() {
	promocionesPlayaList = new ArrayList<Tarifa>();
	return promocionesPlayaList;
    }
}