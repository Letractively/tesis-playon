package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tesis.playon.restful.domain.EstadoPlaya;
import tesis.playon.restful.service.IEstadoPlayaService;

@Service("estadoPlayaService")
@Transactional
public class EstadoPlayaService implements IEstadoPlayaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(EstadoPlaya estadoPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.save(estadoPlaya);
    }

    @Override
    public void update(EstadoPlaya estadoPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.update(estadoPlaya);
    }

    @Override
    public void delete(EstadoPlaya estadoPlaya) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(estadoPlaya);
    }

    @Override
    public EstadoPlaya findByNombreEstadoPlaya(String nombreEstado) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from EstadoPlaya where nombre=?")
		.setParameter(0, nombreEstado).list();
	return (EstadoPlaya) list.get(0);
    }

    @Override
    public List<EstadoPlaya> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<EstadoPlaya> estadoPlaya = new ArrayList<EstadoPlaya>();
	List<?> list = session.createQuery("from EstadoPlaya order by nombre").list();
	for (Object object : list) {
	    estadoPlaya.add((EstadoPlaya) object);
	}
	return estadoPlaya;
    }
}