package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.TipoEstadia;
import tesis.playon.restful.service.ITipoEstadiaService;

@Service("tipoEstadiaService")
public class TipoEstadiaService implements ITipoEstadiaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(TipoEstadia tipoEstadia) {
	Session session = sessionFactory.getCurrentSession();
	session.save(tipoEstadia);
    }

    @Override
    public void update(TipoEstadia tipoEstadia) {
	Session session = sessionFactory.getCurrentSession();
	session.update(tipoEstadia);
    }

    @Override
    public void delete(TipoEstadia tipoEstadia) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(tipoEstadia);
    }

    @Override
    public TipoEstadia findByNombreTipoEstadia(String nombreTipoEstadia) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from TipoEstadia where nombre=?")
		.setParameter(0, nombreTipoEstadia).list();
	return (TipoEstadia) list.get(0);
    }

    @Override
    public List<TipoEstadia> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<TipoEstadia> tipos = new ArrayList<TipoEstadia>();
	List<?> list = session.createQuery("from TipoEstadia order by nombre").list();
	for (Object object : list) {
	    tipos.add((TipoEstadia) object);
	}
	return tipos;
    }
}