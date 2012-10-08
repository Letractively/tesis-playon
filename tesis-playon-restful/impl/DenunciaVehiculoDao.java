package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tesis.playon.web.dao.IDenunciaVehiculoDao;
import tesis.playon.web.model.DenunciaVehiculo;

/**
 * @author Pablo
 * 
 */
@Repository("denunciaVehiculoDao")
public class DenunciaVehiculoDao implements IDenunciaVehiculoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(DenunciaVehiculo denunciaVehiculo) {
	session.save(denunciaVehiculo);
    }

    public void update(DenunciaVehiculo denunciaVehiculo) {
	session.update(denunciaVehiculo);
    }

    public void delete(DenunciaVehiculo denunciaVehiculo) {
	session.delete(denunciaVehiculo);
    }

    public DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo) {
	List<?> list = session.createQuery("from DenunciaVehiculo where asunto=?")
		.setParameter(0, asuntoDenunciaVehiculo).list();
	return (DenunciaVehiculo) list.get(0);
    }

    public List<DenunciaVehiculo> findAll() {
	List<DenunciaVehiculo> denuncias = new ArrayList<DenunciaVehiculo>();
	List<?> list = session.createQuery("from DenunciaVehiculo").list();
	for (Object object : list) {
	    denuncias.add((DenunciaVehiculo) object);
	}
	return denuncias;
    }
}
