package tesis.playon.restful.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import tesis.playon.restful.dao.IFotoDao;
import tesis.playon.restful.domain.Foto;
import tesis.playon.restful.domain.PerfilPlaya;

public class FotoDao extends HibernateDaoSupport implements IFotoDao {

    @Override
    public void save(Foto foto) {
	getSessionFactory().getCurrentSession().save(foto);
    }

    @Override
    public void update(Foto foto) {
	getSessionFactory().getCurrentSession().update(foto);
    }

    @Override
    public void delete(Foto foto) {
	getSessionFactory().getCurrentSession().delete(foto);
    }

    @Override
    public Foto findByLinkFoto(String link) {
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Foto where nombre=?")
		.setParameter(0, link).list();
	return (Foto) list.get(0);
    }

    @Override
    public List<Foto> findAll() {
	List<Foto> fotos = new ArrayList<Foto>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Foto").list();
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
	List<?> list = getSessionFactory().getCurrentSession().createSQLQuery("SELECT MAX(fotoID) FROM foto").list();
	Integer ultimoID;
	if (list.get(0) == null)
	    ultimoID = 1;
	else
	    ultimoID = ((Integer) list.get(0)) + 1;
	return ultimoID;
    }

    @Override
    public List<Foto> findByPlaya(PerfilPlaya perfil) {
	List<Foto> fotos = new ArrayList<Foto>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Foto where perfilPlaya=?")
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