/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.CuentaCliente;
import tesis.playon.web.model.TipoPago;
import tesis.playon.web.model.TransaccionCliente;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.ITipoPagoService;
import tesis.playon.web.service.ITransaccionClienteService;
import tesis.playon.web.service.IUsuarioService;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

/**
 * @author pablo
 * 
 */
@ManagedBean(name = "cuentaClienteMB")
@RequestScoped
public class CuentaClienteManagedBean {

    @ManagedProperty(value = "#{ClienteService}")
    IClienteService clienteService;

    @ManagedProperty(value = "#{UsuarioService}")
    IUsuarioService usuarioService;

    @ManagedProperty(value = "#{CuentaClienteService}")
    ICuentaClienteService cuentaClienteService;

    @ManagedProperty(value = "#{TipoPagoService}")
    ITipoPagoService tipoPagoService;

    @ManagedProperty(value = "#{TransaccionClienteService}")
    ITransaccionClienteService transaccionClienteService;

    private static Cliente cliente;

    private double importe;

    private CuentaCliente cuentaCliente;

    private TransaccionCliente transaccionCliente;

    private static Float saldo = 0.f;

    private List<TransaccionCliente> transacciones;

    private TipoPago tipoPago;

    private Date fechaDesde;

    private Date fechaHasta;

    @PostConstruct
    private void init() {
	this.fechaDesde = new Date();
	this.fechaHasta = new Date();
	this.fechaDesde = DateUtils.setDays(this.fechaDesde, 1);
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario usuario = getUsuarioService().findByNombreUsuario(userName);
	if(usuario != null){
	    cliente = getClienteService().findByUsuario(usuario);
	    cuentaCliente =cliente.getCuentaCliente();
	}
    }

    public void cargarSaldo() {
	try {
	    float saldoCliente = cliente.getCuentaCliente().getSaldo();
	    cliente.getCuentaCliente().setSaldo(saldoCliente + saldo);

	    transaccionCliente = new TransaccionCliente();
	    Timestamp fecha = new Timestamp(Calendar.getInstance().getTimeInMillis());
	    transaccionCliente.setFecha(fecha);
	    transaccionCliente.setImporte(getSaldo());
	    transaccionCliente.setCuentaCliente(cliente.getCuentaCliente());
	    TipoPago tipoPago = getTipoPagoService().findByNombreTipoPago("DineroMail");
	    transaccionCliente.setTipoPago(tipoPago);
	    getTransaccionClienteService().save(transaccionCliente);

	    FacesContext.getCurrentInstance().addMessage(
		    null,
		    new FacesMessage(FacesMessage.SEVERITY_INFO, "Transacción exitosa. Se agregó a su cuenta $"
			    + getSaldo() + " argentinos. ¡Muchas Gracias!", ""));

	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
    }

    public List<TransaccionCliente> getTransacciones() {
	Date fechaDesde = (this.fechaDesde != null ? this.fechaDesde : new Date(01012012));
	Date fechaHasta = (this.fechaHasta != null ? this.fechaHasta : Calendar.getInstance().getTime());
	transacciones = getTransaccionClienteService().findTransaccionesByFecha(cliente.getCuentaCliente(), fechaDesde,
		fechaHasta);
	return transacciones;
    }

    public void setTransacciones(List<TransaccionCliente> transacciones) {
	this.transacciones = transacciones;
    }

    public IClienteService getClienteService() {
	return clienteService;
    }

    public void setClienteService(IClienteService clienteService) {
	this.clienteService = clienteService;
    }

    public IUsuarioService getUsuarioService() {
	return usuarioService;
    }

    public void setUsuarioService(IUsuarioService usuarioService) {
	this.usuarioService = usuarioService;
    }

    public ICuentaClienteService getCuentaClienteService() {
	return cuentaClienteService;
    }

    public void setCuentaClienteService(ICuentaClienteService cuentaClienteService) {
	this.cuentaClienteService = cuentaClienteService;
    }

    public ITransaccionClienteService getTransaccionClienteService() {
	return transaccionClienteService;
    }

    public void setTransaccionClienteService(ITransaccionClienteService transaccionClienteService) {
	this.transaccionClienteService = transaccionClienteService;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	CuentaClienteManagedBean.cliente = cliente;
    }

    public TipoPago getTipoPago() {
	return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
	this.tipoPago = tipoPago;
    }

    public Float getSaldo() {
	return saldo;
    }

    public void setSaldo(Float saldo) {
	CuentaClienteManagedBean.saldo = saldo;
    }

    public double getImporte() {
	return importe;
    }

    public void setImporte(double importe) {
	this.importe = importe;
    }

    public ITipoPagoService getTipoPagoService() {
	return tipoPagoService;
    }

    public void setTipoPagoService(ITipoPagoService tipoPagoService) {
	this.tipoPagoService = tipoPagoService;
    }

    public CuentaCliente getCuentaCliente() {
	return cuentaCliente;
    }

    public void setCuentaCliente(CuentaCliente cuentaCliente) {
	this.cuentaCliente = cuentaCliente;
    }

    public TransaccionCliente getTransaccionCliente() {
	return transaccionCliente;
    }

    public void setTransaccionCliente(TransaccionCliente transaccionCliente) {
	this.transaccionCliente = transaccionCliente;
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
	String sep = File.separator;
	Document pdf = (Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
	String logo = extContext.getRealPath("resources" + sep + "images" + sep + "transacciones.png");
	pdf.addTitle("Historial de Transacciones");
	pdf.add(Image.getInstance(logo));
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

    public void updateListado() {
	this.fechaDesde = null;
	this.fechaHasta = null;
    }

}
