package tesis.playon.web.managed.bean;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import tesis.playon.web.model.Liquidacion;
import tesis.playon.web.model.TransaccionPlaya;
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

	private List<TransaccionPlaya> transaccionesList;

	private static Liquidacion liquidacionSelected;

	@PostConstruct
	private void init() {
		if (liquidacionSelected != null && transaccionesList == null) {
			updateTransaccionesList();
		}
	}

	public void updateTransaccionesList() {
		transaccionesList = getTransaccionPlayaService()
				.findTransaccionesByLiquidacion(liquidacionSelected);
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

	public void setTransaccionPlayaService(
			ITransaccionPlayaService transaccionPlayaService) {
		this.transaccionPlayaService = transaccionPlayaService;
	}

	public void listadoDetalleTransaccionPDF(Object document)
			throws IOException, BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 16, Font.NORMAL,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph("Detalle de transacciones de la playa "
				+ liquidacionSelected.getEstadia().getPlaya().getRazonSocial()
				+ " emitido el " + fechaActual(), fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de empleados");
		pdf.add(Image.getInstance(logo));
		pdf.add(titulo);
	}

	public void listadoGeneralTransaccionPDF(Object document)
			throws IOException, BadElementException, DocumentException {

		Font fuenteNegra18 = new Font(Font.TIMES_ROMAN, 16, Font.BOLD,
				Color.BLACK);

		Paragraph titulo = new Paragraph();
		titulo.add(new Paragraph(
				"Listado de playas de estacionamiento liquidadas",
				fuenteNegra18));
		agregarLineasEnBlanco(titulo, 2);
		titulo.setAlignment(Element.ALIGN_CENTER);
		String sep = File.separator;
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);
		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String logo = extContext.getRealPath("resources" + sep + "images" + sep
				+ "transacciones.png");
		pdf.addTitle("Listado de empleados");
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
