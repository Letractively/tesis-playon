package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import tesis.playon.web.model.CategoriaVehiculo;
import tesis.playon.web.model.PerfilPlaya;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TipoEstadia;
import tesis.playon.web.service.IPerfilPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.util.LatitudlongitudUtil;
import tesis.playon.web.util.LatitudlongitudUtil.GeoposicionDePlaya;
import tesis.playon.web.util.WriteImage;

@ManagedBean(name = "busquedaplayaMB")
@ViewScoped
public class BusquedaPlayasManagedBean implements Serializable {

	private static final long serialVersionUID = -1085389423375986168L;

	private MapModel simpleModel;
	private MapModel advancedModel;
	private Marker marker;

	@ManagedProperty(value = "#{PlayaService}")
	IPlayaService playaService;

	@ManagedProperty(value = "#{PerfilPlayaService}")
	IPerfilPlayaService perfilPlayaService;

	List<Playa> playaList;

	private static List<Playa> playaResultadoBusqueda;

	LatitudlongitudUtil latLonUtil;

	private String direccionBusqueda;

	private Integer distancia;

	private GeoposicionDePlaya respuesta;

	private String coordenadas;
	
	private String direccionDesde;
	private String direccionHasta;
	
	public static Playa playaselected;

	// Atributos para los filtros.
	private boolean opcionesAvanzadas;
	private CategoriaVehiculo categoriaParameter;
	private TipoEstadia tipoEstadiaParameter;
	private int checkPromociones;
	private String nombrePlayaParameter;

	private Double latitudCentro;
	private Double longitudCentro;

	@PostConstruct
	private void init() {
		playaResultadoBusqueda = new ArrayList<Playa>();
		distancia = 25;
		opcionesAvanzadas = false;
		this.direccionDesde = " ";
	}

