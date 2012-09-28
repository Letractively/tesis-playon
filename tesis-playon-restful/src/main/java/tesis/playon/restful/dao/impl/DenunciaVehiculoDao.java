package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import tesis.playon.restful.dao.IDenunciaVehiculoDao;
import tesis.playon.restful.domain.DenunciaVehiculo;

@Repository("denunciaVehiculoDao")
public class DenunciaVehiculoDao extends HibernateDaoSupport implements IDenunciaVehiculoDao {

    @Override
    public void save(DenunciaVehiculo denunciaVehiculo) {
	getSessionFactory().getCurrentSession().save(denunciaVehiculo);
    }

    @Override
    public void update(DenunciaVehiculo denunciaVehiculo) {
	getSessionFactory().getCurrentSession().update(denunciaVehiculo);
    }

    @Override
    public void delete(DenunciaVehiculo denunciaVehiculo) {
	getSessionFactory().getCurrentSession().delete(denunciaVehiculo);
    }

    @Override
    public DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DenunciaVehiculo where asunto=?")
		.setParameter(0, asuntoDenunciaVehiculo).list();
	return (DenunciaVehiculo) list.get(0);
    }

    @Override
    public List<DenunciaVehiculo> findAll() {
	List<DenunciaVehiculo> denuncias = new ArrayList<DenunciaVehiculo>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from DenunciaVehiculo").list();
	for (Object object : list) {
	    denuncias.add((DenunciaVehiculo) object);
	}
	return denuncias;
    }
    
}