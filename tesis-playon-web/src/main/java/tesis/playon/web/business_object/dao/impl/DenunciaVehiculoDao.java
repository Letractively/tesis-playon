/**
 * 
 */
package tesis.playon.web.business_object.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.business_object.dao.IDenunciaVehiculoDao;
import tesis.playon.web.model.DenunciaVehiculo;

/**
 * @author Pablo
 *
 */
@Repository("DenunciaVehiculoDao")
public class DenunciaVehiculoDao extends CustomHibernateDaoSupport implements IDenunciaVehiculoDao{
    
    public void save(DenunciaVehiculo denunciaVehiculo) {
	getHibernateTemplate().save(denunciaVehiculo);
    }

    public void update(DenunciaVehiculo denunciaVehiculo) {
	getHibernateTemplate().update(denunciaVehiculo);
    }

    public void delete(DenunciaVehiculo denunciaVehiculo) {
	getHibernateTemplate().delete(denunciaVehiculo);
    }

    public DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo) {
	List<?> list = getHibernateTemplate().find("from DenunciaVehiculo where asunto=?", asuntoDenunciaVehiculo);
	return (DenunciaVehiculo) list.get(0);
    }

}
