package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.DenunciaVehiculo;
import tesis.playon.restful.service.IDenunciaVehiculoService;

@Service("denunciaVehiculoService")
public class DenunciaVehiculoService implements IDenunciaVehiculoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(DenunciaVehiculo denunciaVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.save(denunciaVehiculo);
    }

    @Override
    public void update(DenunciaVehiculo denunciaVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.update(denunciaVehiculo);
    }

    @Override
    public void delete(DenunciaVehiculo denunciaVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(denunciaVehiculo);
    }

    @Override
    public DenunciaVehiculo findByAsuntoDenunciaVehiculo(String asuntoDenunciaVehiculo) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from DenunciaVehiculo where asunto=?")
		.setParameter(0, asuntoDenunciaVehiculo).list();
	return (DenunciaVehiculo) list.get(0);
    }

    @Override
    public List<DenunciaVehiculo> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<DenunciaVehiculo> denuncias = new ArrayList<DenunciaVehiculo>();
	List<?> list = session.createQuery("from DenunciaVehiculo").list();
	for (Object object : list) {
	    denuncias.add((DenunciaVehiculo) object);
	}
	return denuncias;
    }
}