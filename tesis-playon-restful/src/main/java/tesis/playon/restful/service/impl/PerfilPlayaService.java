package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.PerfilPlaya;
import tesis.playon.restful.service.IPerfilPlayaService;

@Service("perfilPlayaService")
@Transactional
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
    public PerfilPlaya findByPlaya(String nombreComercialPlaya) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from PerfilPlaya where playa.nombreComercial=?")
		.setParameter(0, nombreComercialPlaya).list();
	if (!list.isEmpty())
	    return (PerfilPlaya) list.get(0);
	return null;
    }
}