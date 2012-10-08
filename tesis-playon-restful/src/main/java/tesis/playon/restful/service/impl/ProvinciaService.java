package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Pais;
import tesis.playon.restful.domain.Provincia;
import tesis.playon.restful.service.IProvinciaService;

@Service("provinciaService")
public class ProvinciaService implements IProvinciaService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Provincia provincia) {
	Session session = sessionFactory.getCurrentSession();
	session.save(provincia);
    }

    @Override
    public void update(Provincia cliente) {
	Session session = sessionFactory.getCurrentSession();
	session.update(cliente);
    }

    @Override
    public void delete(Provincia cliente) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(cliente);
    }

    @Override
    public Provincia findByNombreProvincia(String nombreProvincia) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Provincia where nombre=?")
		.setParameter(0, nombreProvincia).list();
	return (Provincia) list.get(0);
    }

    @Override
    public List<Provincia> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = session.createQuery("from Provincia order by nombre").list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }

    @Override
    public Provincia findByProvinciaId(Integer id) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Provincia where id=?")
		.setParameter(0, id).list();
	return (Provincia) list.get(0);
    }

    @Override
    public List<Provincia> findProvincias(Pais pais) {
	Session session = sessionFactory.getCurrentSession();
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = session.createQuery("from Provincia where pais=?")
		.setParameter(0, pais).list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }
}