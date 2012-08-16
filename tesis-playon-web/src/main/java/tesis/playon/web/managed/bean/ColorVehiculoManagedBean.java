/**
 * 
 */
package tesis.playon.web.managed.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import tesis.playon.web.model.ColorVehiculo;
import tesis.playon.web.service.IColorVehiculoService;

/**
 * @author pablo
 *
 */
@ManagedBean(name = "colorVehiculoMB")
@RequestScoped
public class ColorVehiculoManagedBean implements Serializable  {

    private static final long serialVersionUID = -1085389423375986168L;
    
    @ManagedProperty(value = "#{ColorVehiculoService}")
    IColorVehiculoService colorVehiculoService;
    
    private List<ColorVehiculo> colorVehiculoList;
    
    private ColorVehiculo colorNulo;

    public IColorVehiculoService getColorVehiculoService() {
        return colorVehiculoService;
    }

    public void setColorVehiculoService(IColorVehiculoService colorVehiculoService) {
        this.colorVehiculoService = colorVehiculoService;
    }

    public List<ColorVehiculo> getColorVehiculoList() {
	colorVehiculoList= new ArrayList<ColorVehiculo>();
	colorVehiculoList.addAll(getColorVehiculoService().findAll());
        return colorVehiculoList;
    }

    public void setColorVehiculoList(List<ColorVehiculo> colorVehiculoList) {
        this.colorVehiculoList = colorVehiculoList;
    }

    public ColorVehiculo getColorNulo() {
	colorNulo = new ColorVehiculo();
	colorNulo.setId(0);
	colorNulo.setNombre("Seleccione un color");
        return colorNulo;
    }

    public void setColorNulo(ColorVehiculo colorNulo) {
        this.colorNulo = colorNulo;
    } 
}
