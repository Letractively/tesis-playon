package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import tesis.playon.web.model.Liquidacion;
import tesis.playon.web.model.TransaccionPlaya;
import tesis.playon.web.service.ITransaccionPlayaService;

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

    private Liquidacion liquidacionSelected;

    public List<TransaccionPlaya> getTransaccionesList() {
	transaccionesList = getTransaccionPlayaService().findTransaccionesByLiquidacion(liquidacionSelected);
	return transaccionesList;
    }

    public void setTransaccionesList(List<TransaccionPlaya> transaccionesList) {
	this.transaccionesList = transaccionesList;
    }

    public Liquidacion getLiquidacionSelected() {
	return liquidacionSelected;
    }

    public void setLiquidacionSelected(Liquidacion liquidacionSelected) {
	this.liquidacionSelected = liquidacionSelected;
    }

    public ITransaccionPlayaService getTransaccionPlayaService() {
	return transaccionPlayaService;
    }

    public void setTransaccionPlayaService(ITransaccionPlayaService transaccionPlayaService) {
	this.transaccionPlayaService = transaccionPlayaService;
    }

}