	public void preRenderView() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			// if (getDireccionBusqueda() == null) {
			// if (getDireccionBusqueda().trim().isEmpty()) {
			if (getPlayaResultadoBusqueda() != null) {
				playaResultadoBusqueda.clear();
				playaResultadoBusqueda = null;
			}
			// }
			// }
		}
	}

	public void buscarPlaya() {

		int idCategoria = 0;
		int idTipoEstadia = 0;
		String nombrePlaya = null;

		if (null != getDireccionBusqueda()
				&& !getDireccionBusqueda().trim().isEmpty()) {
			try {

				latLonUtil = new LatitudlongitudUtil();
				// GeoposicionDePlaya
				respuesta = latLonUtil
						.getLocationFromAddress(getDireccionBusqueda().trim()
								+ ", Cordoba, Argentina");
				coordenadas = respuesta.toString();

				playaResultadoBusqueda = new ArrayList<Playa>();
				advancedModel = new DefaultMapModel();

				List<Playa> playasCercanas = new ArrayList<Playa>();
				playasCercanas = getPlayaService().findByPlayasCercanas(
						respuesta.getLatitud(), respuesta.getLongitud(),
						idCategoria, idTipoEstadia, nombrePlaya,
						checkPromociones);

				if (playasCercanas != null) {
					for (Playa playaAux : playasCercanas) {
						Double comparacion = playaAux
								.getDistanceFrom(respuesta.getLatitud(),
										respuesta.getLongitud());
						if (comparacion < getDistancia()) {
							playaResultadoBusqueda.add(playaAux);
							LatLng coord1 = new LatLng(playaAux.getLatitud(),
									playaAux.getLongitud());
							PerfilPlaya perfil = new PerfilPlaya();
							perfil = getPerfilPlayaService().findByPlaya(
									playaAux);
							WriteImage.getFotoPerfilPlaya(perfil);
							advancedModel
									.addOverlay(new Marker(coord1, playaAux
											.getNombreComercial(), perfil,
											"http://s2.subirimagenes.com/imagen/previo/thump_7891124iconoe.png"));
						}
						LatLng coordenada = new LatLng(respuesta.getLatitud(),
								respuesta.getLongitud());

						advancedModel
								.addOverlay(new Marker(coordenada,
										"¡Usted está aquí!", null,
										"http://s3.subirimagenes.com:81/otros/previo/thump_7896462autoicono.jpg"));

					}
				} else {

					LatLng coordenada = new LatLng(respuesta.getLatitud(),
							respuesta.getLongitud());

					advancedModel
							.addOverlay(new Marker(coordenada,
									"¡Usted está aquí!", null,
									"http://s3.subirimagenes.com:81/otros/previo/thump_7896462autoicono.jpg"));
				}
				ordenar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void busquedaAvanzada() {
		int idCategoria = 0;
		int idTipoEstadia = 0;
		String nombrePlaya = null;

		if (categoriaParameter != null)
			idCategoria = categoriaParameter.getId();
		if (tipoEstadiaParameter != null)
			idTipoEstadia = tipoEstadiaParameter.getId();
		if (!nombrePlayaParameter.isEmpty())
			nombrePlaya = nombrePlayaParameter;

		if (null != getDireccionBusqueda()
				&& !getDireccionBusqueda().trim().isEmpty()) {
			try {

				latLonUtil = new LatitudlongitudUtil();
				// GeoposicionDePlaya
				respuesta = latLonUtil
						.getLocationFromAddress(getDireccionBusqueda().trim()
								+ ", Cordoba, Argentina");
				coordenadas = respuesta.toString();

				playaResultadoBusqueda = new ArrayList<Playa>();
				advancedModel = new DefaultMapModel();

				List<Playa> playasCercanas = new ArrayList<Playa>();
				playasCercanas = getPlayaService().findByPlayasCercanas(
						respuesta.getLatitud(), respuesta.getLongitud(),
						idCategoria, idTipoEstadia, nombrePlaya,
						checkPromociones);

				if (playasCercanas != null) {
					for (Playa playaAux : playasCercanas) {
						Double comparacion = playaAux
								.getDistanceFrom(respuesta.getLatitud(),
										respuesta.getLongitud());
						if (comparacion < getDistancia()) {
							playaResultadoBusqueda.add(playaAux);
							LatLng coord1 = new LatLng(playaAux.getLatitud(),
									playaAux.getLongitud());
							PerfilPlaya perfil = new PerfilPlaya();
							perfil = getPerfilPlayaService().findByPlaya(
									playaAux);
							WriteImage.getFotoPerfilPlaya(perfil);
							advancedModel
									.addOverlay(new Marker(coord1, playaAux
											.getNombreComercial(), perfil,
											"http://s2.subirimagenes.com/imagen/previo/thump_7891124iconoe.png"));
						}
						LatLng coordenada = new LatLng(respuesta.getLatitud(),
								respuesta.getLongitud());

						advancedModel
								.addOverlay(new Marker(coordenada,
										"¡Usted está aquí!", null,
										"http://s3.subirimagenes.com:81/otros/previo/thump_7896462autoicono.jpg"));

					}
				} else {

					LatLng coordenada = new LatLng(respuesta.getLatitud(),
							respuesta.getLongitud());

					advancedModel
							.addOverlay(new Marker(coordenada,
									"¡Usted está aquí!", null,
									"http://s3.subirimagenes.com:81/otros/previo/thump_7896462autoicono.jpg"));
				}
				ordenar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	class Comparar implements Comparator<Playa> {
		public int compare(Playa p1, Playa p2) {
			Double comparacion1 = p1.getDistanceFrom(respuesta.getLatitud(),
					respuesta.getLongitud());
			Double comparacion2 = p2.getDistanceFrom(respuesta.getLatitud(),
					respuesta.getLongitud());

			return comparacion1.compareTo(comparacion2);
		}

	}

	public void ordenar() {
		List<Playa> playas = playaResultadoBusqueda;
		Collections.sort(playas, new Comparar());

	}

	public IPerfilPlayaService getPerfilPlayaService() {
		return perfilPlayaService;
	}

	public void setPerfilPlayaService(IPerfilPlayaService perfilPlayaService) {
		this.perfilPlayaService = perfilPlayaService;
	}

	public LatitudlongitudUtil getLatLonUtil() {
		return latLonUtil;
	}

	public void setLatLonUtil(LatitudlongitudUtil latLonUtil) {
		this.latLonUtil = latLonUtil;
	}

	public GeoposicionDePlaya getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(GeoposicionDePlaya respuesta) {
		this.respuesta = respuesta;
	}

	public String getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public String getDireccionBusqueda() {
		return direccionBusqueda;
	}

	public void setDireccionBusqueda(String direccionBusqueda) {
		this.direccionBusqueda = direccionBusqueda;
	}

	public List<Playa> getPlayaResultadoBusqueda() {
		return playaResultadoBusqueda;
	}

	public void setPlayaResultadoBusqueda(List<Playa> playaResultadoBusqueda) {
		BusquedaPlayasManagedBean.playaResultadoBusqueda = playaResultadoBusqueda;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public List<Playa> getPlayaList() {
		playaList = new ArrayList<Playa>();
		playaList.addAll(getPlayaService().findAll());
		return playaList;
	}

	public void setPlayaList(List<Playa> playaList) {
		this.playaList = playaList;
	}

	public IPlayaService getPlayaService() {
		return playaService;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public void setPlayaService(IPlayaService playaService) {
		this.playaService = playaService;
	}

	public MapModel getAdvancedModel() {
		return advancedModel;
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		marker = (Marker) event.getOverlay();
	}

	public Marker getMarker() {
		return marker;
	}

	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	// ==================================GETTER & SETTER DE LOS FILTROS
	// ===============================//
	public boolean isOpcionesAvanzadas() {
		return opcionesAvanzadas;
	}

	public void setOpcionesAvanzadas(boolean opcionesAvanzadas) {
		this.opcionesAvanzadas = opcionesAvanzadas;
	}

	public void settearOpcionesAvanzadas() {
		this.opcionesAvanzadas = true;
	}

	public CategoriaVehiculo getCategoriaParameter() {
		return categoriaParameter;
	}

	public void setCategoriaParameter(CategoriaVehiculo categoriaParameter) {
		this.categoriaParameter = categoriaParameter;
	}

	public TipoEstadia getTipoEstadiaParameter() {
		return tipoEstadiaParameter;
	}

	public void setTipoEstadiaParameter(TipoEstadia tipoEstadiaParameter) {
		this.tipoEstadiaParameter = tipoEstadiaParameter;
	}

	public Double getLatitudCentro() {
		if (getDireccionBusqueda() != null) {
			LatitudlongitudUtil latLon = new LatitudlongitudUtil();
			GeoposicionDePlaya latitud = null;
			try {
				latitud = latLon.getLocationFromAddress(getDireccionBusqueda()
						.trim() + ", Cordoba, Argentina");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double lat;
			lat = latitud.getLatitud();
			latitudCentro = lat;
			return latitudCentro;
		}

		else
			return (double) 0;
	}

	public void setLatitudCentro(Double latitudCentro) {
		this.latitudCentro = latitudCentro;
	}

	public Double getLongitudCentro() {
		if (getDireccionBusqueda() != null) {
			LatitudlongitudUtil latLon = new LatitudlongitudUtil();
			GeoposicionDePlaya latitud = null;
			try {
				latitud = latLon.getLocationFromAddress(getDireccionBusqueda()
						.trim() + ", Cordoba, Argentina");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double longi;
			longi = latitud.getLongitud();
			longitudCentro = longi;
			return longitudCentro;
		} else
			return (double) 0;
	}

	public void setLongitudCentro(Double longitudCentro) {
		this.longitudCentro = longitudCentro;
	}

	public int getCheckPromociones() {
		return checkPromociones;
	}

	public void setCheckPromociones(int checkPromociones) {
		this.checkPromociones = checkPromociones;
	}

	public String getNombrePlayaParameter() {
		return nombrePlayaParameter;
	}

	public void setNombrePlayaParameter(String nombrePlayaParameter) {
		this.nombrePlayaParameter = nombrePlayaParameter;
	}

	public String getDireccionDesde() {
		return direccionDesde;
	}

	public void setDireccionDesde(String direccionDesde) {
		this.direccionDesde = direccionDesde;
	}

	public  Playa getPlayaselected() {
		return playaselected;
	}

	public  void setPlayaselected(Playa playaselected) {
		BusquedaPlayasManagedBean.playaselected = playaselected;
	}

	public String getDireccionHasta() {
		return direccionHasta=playaselected.getDomicilio();
	}

	public void setDireccionHasta(String direccionHasta) {
		this.direccionHasta = direccionHasta;
	}
	
	public void settearDireccionHasta()
	{
		this.direccionHasta=playaselected.getDomicilio();
		
	}
	
	public void tomarDomicilioHasta (String domicilio)
	{
		direccionHasta=domicilio;
	}
	
	public void tomarDomicilioDesde(){
	    this.setDireccionDesde(this.getDireccionDesde());
	}
	
	
	

}