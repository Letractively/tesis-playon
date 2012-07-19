package tesis.playon.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import tesis.playon.web.dao.IFavoritoDao;
import tesis.playon.web.model.Favorito;

/**
 * @author Pablo
 * 
 */
public class FavoritoDao implements IFavoritoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
    }

    public void save(Favorito favorito) {
	getSessionFactory().getCurrentSession().save(favorito);
    }

    public void update(Favorito favorito) {
	getSessionFactory().getCurrentSession().update(favorito);
    }

    public void delete(Favorito favorito) {
	getSessionFactory().getCurrentSession().delete(favorito);
    }

    public List<Favorito> findAll() {
	List<Favorito> favoritos = new ArrayList<Favorito>();
	List<?> list = getSessionFactory().getCurrentSession().createQuery("from Favorito").list();
	for (Object object : list) {
	    favoritos.add((Favorito) object);
	}
	return favoritos;
    }
}
