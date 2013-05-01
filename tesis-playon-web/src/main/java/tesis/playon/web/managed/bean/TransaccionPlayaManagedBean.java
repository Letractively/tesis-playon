package tesis.playon.web.managed.bean;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.time.DateUtils;

import tesis.playon.web.model.CuentaPlaya;
import tesis.playon.web.model.DetalleEstadia;
import tesis.playon.web.model.Liquidacion;
import tesis.playon.web.model.Playa;
import tesis.playon.web.model.TransaccionPlaya;
import tesis.playon.web.service.ICuentaPlayaService;
import tesis.playon.web.service.IPlayaService;
import tesis.playon.web.service.ITransaccionPlayaService;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

/**
 * 
 * @author alejandro
 * 
 */

@ManagedBean(name = "transaccionPlayaMB")
@RequestScoped
public class TransaccionPlayaManagedBean implements Serializable {

    private static final long serialVersionUID = 3260243859983118685L;

    @ManagedProperty(value = "#{TransaccionPlayaService}")
    private ITransaccionPlayaService transaccionPlayaService;

    @ManagedProperty(value = "#{PlayaService}")
    private IPlayaService playaService;

    @ManagedProperty(value = "#{CuentaPlayaService}")
    private ICuentaPlayaService cuentaPlayaService;

    private List<TransaccionPlaya> transaccionesList;

    private List<TransaccionPlaya> transaccionesPorFechaList;

    private static Liquidacion liquidacionSelected;

    private static DetalleEstadia estadiaSelected;

    private String acTxtPlaya;

    private Date fechaDesde;

    private Date fechaHasta;

    @PostConstruct
    private void init() {
	this.fechaDesde = new Date();
	this.fechaHasta = new Date();
	this.fechaDesde = DateUtils.setDays(this.fechaDesde, 1);
	if (liquidacionSelected != null && transaccionesList == null) {
	    updateTransaccionesList();
	}
    }

    public List<String> complete(String query) {
	List<String> results = new ArrayList<String>();

	List<Playa> playas = this.getPlayaService().findByLikeNombreComercial(query);

	for (Playa nombrePlaya : playas) {
	    results.add(nombrePlaya.getNombreComercial());
	}

	return results;
    }

    public List<Playa> completePlayas(String query) {

	List<Playa> playas = this.getPlayaService().findByLikeNombreComercial(query);

	return playas;
    }

    public List<TransaccionPlaya> getTransaccionesPorFechaList() {

	Date fechaDesde = (this.fechaDesde != null ? this.fechaDesde : new Date(01012012));
	Date fechaHasta = (this.fechaHasta != null ? this.fechaHasta : Calendar.getInstance().getTime());

	Playa playa = getPlayaService().findByNombreComercial(this.acTxtPlaya);
	CuentaPlaya cuentaPlaya = getCuentaPlayaService().findByPlaya(playa);

	transaccionesPorFechaList = getTransaccionPlayaService().findTransaccionesByFecha(cuentaPlaya, fechaDesde,
		fechaHasta);

	// ((TransaccionPlaya)transaccionesPorFechaList.get(0)).getDetalleEstadia().getVehiculo().getPatente().
	return transaccionesPorFechaList;
    }

    public void setTransaccionesPorFechaList(List<TransaccionPlaya> transaccionesPorFechaList) {
	this.transaccionesPorFechaList = transaccionesPorFechaList;
    }

    public void updateTransaccionesList() {
	transaccionesList = getTransaccionPlayaService().findTransaccionesByLiquidacion(liquidacionSelected);
    }

    public List<TransaccionPlaya> getTransaccionesList() {
	// transaccionesList =
	// getTransaccionPlayaService().findTransaccionesByLiquidacion(liquidacionSelected);
	if (transaccionesList == null) {
	    updateTransaccionesList();
	}
	return transaccionesList;
    }

    public void setTransaccionesList(List<TransaccionPlaya> transaccionesList) {
	this.transaccionesList = transaccionesList;
    }

    public Liquidacion getLiquidacionSelected() {
	return liquidacionSelected;
    }

    public void setLiquidacionSelected(Liquidacion liquidacionSelected) {
	TransaccionPlayaManagedBean.liquidacionSelected = liquidacionSelected;
    }

    public ITransaccionPlayaService getTransaccionPlayaService() {
	return transaccionPlayaService;
    }

    public void setTransaccionPlayaService(ITransaccionPlayaService transaccionPlayaService) {
	this.transaccionPlayaService = transaccionPlayaService;
    }

    public IPlayaService getPlayaService() {
	return playaService;
    }

    public void setPlayaService(IPlayaService playaService) {
	this.playaService = playaService;
    }

    public ICuentaPlayaService getCuentaPlayaService() {
	return cuentaPlayaService;
    }

    public void setCuentaPlayaService(ICuentaPlayaService cuentaPlayaService) {
	this.cuentaPlayaService = cuentaPlayaService;
    }

    public String getAcTxtPlaya() {
	return acTxtPlaya;
    }

    public void setAcTxtPlaya(String acTxtPlaya) {
	this.acTxtPlaya = acTxtPlaya;
    }

    public Date getFechaDesde() {
	return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
	return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    public DetalleEstadia getEstadiaSelected() {
	return estadiaSelected;
    }

    public void setEstadiaSelected(DetalleEstadia estadiaSelected) {
	TransaccionPlayaManagedBean.estadiaSelected = estadiaSelected;
    }

    public void listadoDetalleTransaccionPDF(Object document) throws IOException, BadElementException,
	    DocumentException {

	Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 16, Font.NORMAL, Color.BLACK);

	Paragraph titulo = new Paragraph();
	titulo.add(new Paragraph("Detalle de transacciones de la playa "
		+ liquidacionSelected.getEstadia().getPlaya().getRazonSocial() + " emitido el " + fechaActual(),
		fuenteNegra18));
	agregarLineasEnBlanco(titulo, 2);
	titulo.setAlignment(Element.ALIGN_CENTER);
	String sep = File.separator;
	Document pdf = (Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	pdf.addTitle("Listado de empleados");
	pdf.add(Image.getInstance(logo));
	pdf.add(titulo);
    }

    public void listadoGeneralTransaccionPDF(Object document) throws IOException, BadElementException,
	    DocumentException {

	Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.BLACK);

	Paragraph titulo = new Paragraph();
	titulo.add(new Paragraph("Listado de playas de estacionamiento liquidadas", fuenteNegra18));
	agregarLineasEnBlanco(titulo, 2);
	titulo.setAlignment(Element.ALIGN_CENTER);
	String sep = File.separator;
	Document pdf = (Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	pdf.addTitle("Listado de empleados");
	pdf.add(Image.getInstance(logo));
	pdf.add(titulo);
    }

    public void listadoTransaccionesPDF(Object document) throws IOException, BadElementException,
	    DocumentException {

	Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.BLACK);

	Paragraph titulo = new Paragraph();
	titulo.add(new Paragraph("Listado de transacciones", fuenteNegra18));
	agregarLineasEnBlanco(titulo, 2);
	titulo.setAlignment(Element.ALIGN_CENTER);
	String sep = File.separator;
	Document pdf = (Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	pdf.addTitle("Listado de transacciones");
	pdf.add(Image.getInstance(logo));
	pdf.add(titulo);
    }

    private static void agregarLineasEnBlanco(Paragraph parrafo, int nLineas) {
	for (int i = 0; i < nLineas; i++)
	    parrafo.add(new Paragraph(" "));
    }

    private String fechaActual() {
	Date hoy = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	String fecha = formato.format(hoy);
	return fecha;

    }

}
