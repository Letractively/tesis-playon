/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import tesis.playon.web.service.ILiquidacionService;
import tesis.playon.web.service.IPublicidadService;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "estadisticaAdministracionMB")
@ViewScoped
public class EstadisticasAdministracionManagedBean implements Serializable {

    private static final long serialVersionUID = -5157703369335370457L;

    @ManagedProperty(value = "#{LiquidacionService}")
    ILiquidacionService liquidacionService;

    @ManagedProperty(value = "#{PublicidadService}")
    IPublicidadService publicidadService;

    private List<String[]> comisionesList, comisionesPublicidadList;

    private Date fechaDesde, fechaHasta;

    private CartesianChartModel lModelComision;

    private String datatipFormat;

    private int maximoMonto;

    @PostConstruct
    public void init() {
	comisionesList = new ArrayList<String[]>();
	comisionesPublicidadList = new ArrayList<String[]>();
    }

    public void calcularComisionPorPeriodo() {
	crearGraficoComision();
    }

    private void crearGraficoComision() {
	lModelComision = new CartesianChartModel();
	maximoMonto = 0;
	LineChartSeries serieComisionTransacciones = new LineChartSeries();
	serieComisionTransacciones.setLabel("Recaudación de transacciones en $");
	comisionesList = getLiquidacionService().getEstadisticasComisiones(fechaDesde, fechaHasta);
	for (int i = 0; i < comisionesList.size(); i++) {
	    String[] vComision = (String[]) comisionesList.get(i);
	    float comision = Float.parseFloat(vComision[3]);
	    maximoMonto = (int) comision > maximoMonto ? (int) comision : maximoMonto;
	    serieComisionTransacciones.set(String.format("%02d", Integer.parseInt(vComision[1])) + "/" + vComision[0],
		    comision);
	}
	lModelComision.addSeries(serieComisionTransacciones);

	if (maximoMonto > 0)
	    maximoMonto += 20;
    }

    public void crearPDF() {

	Date hoy = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

	Document documento = new Document(new Rectangle(PageSize.A4.rotate()));
	FileOutputStream archivoPdf;

	try {
	    archivoPdf = new FileOutputStream("/tmp/informe_" + formato.format(hoy) + ".pdf");

	    PdfWriter.getInstance(documento, archivoPdf).setInitialLeading(20);

	    documento.open();
	    Image img = Image.getInstance(getClass().getResource("/images/logo_playon_admin.png"));
	    img.scaleToFit(PageSize.A4.getHeight() * 1.3F, PageSize.A4.getWidth() * 1.3F);
	    img.setAbsolutePosition(0.0F, 0.0F);

	    documento.add(img);
	    
	    
	    
	    
	    
	    
	} catch (Exception ex) {

	}
    }

    /**
     * @return the liquidacionService
     */
    public ILiquidacionService getLiquidacionService() {
	return liquidacionService;
    }

    /**
     * @param liquidacionService
     *            the liquidacionService to set
     */
    public void setLiquidacionService(ILiquidacionService liquidacionService) {
	this.liquidacionService = liquidacionService;
    }

    /**
     * @return the publicidadService
     */
    public IPublicidadService getPublicidadService() {
	return publicidadService;
    }

    /**
     * @param publicidadService
     *            the publicidadService to set
     */
    public void setPublicidadService(IPublicidadService publicidadService) {
	this.publicidadService = publicidadService;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
	return fechaDesde;
    }

    /**
     * @param fechaDesde
     *            the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
	this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
	return fechaHasta;
    }

    /**
     * @param fechaHasta
     *            the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
	this.fechaHasta = fechaHasta;
    }

    /**
     * @return the datatipFormat
     */
    public String getDatatipFormat() {
	datatipFormat = "<span style=\"display:none;\">$%.2f</span><span>$%.2f</span>";
	return datatipFormat;
    }

    /**
     * @param datatipFormat
     *            the datatipFormat to set
     */
    public void setDatatipFormat(String datatipFormat) {
	this.datatipFormat = datatipFormat;
    }

    /**
     * @return the maximaComision
     */
    public int getMaximoMonto() {
	return maximoMonto;
    }

    /**
     * @param maximaComision
     *            the maximaComision to set
     */
    public void setMaximoMonto(int maximaComision) {
	this.maximoMonto = maximaComision;
    }

    /**
     * @return the lModelComision
     */
    public CartesianChartModel getlModelComision() {
	return lModelComision;
    }

    /**
     * @param lModelComision
     *            the lModelComision to set
     */
    public void setlModelComision(CartesianChartModel lModelComision) {
	this.lModelComision = lModelComision;
    }

    /**
     * @return the comisionesList
     */
    public List<String[]> getComisionesList() {
	return comisionesList;
    }

    /**
     * @param comisionesList
     *            the comisionesList to set
     */
    public void setComisionesList(List<String[]> comisionesList) {
	this.comisionesList = comisionesList;
    }

    /**
     * @return the comisionesPublicidadList
     */
    public List<String[]> getComisionesPublicidadList() {
	return comisionesPublicidadList;
    }

    /**
     * @param comisionesPublicidadList
     *            the comisionesPublicidadList to set
     */
    public void setComisionesPublicidadList(List<String[]> comisionesPublicidadList) {
	this.comisionesPublicidadList = comisionesPublicidadList;
    }

}
