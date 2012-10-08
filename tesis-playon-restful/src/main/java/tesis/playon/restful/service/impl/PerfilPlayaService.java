package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.PerfilPlaya;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.service.IPerfilPlayaService;

@Service("perfilPlayaService")
public class PerfilPlayaService implements IPerfilPlayaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(PerfilPlaya perfilPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.save(perfilPlaya);
    }

    @Override
    public void update(PerfilPlaya perfilPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.update(perfilPlaya);
    }

    @Override
    public void delete(PerfilPlaya perfilPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(perfilPlaya);
    }

    @Override
    public PerfilPlaya findByNombrePerfilPlaya(String nombrePerfilPlaya) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from PerfilPlaya where nombre=?")
		.setParameter(0, nombrePerfilPlaya).list();
	if (!list.isEmpty())
	    return (PerfilPlaya) list.get(0);
	return null;
    }

    @Override
    public List<PerfilPlaya> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<PerfilPlaya> perfiles = new ArrayList<PerfilPlaya>();
	List<?> list = session.createQuery("from PerfilPlaya").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		perfiles.add((PerfilPlaya) object);
	    }
	    return perfiles;
	}
	return null;
    }

    @Override
    public PerfilPlaya findByPlaya(Playa playa) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from PerfilPlaya where playa=?")
		.setParameter(0, playa).list();
	if (!list.isEmpty())
	    return (PerfilPlaya) list.get(0);
	return null;
    }
}