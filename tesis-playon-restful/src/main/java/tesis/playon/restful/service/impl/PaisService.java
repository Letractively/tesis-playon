package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Pais;
import tesis.playon.restful.domain.Provincia;
import tesis.playon.restful.service.IPaisService;

@Service("paisService")
public class PaisService implements IPaisService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Pais pais) {
	Session session = sessionFactory.getCurrentSession();
	session.save(pais);
    }

    @Override
    public void update(Pais pais) {
	Session session = sessionFactory.getCurrentSession();
	session.update(pais);
    }

    @Override
    public void delete(Pais pais) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(pais);
    }

    @Override
    public Pais findByNombrePais(String nombrePais) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Pais where nombre=?")
		.setParameter(0, nombrePais).list();
	return (Pais) list.get(0);
    }

    @Override
    public List<Pais> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Pais> paises = new ArrayList<Pais>();
	List<?> list = session.createQuery("from Pais order by nombre").list();
	for (Object object : list) {
	    paises.add((Pais) object);
	}
	return paises;
    }

    @Override
    public Pais findByPaisId(Integer id) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Pais where id=?").setParameter(0, id)
		.list();
	return (Pais) list.get(0);
    }

    @Override
    public List<Provincia> getProvincias(Integer idPais) {
	Session session = sessionFactory.getCurrentSession();
	List<Provincia> provincias = new ArrayList<Provincia>();
	List<?> list = session.createQuery("from Provincia where pais.id=?")
		.setParameter(0, idPais).list();
	for (Object object : list) {
	    provincias.add((Provincia) object);
	}
	return provincias;
    }
}