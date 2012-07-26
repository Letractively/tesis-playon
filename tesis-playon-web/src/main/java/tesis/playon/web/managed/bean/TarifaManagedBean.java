/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

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
public class TarifaManagedBean implements Serializable{

    private static final long serialVersionUID = -1085389423375986168L;
    
    private static final String LISTA_TARIFAS = "tarifalist";

    private static final String ERROR = "error";
    
    @ManagedProperty(value = "#{TarifaService}")
    ITarifaService tarifaService;

    List<Tarifa> tarifaList;

    private Integer id;
    
    private Float importe;

    private Boolean vigente;

    private Date fechaAlta;

    private Date fechaBaja;

    private Playa playa;

    private TipoEstadia tipoEstadia;

    private CategoriaVehiculo categoriaVehiculo;

    public String addTarifa() {
	try {
	    Tarifa tarifa = new Tarifa();
	    tarifa.setFechaAlta(getFechaAlta());
	    tarifa.setFechaBaja(getFechaBaja());
	    tarifa.setImporte(getImporte());
	    tarifa.setVigente(getVigente());
	    tarifa.setPlaya(getPlaya());
	    tarifa.setTipoEstadia(getTipoEstadia());
	    tarifa.setCategoriaVehiculo(getCategoriaVehiculo());
	    getTarifaService().save(tarifa);
	    return LISTA_TARIFAS;
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
	return ERROR;
    }

    public void deleteTarifa(Tarifa tarifa) {
	getTarifaService().delete(tarifa);
    }

    public void updateTarifa(Tarifa tarifa) {
	getTarifaService().update(tarifa);
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
}