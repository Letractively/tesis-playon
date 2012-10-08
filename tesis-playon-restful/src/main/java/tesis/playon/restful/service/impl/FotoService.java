package tesis.playon.restful.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import tesis.playon.restful.domain.Foto;
import tesis.playon.restful.domain.PerfilPlaya;
import tesis.playon.restful.service.IFotoService;

@Service("fotoService")
public class FotoService implements IFotoService {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    
    @Override
    public void save(Foto foto) {
	Session session = sessionFactory.getCurrentSession();
	session.save(foto);
    }

    @Override
    public void update(Foto foto) {
	Session session = sessionFactory.getCurrentSession();
	session.update(foto);
    }

    @Override
    public void delete(Foto foto) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(foto);
    }

    @Override
    public Foto findByLinkFoto(String link) {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createQuery("from Foto where nombre=?")
		.setParameter(0, link).list();
	return (Foto) list.get(0);
    }

    @Override
    public List<Foto> findAll() {
	Session session = sessionFactory.getCurrentSession();
	List<Foto> fotos = new ArrayList<Foto>();
	List<?> list = session.createQuery("from Foto").list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		fotos.add((Foto) object);
	    }
	    return fotos;
	}
	return null;
    }

    @Override
    public Integer obtenerUltimoID() {
	Session session = sessionFactory.getCurrentSession();
	List<?> list = session.createSQLQuery("SELECT MAX(fotoID) FROM foto").list();
	Integer ultimoID;
	if (list.get(0) == null)
	    ultimoID = 1;
	else
	    ultimoID = ((Integer) list.get(0)) + 1;
	return ultimoID;
    }

    @Override
    public List<Foto> findByPlaya(PerfilPlaya perfil) {
	Session session = sessionFactory.getCurrentSession();
	List<Foto> fotos = new ArrayList<Foto>();
	List<?> list = session.createQuery("from Foto where perfilPlaya=?")
		.setParameter(0, perfil).list();
	if (!list.isEmpty()) {
	    for (Object object : list) {
		fotos.add((Foto) object);
	    }
	    return fotos;
	}
	return null;
    }
}