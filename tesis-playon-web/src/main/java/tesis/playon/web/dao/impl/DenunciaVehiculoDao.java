package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import tesis.playon.util.CustomHibernateDaoSupport;
import tesis.playon.web.dao.IDenunciaVehiculoDao;
import tesis.playon.web.model.DenunciaVehiculo;

/**
 * @author Pablo
 * 
 */
@Repository("denunciaVehiculoDao")
public class DenunciaVehiculoDao extends CustomHibernateDaoSupport implements IDenunciaVehiculoDao {

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

    public List<DenunciaVehiculo> findAll() {
	List<DenunciaVehiculo> denuncias = new ArrayList<DenunciaVehiculo>();
	List<?> list = getHibernateTemplate().find("from DenunciaVehiculo");
	for (Object object : list) {
	    denuncias.add((DenunciaVehiculo) object);
	}
	return denuncias;
    }
}
