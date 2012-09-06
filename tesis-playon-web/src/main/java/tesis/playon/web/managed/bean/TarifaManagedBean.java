/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ICategoriaVehiculoService;
import tesis.playon.web.service.ITarifaService;
import tesis.playon.web.service.ITipoEstadiaService;
import tesis.playon.web.service.IUsuarioService;

/**
 * @author Pablo
 * 
 */
@ManagedBean(name = "tarifaMB")
@RequestScoped
public class TarifaManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    private static final String LISTA_TARIFAS = "tarifalist";
    
    private static final String ADD_TARIFAS_SUCCESS = "tarifaend";

    private static final String ERROR = "error";

    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{TipoEstadiaService}")
    ITipoEstadiaService tipoEstadiaService;

    @ManagedProperty(value = "#{CategoriaVehiculoService}")
    ICategoriaVehiculoService categoriaVehiculoService;

    List<Tarifa> tarifaList;

    private List<Tarifa> tarifaPlayaList;

    private List<Tarifa> promocionesPlayaList;

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

    private String autoPorHora;
    private String utilitarioPorHora;
    private String pickupPorHora;
    private String motoPorHora;

    private String autoPorDia;
    private String utilitarioPorDia;
    private String pickupPorDia;
    private String motoPorDia;

    private String autoPorSemana;
    private String utilitarioPorSemana;
    private String pickupPorSemana;
    private String motoPorSemana;

    private String autoPorMes;
    private String utilitarioPorMes;
    private String pickupPorMes;
    private String motoPorMes;
    
    //ATRIBUTO PARA EL FRONTEND
    
    private List<Tarifa> tarifaListSelected;

    public String addTarifaAdmin() {
	try {
	    Tarifa tarifa = new Tarifa();

	    tarifa.setImporte(getImporte());
	    tarifa.setVigente(getVigente());

	    tarifa.setPlaya(getPlaya());
	    tarifa.setTipoEstadia(getTipoEstadia());
	    tarifa.setCategoriaVehiculo(getCategoriaVehiculo());
	    getTarifaService().save(tarifa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agregó la tarifa correctamente", "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_TARIFAS;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar la tarifa"
		    + tarifaSelected.getPlaya().getNombreComercial(), "Por favor, intételo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	}
	return ERROR;
    }

    public String deleteTarifaAdmin(Tarifa tarifa) {
	try {
	    tarifa.setFechaBaja(new Date());
	    getTarifaService().update(tarifa);
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se borró la tarifa de la playa: "
		    + tarifaSelected.getPlaya().getNombreComercial(), "");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    return LISTA_TARIFAS;
	} catch (Exception e) {
	    FacesMessage message = new FacesMessage(
		    FacesMessage.SEVERITY_ERROR,
		    "Error, no se pudo borrar la tarifa de la playa: " + tarifaSelected.getPlaya().getNombreComercial(),
		    "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return ERROR;
    }

    public String addTarifas(){
	this.setPlayaByLoggedUser();
	this.setVigente(new Boolean(true));
	this.setFechaAlta(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	
	if (autoPorHora != null && !autoPorHora.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Hora"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Auto"));
	    this.setImporte(Float.valueOf(autoPorHora));
	    this.addTarifa();
	}
	if (utilitarioPorHora != null && !utilitarioPorHora.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Hora"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Utilitario"));
	    this.setImporte(Float.valueOf(utilitarioPorHora));
	    this.addTarifa();
	}
	if (pickupPorHora != null && !pickupPorHora.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Hora"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("PickUp / 4X4"));
	    this.setImporte(Float.valueOf(pickupPorHora));
	    this.addTarifa();
	}
	if (motoPorHora != null && !motoPorHora.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Hora"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Moto"));
	    this.setImporte(Float.valueOf(motoPorHora));
	    this.addTarifa();
	}
	
	if (autoPorDia != null && !autoPorDia.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Dí­a"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Auto"));
	    this.setImporte(Float.valueOf(autoPorDia));
	    this.addTarifa();
	}
	if (utilitarioPorDia != null && !utilitarioPorDia.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Dí­a"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Utilitario"));
	    this.setImporte(Float.valueOf(utilitarioPorDia));
	    this.addTarifa();
	}
	if (pickupPorDia != null && !pickupPorDia.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Dí­a"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("PickUp / 4X4"));
	    this.setImporte(Float.valueOf(pickupPorDia));
	    this.addTarifa();
	}
	if (motoPorDia != null && !motoPorDia.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Dí­a"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Moto"));
	    this.setImporte(Float.valueOf(motoPorDia));
	    this.addTarifa();
	}

	if (autoPorSemana != null && !autoPorSemana.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Semana"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Auto"));
	    this.setImporte(Float.valueOf(autoPorSemana));
	    this.addTarifa();
	}
	if (utilitarioPorSemana != null && !utilitarioPorSemana.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Semana"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Utilitario"));
	    this.setImporte(Float.valueOf(utilitarioPorSemana));
	    this.addTarifa();
	}
	if (pickupPorSemana != null && !pickupPorSemana.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Semana"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("PickUp / 4X4"));
	    this.setImporte(Float.valueOf(pickupPorSemana));
	    this.addTarifa();
	}
	if (motoPorSemana != null && !motoPorSemana.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Semana"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Moto"));
	    this.setImporte(Float.valueOf(motoPorSemana));
	    this.addTarifa();
	}

	if (autoPorMes != null && !autoPorMes.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Mes"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Auto"));
	    this.setImporte(Float.valueOf(autoPorMes));
	    this.addTarifa();
	}
	if (utilitarioPorMes != null && !utilitarioPorMes.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Mes"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("Utilitario"));
	    this.setImporte(Float.valueOf(utilitarioPorMes));
	    this.addTarifa();
	}
	if (pickupPorMes != null && !pickupPorMes.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService().
		    findByNombreTipoEstadia("Por Mes"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService().
		    findByNombreCategoriaVehiculo("PickUp / 4X4"));
	    this.setImporte(Float.valueOf(pickupPorMes));
	    this.addTarifa();
	}
	if (motoPorMes != null && !motoPorMes.toString().trim().isEmpty()){
	    this.setTipoEstadia(getTipoEstadiaService()
		    .findByNombreTipoEstadia("Por Mes"));
	    this.setCategoriaVehiculo(getCategoriaVehiculoService()
		    .findByNombreCategoriaVehiculo("Moto"));
	    this.setImporte(Float.valueOf(motoPorMes));
	    this.addTarifa();
	}
	return ADD_TARIFAS_SUCCESS;
    }

    private boolean addTarifa() {
	try {
	    Tarifa tarifa = new Tarifa();

	    tarifa.setImporte(getImporte());
	    tarifa.setVigente(getVigente());

	    tarifa.setPlaya(getPlaya());
	    tarifa.setTipoEstadia(getTipoEstadia());
	    tarifa.setCategoriaVehiculo(getCategoriaVehiculo());
	    getTarifaService().save(tarifa);
	    return true;
	} catch (DataAccessException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, no se pudo agregar la tarifa "
	    /*
	     * + "con categoría: " + tarifa.getCategoriaVehiculo().getNombre() + " y del tipo: " +
	     * tarifa.getTipoEstadia().getNombre()
	     */, "Por favor, inténtelo más tarde.");
	    FacesContext.getCurrentInstance().addMessage(null, message);
	    e.printStackTrace();
	    return false;
	}
    }

    public void setPlayaByLoggedUser() {
	String nombreUsuario;
	Usuario usuario;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	nombreUsuario = facesContext.getExternalContext().getRemoteUser();
	usuario = getUsuarioService().findByNombreUsuario(nombreUsuario);
	setPlaya(usuario.getPlaya());
    }

    public void deleteTarifa(Tarifa tarifa) {
	// getTarifaService().delete(tarifa);
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
		    "Error, la tarifa no se pudo modificar", "Por favor, intentelo mas tarde.");
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

    public ITipoEstadiaService getTipoEstadiaService() {
	return tipoEstadiaService;
    }

    public void setTipoEstadiaService(ITipoEstadiaService tipoEstadiaService) {
	this.tipoEstadiaService = tipoEstadiaService;
    }

    public ICategoriaVehiculoService getCategoriaVehiculoService() {
	return categoriaVehiculoService;
    }

    public void setCategoriaVehiculoService(ICategoriaVehiculoService categoriaVehiculoService) {
	this.categoriaVehiculoService = categoriaVehiculoService;
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
	this.tarifaPlayaList = tarifaPlayaList;
    }

    public Float getImporte() {
	return importe;
    }

    public void setImporte(Float importe) {
	this.importe = importe;
    }

    public Float getImporteAutoPorHora() {
	return importe;
    }

    public void setImporteAutoPorHora(Float importe) {
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

    public Playa getPlayaSelected() {
	return playaSelected;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public List<Tarifa> getPromocionesPlayaList() {
	promocionesPlayaList = new ArrayList<Tarifa>();
	return promocionesPlayaList;
    }

    public String getAutoPorHora() {
	return autoPorHora;
    }

    public void setAutoPorHora(String autoPorHora) {
	this.autoPorHora = autoPorHora;
    }

    public String getUtilitarioPorHora() {
	return utilitarioPorHora;
    }

    public void setUtilitarioPorHora(String utilitarioPorHora) {
	this.utilitarioPorHora = utilitarioPorHora;
    }

    public String getPickupPorHora() {
	return pickupPorHora;
    }

    public void setPickupPorHora(String pickupPorHora) {
	this.pickupPorHora = pickupPorHora;
    }

    public String getMotoPorHora() {
	return motoPorHora;
    }

    public void setMotoPorHora(String motoPorHora) {
	this.motoPorHora = motoPorHora;
    }

    public String getAutoPorDia() {
	return autoPorDia;
    }

    public void setAutoPorDia(String autoPorDia) {
	this.autoPorDia = autoPorDia;
    }

    public String getUtilitarioPorDia() {
	return utilitarioPorDia;
    }

    public void setUtilitarioPorDia(String utilitarioPorDia) {
	this.utilitarioPorDia = utilitarioPorDia;
    }

    public String getPickupPorDia() {
	return pickupPorDia;
    }

    public void setPickupPorDia(String pickupPorDia) {
	this.pickupPorDia = pickupPorDia;
    }

    public String getMotoPorDia() {
	return motoPorDia;
    }

    public void setMotoPorDia(String motoPorDia) {
	this.motoPorDia = motoPorDia;
    }

    public String getAutoPorSemana() {
	return autoPorSemana;
    }

    public void setAutoPorSemana(String autoPorSemana) {
	this.autoPorSemana = autoPorSemana;
    }

    public String getUtilitarioPorSemana() {
	return utilitarioPorSemana;
    }

    public void setUtilitarioPorSemana(String utilitarioPorSemana) {
	this.utilitarioPorSemana = utilitarioPorSemana;
    }

    public String getPickupPorSemana() {
	return pickupPorSemana;
    }

    public void setPickupPorSemana(String pickupPorSemana) {
	this.pickupPorSemana = pickupPorSemana;
    }

    public String getMotoPorSemana() {
	return motoPorSemana;
    }

    public void setMotoPorSemana(String motoPorSemana) {
	this.motoPorSemana = motoPorSemana;
    }

    public String getAutoPorMes() {
	return autoPorMes;
    }

    public void setAutoPorMes(String autoPorMes) {
	this.autoPorMes = autoPorMes;
    }

    public String getUtilitarioPorMes() {
	return utilitarioPorMes;
    }

    public void setUtilitarioPorMes(String utilitarioPorMes) {
	this.utilitarioPorMes = utilitarioPorMes;
    }

    public String getPickupPorMes() {
	return pickupPorMes;
    }

    public void setPickupPorMes(String pickupPorMes) {
	this.pickupPorMes = pickupPorMes;
    }

    public String getMotoPorMes() {
	return motoPorMes;
    }

    public void setMotoPorMes(String motoPorMes) {
	this.motoPorMes = motoPorMes;
    }

    public List<Tarifa> getTarifaListSelected() {
	if(playaSelected != null){
	    tarifaListSelected = getTarifaService().findTarifaVigenteByPlaya(playaSelected);
	}
	return tarifaListSelected;
    }

    public void setTarifaListSelected(List<Tarifa> tarifaListSelected) {
        this.tarifaListSelected = tarifaListSelected;
    }

    public static void setTarifaSelected(Tarifa tarifaSelected) {
        TarifaManagedBean.tarifaSelected = tarifaSelected;
    }

    public void setPlayaSelected(Playa playaSelected) {
        TarifaManagedBean.playaSelected = playaSelected;
    }
    
}