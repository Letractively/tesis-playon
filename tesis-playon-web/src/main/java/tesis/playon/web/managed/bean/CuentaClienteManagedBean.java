/**
 * 
 */
package tesis.playon.web.managed.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.dao.DataAccessException;

import tesis.playon.web.model.Cliente;
import tesis.playon.web.model.Usuario;
import tesis.playon.web.service.IClienteService;
import tesis.playon.web.service.ICuentaClienteService;
import tesis.playon.web.service.IUsuarioService;

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
    
    private static Cliente cliente;
    
    private static Float saldo=0.f;
    
    public void cargarSaldo() {
	try {
	    float saldoCliente = cliente.getCuentaCliente().getSaldo();
	    cliente.getCuentaCliente().setSaldo(saldoCliente + saldo);
	    getCuentaClienteService().update(cliente.getCuentaCliente());

	    FacesContext.getCurrentInstance().addMessage(null,
		    new FacesMessage(FacesMessage.SEVERITY_INFO, "Transacción exitosa. Se agregó a su cuenta $" + getSaldo() + " argentinos. ¡Muchas Gracias!",""));
	    
	} catch (DataAccessException e) {
	    e.printStackTrace();
	}
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

    public Cliente getCliente() {
	FacesContext facesContext = FacesContext.getCurrentInstance();
	String userName = facesContext.getExternalContext().getRemoteUser();
	Usuario usuario = getUsuarioService().findByNombreUsuario(userName);
	cliente = getClienteService().findByUsuario(usuario);
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	CuentaClienteManagedBean.cliente = cliente;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        CuentaClienteManagedBean.saldo = saldo;
    }

}
