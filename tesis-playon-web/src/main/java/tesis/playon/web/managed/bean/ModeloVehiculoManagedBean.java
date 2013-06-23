/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.MarcaVehiculo;
import tesis.playon.web.model.ModeloVehiculo;
import tesis.playon.web.service.IMarcaVehiculoService;
import tesis.playon.web.service.IModeloVehiculoService;
import tesis.playon.web.service.IRolUsuarioService;

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

	private static final String LISTA_MARCA_VEHICULOS = "marcavehiculolist";

	private static final String ERROR = "error";

	private static List<MarcaVehiculo> marcaVehiculoList;

	private String nombreMarca;

	private static MarcaVehiculo marcaVehiculoSelected;

	private String descripcionMarca;

	@PostConstruct
	private void init() {
		marcaVehiculoList = getMarcaVehiculoService().findAll();
	}

	public String addMarcaVehiculo() {
		MarcaVehiculo marcaVehiculo = new MarcaVehiculo();
		try {

			marcaVehiculo.setNombre(getNombreMarca());
			marcaVehiculo.setDescripcion(getDescripcionMarca());
			getMarcaVehiculoService().save(marcaVehiculo);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Se agregó correctamente la marca: "
							+ marcaVehiculo.getNombre(), null);
			FacesContext.getCurrentInstance().addMessage(null, message);

			return LISTA_MARCA_VEHICULOS;
		} catch (DataAccessException e) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Error, no se pudo agregar el rol: "
							+ marcaVehiculo.getNombre(),
					"Por favor, inténtelo más tarde.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			e.printStackTrace();
		}
		return ERROR;
	}

	public IModeloVehiculoService getModeloVehiculoService() {
		return modeloVehiculoService;
	}

	public void setModeloVehiculoService(
			IModeloVehiculoService modeloVehiculoService) {
		this.modeloVehiculoService = modeloVehiculoService;
	}

	public IMarcaVehiculoService getMarcaVehiculoService() {
		return marcaVehiculoService;
	}

	public void setMarcaVehiculoService(
			IMarcaVehiculoService marcaVehiculoService) {
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
		if (marca != null && !marca.equals(""))
			modelosList = getModeloVehiculoService().findByMarca(marca);
		else
			modelosList = new ArrayList<ModeloVehiculo>();
	}

	public List<MarcaVehiculo> getMarcaVehiculoList() {
		return marcaVehiculoList;
	}

	public void setMarcaVehiculoList(List<MarcaVehiculo> marcaVehiculoList) {
		ModeloVehiculoManagedBean.marcaVehiculoList = marcaVehiculoList;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public static MarcaVehiculo getMarcaVehiculoSelected() {
		return marcaVehiculoSelected;
	}

	public static void setMarcaVehiculoSelected(
			MarcaVehiculo marcaVehiculoSelected) {
		ModeloVehiculoManagedBean.marcaVehiculoSelected = marcaVehiculoSelected;
	}

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/******************************** Metodos para marca de vehiculo ************************/

}
