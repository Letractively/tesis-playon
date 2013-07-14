/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.ILiquidacionService;
import tesis.playon.web.service.IPublicidadService;
import tesis.playon.web.service.IUsuarioService;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
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

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

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

	Document documento = new Document(new Rectangle(PageSize.A4.rotate()));
	FileOutputStream archivoPdf;

	Date hoy = new Date();
	SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");

	// Declaramos la imagen y texto de la cabecera
	Phrase txtCabeceraFecha = null;
	Phrase txtCabeceraUsuario = null;
	Image imagen = null;
	Font fuente = FontFactory.getFont("arial",12,Font.BOLDITALIC);

	try {
	    archivoPdf = new FileOutputStream("/tmp/informe_" + formato.format(hoy) + ".pdf");
	    PdfWriter writer = PdfWriter.getInstance(documento, archivoPdf);
	    documento.open();
	    documento.setMargins(72.0f, 72.0f, 72.0f, 72.0f);

	    /** setteamos la cabecera del documento **/
	    try {
		// Obtenemos la imagen
		ExternalContext extContext = null;
		extContext = FacesContext.getCurrentInstance().getExternalContext();
		String sep = File.separator;
		String path = extContext.getRealPath("resources" + sep + "images") 
			+ sep + "logo_playon_admin.png";
		imagen = Image.getInstance(path);
		imagen.scaleToFit(280, 110);
		

		// Agregamos tambien un texto que acompañe la imagen
		formato = new SimpleDateFormat("dd/MM/yyyy");
		Usuario usuario = getUsuario();
		String encabezadoFecha = formato.format(hoy);
		txtCabeceraFecha = new Phrase(encabezadoFecha);
		txtCabeceraFecha.setFont(fuente);

		String encabezadoUsuario = usuario.getApellido() + ", " + usuario.getNombre();
		txtCabeceraUsuario = new Phrase(encabezadoUsuario);
		txtCabeceraUsuario.setFont(fuente);

	    } catch (BadElementException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }

	    // Creamos un objeto PdfContentByte donde se guarda el contenido
	    // de una página en el pdf. Gráficos y texto.
	    PdfContentByte cb = writer.getDirectContent();


	    // Esta es la imágen
	    /*ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, imgCabecera
		    , (documento.right() - 200), documento.top(),0);*/

	    // Este es el texto
	    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, txtCabeceraFecha,
		    (documento.right() - 70), documento.top() + 10, 0);
	    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, txtCabeceraUsuario,
		    (documento.right() - 70), documento.top() - 20, 0);

	    documento.add(imagen);
	    
	    PdfPTable tabla = new PdfPTable(4);
	    tabla.setWidthPercentage(100);
	    
	    PdfPCell celdaMes = new PdfPCell(new Paragraph("Mes", fuente));
	    tabla.addCell(celdaMes);
	    
	    PdfPCell celdaAnio = new PdfPCell(new Paragraph("Año", fuente));
	    tabla.addCell(celdaAnio);
	    
	    PdfPCell celdaTransacciones = new PdfPCell(new Paragraph("Cantidad de transacciones", fuente));
	    tabla.addCell(celdaTransacciones);
	    
	    PdfPCell celdaComision = new PdfPCell(new Paragraph("Comisión por el servicio", fuente));
	    tabla.addCell(celdaComision);
	    
	    
	    for (int i = 0; i < comisionesList.size(); i++)
	    {
		String [] vComision = comisionesList.get(i);
		tabla.addCell(convertirMes(vComision[1]));
		tabla.addCell(vComision[0]);
	    	tabla.addCell(vComision[2]);
	    	tabla.addCell("$ "+ vComision[3]);
	    	
	    }
	    
	    documento.add(new Paragraph("\n"));
	    documento.add(tabla);
	    documento.close();
	    writer.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private Usuario getUsuario() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario user = getUsuarioService().findByNombreUsuario(userName);
	return user;
    }

    private String convertirMes(String mesEnNumero) {
	String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto"
		,"Septiembre", "Octubre", "Noviembre", "Diciembre" };
	return meses[Integer.parseInt(mesEnNumero)-1];
    }

    /**
     * @return the usuarioService
     */
    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    /**
     * @param usuarioService
     *            the usuarioService to set
     */
    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
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
