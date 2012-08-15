/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;
import tesis.playon.web.service.IMarcaVehiculoService;
import tesis.playon.web.service.IModeloVehiculoService;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "modeloVehiculoMB")
@ViewScoped
public class ModeloVehiculoManagedBean implements Serializable {

    private static final long serialVersionUID = -1085389423375986168L;

    @ManagedProperty(value = "#{ModeloVehiculoService}")
    IModeloVehiculoService modeloVehiculoService;

    @ManagedProperty(value = "#{MarcaVehiculoService}")
    IMarcaVehiculoService marcaVehiculoService;

    private ModeloVehiculo modelo;

    private MarcaVehiculo marca;
    
    @SuppressWarnings("unused")
    private ModeloVehiculo modeloNulo;
    
    @SuppressWarnings("unused")
    private MarcaVehiculo marcaNula;
    
    private List<MarcaVehiculo> marcasList;
    
    private List<ModeloVehiculo> modelosList;

    public IModeloVehiculoService getModeloVehiculoService() {
	return modeloVehiculoService;
    }

    public void setModeloVehiculoService(IModeloVehiculoService modeloVehiculoService) {
	this.modeloVehiculoService = modeloVehiculoService;
    }

    public IMarcaVehiculoService getMarcaVehiculoService() {
	return marcaVehiculoService;
    }

    public void setMarcaVehiculoService(IMarcaVehiculoService marcaVehiculoService) {
	this.marcaVehiculoService = marcaVehiculoService;
    }

    public ModeloVehiculo getModelo() {
	return modelo;
    }

    public void setModelo(ModeloVehiculo modelo) {
	this.modelo = modelo;
    }

    public MarcaVehiculo getMarca() {
	return marca;
    }

    public void setMarca(MarcaVehiculo marca) {
	this.marca = marca;
    }

    public ModeloVehiculo getModeloNulo() {
        return null;
    }

    public void setModeloNulo(ModeloVehiculo modeloNulo) {
        this.modeloNulo = modeloNulo;
    }

    public MarcaVehiculo getMarcaNula() {
	modelosList = new ArrayList<ModeloVehiculo>();
        return null;
    }

    public void setMarcaNula(MarcaVehiculo marcaNula) {
        this.marcaNula = marcaNula;
    }

    public List<MarcaVehiculo> getMarcasList() {
	marcasList = getMarcaVehiculoService().findAll();
        return marcasList;
    }

    public void setMarcasList(List<MarcaVehiculo> marcasList) {
        this.marcasList = marcasList;
    }

    public List<ModeloVehiculo> getModelosList() {
        return modelosList;
    }

    public void setModelosList(List<ModeloVehiculo> modelosList) {
        this.modelosList = modelosList;
    }

    public void handleMarcaChange() {
	if(marca != null && !marca.equals(""))
	    modelosList = getModeloVehiculoService().findByMarca(marca);
	else
	    modelosList = new ArrayList<ModeloVehiculo>();
    }
}
